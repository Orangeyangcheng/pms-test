package common;

import api_test.lease.LeaseBO;
import mybatis.dao.CommunityDao;
import mybatis.dao.PmsHouseDao;
import mybatis.dao.PmsTenantDao;
import mybatis.pojo.*;
import mybatis.util.MyBatisUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.DataProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class DataSupport {

    /**
     * 随机获取一个小区信息
     * @return
     */
    public static Community getCommunity(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        CommunityDao communityDao = sqlSession.getMapper( CommunityDao.class );
        Community community = communityDao.getARandCommunity();
        sqlSession.close();
        return community;
    }

    /**
     * 根据城市id获取随机小区
     * @return
     */
    public static Community getCommunityByCity(int cityId){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        CommunityDao communityDao = sqlSession.getMapper( CommunityDao.class );
        Community community = communityDao.getCommunityByCity(cityId);
        sqlSession.close();
        return community;
    }

    /**
     * 根据超管手机号查询组织信息
     * @param adminPhone
     * @return
     */
    public static PmsTenant queryTenantInfoByAdminPhone(String adminPhone){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        PmsTenantDao pmsTenantDao = sqlSession.getMapper( PmsTenantDao.class );
        PmsTenant pmsTenant = pmsTenantDao.getTenantInfo( adminPhone );
        System.out.println("========================"+"查询组织信息"+"========================");
        System.out.println( pmsTenant );
        sqlSession.close();
        return pmsTenant;
    }

    /**
     * 根据手机号查询人员信息
     * @param phone
     * @return
     */
    public static PmsUser queryUserInfoByPhone(String phone){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        PmsTenantDao pmsTenantDao = sqlSession.getMapper( PmsTenantDao.class );
        try {
            PmsUser pmsUser = pmsTenantDao.queryUserInfo( phone );
            if (pmsUser==null){
                return null;
            }
            System.out.println("========================"+"查询人员信息"+"========================");
            System.out.println( pmsUser );
            sqlSession.close();
            return pmsUser;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询组织对应城市的门店信息
     * @return
     */
    public static List<PmsStore> queryPmsStore(String tenantId,int cityId){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        PmsTenantDao pmsTenantDao = sqlSession.getMapper( PmsTenantDao.class );
        List<PmsStore> pmsStores = pmsTenantDao.getStoreInfo( tenantId,cityId );
        sqlSession.close();
        return pmsStores;
    }

    /**
     * 查询房源房间信息
     * @param roomCode
     * @return
     */
    public static HouseRoom queryRoomInfo(String roomCode){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        PmsHouseDao pmsHouseDao = sqlSession.getMapper( PmsHouseDao.class );
        HouseRoom houseRoom = pmsHouseDao.queryRoomInfo( roomCode );
        sqlSession.close();
        return houseRoom;
    }

    /**
     * 查询组织内可租房源
     * @param tenantId
     * @return
     */
    public static HouseRoom queryRentableRoomInfo(String tenantId){
        HouseRoom houseRoom = new HouseRoom();
        try{
            SqlSession sqlSession = MyBatisUtil.getSqlSession();
            PmsHouseDao pmsHouseDao = sqlSession.getMapper( PmsHouseDao.class );
            houseRoom = pmsHouseDao.queryRentableRoomInfo( tenantId );
            sqlSession.close();
        }
        catch (Exception e){
            System.out.println("查询组织可租房源异常");
        }
        return houseRoom;
    }

    /**
     *
     * @param type 1-楼幢 2-单元 3-房号
     * @return
     */
    public static Map randomInfo(int type){
        Random random = new Random();
        Map randomInfo = new HashMap();
        StringBuilder stringBuilder = new StringBuilder();
        if (type==1){
            stringBuilder.append(  random.nextInt(99)+1 );
            stringBuilder.append( "幢" );
            randomInfo.put( "flatBuilding",stringBuilder.toString() );
        }
        else if (type==2){
            stringBuilder.append(  random.nextInt(4)+1 );
            randomInfo.put( "flatUnit",stringBuilder.toString() );
        }
        else if (type==3){
            stringBuilder.append(  random.nextInt(30)+1 );
            String currentFloor = stringBuilder.toString();
            randomInfo.put( "currentFloor",currentFloor );
            stringBuilder.append(  0 );
            stringBuilder.append(  random.nextInt(3)+1 );
            randomInfo.put( "flatDoor",stringBuilder.toString() );
        }
        else {
            return null;
        }
        return randomInfo;
    }

    /**
     *  设置租期时间，传入开始时间根据Months自动计算结束时间，单独传入Months根据当前时间为开始时间计算结束时间或默认为12个月
     * @param leaseBO
     * @return
     * @throws ParseException
     */
    public static LeaseBO getStartAndEndDte(LeaseBO leaseBO) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isNotBlank(leaseBO.getStartDate())&&StringUtils.isNotBlank(String.valueOf( leaseBO.getMonths() ))) {
            Date date=getMonthDate(sdf.parse( leaseBO.getStartDate() ),leaseBO.getMonths());
            leaseBO.setEndDate( sdf.format(date) );
        }

        else if (leaseBO.getMonths()!=0){
            Date date=getMonthDate(sdf.parse(sdf.format( new Date()) ),leaseBO.getMonths());
            leaseBO.setStartDate( sdf.format( new Date()));
            leaseBO.setEndDate( sdf.format(date) );
        }
        else {
            Date date=getMonthDate(sdf.parse(sdf.format( new Date()) ),12);
            leaseBO.setStartDate( sdf.format( new Date()));
            leaseBO.setEndDate( sdf.format(date) );
        }
        return leaseBO;
    }


    /**
     * 获取startDate日期后month月的日期
     * @param startDate 开始日期
     * @param month  几个月后
     * @return
     */
    public static Date getMonthDate(Date startDate,int month){
        LocalDateTime localDateTime = startDate.toInstant()
                .atZone( ZoneId.systemDefault() )
                .toLocalDateTime().plusMonths(month).minusDays( 1 );
        Date date = Date.from(localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return date;
    }


    public static void main(String[] args) {
        System.out.println(randomInfo( 3 ));

    }




}
