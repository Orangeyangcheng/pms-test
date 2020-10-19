package common;

import mybatis.dao.CommunityDao;
import mybatis.dao.PmsHouseDao;
import mybatis.dao.PmsTenantDao;
import mybatis.pojo.*;
import mybatis.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        PmsHouseDao pmsHouseDao = sqlSession.getMapper( PmsHouseDao.class );
        HouseRoom houseRoom = pmsHouseDao.queryRentableRoomInfo( tenantId );
        sqlSession.close();
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


    public static void main(String[] args) {
        System.out.println(randomInfo( 3 ));

    }




}
