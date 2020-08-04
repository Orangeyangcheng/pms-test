package api_test.base_test;

import api_test.house.HouseBO;
import api_test.uac.UserBO;
import api_test.uac.UserToken;
import com.mysql.cj.log.NullLogger;
import common.BeautifyJson;
import common.DataSupport;
import common.HttpRequest;
import common.HttpUtil;
import mybatis.pojo.*;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api_test.uac.PmsTenantOperation.addStore;
import static api_test.uac.UserToken.tmp1;
import static common.BeautifyJson.beautifyJson;
import static common.HttpConfig.applicationJson;

public class HouseTest {

    private static String access_token = "";



    private static String saveHouse = "http://tpm1-gmd.mdguanjia.com/pms-hsc/house/inner/save";

    private static String roomRent = "http://tpm1-gmd.mdguanjia.com/pms-hsc/client/room/rent";


    public static UserBO getToken (){
        UserBO userBO = new UserBO();
        userBO.setPhone("13133373338");
        userBO.setPwd("1234567");
        userBO.setEnv( tmp1 );
        PmsUser pmsUser = DataSupport.queryUserInfoByPhone( userBO.getPhone() );
        access_token = UserToken.getToken(userBO);
        userBO.setToken( access_token );
        PmsTenant pmsTenant = DataSupport.queryTenantInfoByAdminPhone(userBO.getPhone());
        userBO.setUserName( pmsUser.getName() );
        userBO.setUserId(  pmsUser.getId()  );
        userBO.setTenantId( pmsTenant.getTenantId() );
        return userBO;
    }




    public static JSONObject buildHouseParams(HouseBO houseBO){
        JSONObject houseParams = new JSONObject();
        //图片列表
        List commonPics = new ArrayList();
        houseParams.put( "commonPics",commonPics );
        JSONObject commonPics0 = new JSONObject();
        commonPics0.put( "picUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/2020617103948219013.jpg");
        commonPics0.put( "originalUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/2020617103948219013.jpg");
        commonPics0.put( "picTag",null);
        commonPics0.put( "picName","2020617103948219013.jpg");

        JSONObject commonPics1 = new JSONObject();
        commonPics1.put( "picUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/2020617103949505584.jpg");
        commonPics1.put( "originalUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/2020617103949505584.jpg");
        commonPics1.put( "picTag",null);
        commonPics1.put( "picName","2020617103949505584.jpg");

        JSONObject commonPics2 = new JSONObject();
        commonPics2.put( "picUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/2020617103950753032.jpg");
        commonPics2.put( "originalUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/2020617103950753032.jpg");
        commonPics2.put( "picTag",null);
        commonPics2.put( "picName","2020617103950753032.jpg");

        commonPics.add( commonPics0 );
        commonPics.add( commonPics1 );
        commonPics.add( commonPics2 );

        houseParams.put( "commonPics", commonPics);

        //地址信息
        houseParams.put( "cityId",houseBO.getCommunity().getCityId() );
        houseParams.put( "cityName",houseBO.getCommunity().getCityName() );
        houseParams.put( "communityAddress",houseBO.getCommunity().getAddress());
        houseParams.put( "communityId",houseBO.getCommunity().getId());
        houseParams.put( "communityName",houseBO.getCommunity().getCommunityName());
        Map a = DataSupport.randomInfo( 3 );
        houseParams.put( "currentFloor",a.get( "currentFloor" ) );
        houseParams.put( "decorateLevel","3" );
        houseParams.put( "districtId",houseBO.getCommunity().getAreaId() );
        houseParams.put( "districtName",houseBO.getCommunity().getAreaName() );
        houseParams.put( "latitude",houseBO.getCommunity().getLatitude() );
        houseParams.put( "longitude",houseBO.getCommunity().getLongitude() );

        //房屋设施
        List facilityItemList = new ArrayList();
        facilityItemList.add( "1" );
        facilityItemList.add( "2" );
        facilityItemList.add( "3" );
        houseParams.put( "facilityItemList",facilityItemList );
        //房间设施
        List roomItemTagList = new ArrayList();
        roomItemTagList.add( "1" );
        roomItemTagList.add( "2" );
        houseParams.put( "roomItemTagList",roomItemTagList );
        //楼幢单元
        houseParams.put( "flatBuilding",DataSupport.randomInfo( 1 ).get( "flatBuilding" ) );
        houseParams.put( "flatDoor",a.get( "flatDoor" ) );
        houseParams.put( "flatUnit",DataSupport.randomInfo( 2 ).get( "flatUnit" ) );
        houseParams.put( "houseArea","89" );
        houseParams.put( "houseCode","" );
        //看房管家
        JSONObject houseManager = new JSONObject();
        houseManager.put( "houseManagerId",houseBO.getHouseManagerId() );
        houseManager.put( "houseManagerName",houseBO.getHouseManagerName() );
        houseManager.put( "houseManagerTel",houseBO.getHouseManagerTel() );
        houseParams.put( "houseManager",houseManager );
        //房源描述
        JSONObject roomDesc = new JSONObject();
        roomDesc.put( "roomDesc","自动化脚本测试房源,编号："+System.currentTimeMillis() );
//        roomDesc.put( "roomDesc","测试 测试房源" );
        List tenancyCondList = new ArrayList();
        roomDesc.put( "tenancyCondList", tenancyCondList);
        houseParams.put( "roomDesc",roomDesc );
        //房源基础信息
        houseParams.put( "houseMode",houseBO.getHouseMode() );
        houseParams.put( "hallNum","1" );
        houseParams.put( "roomNum","3" );
        houseParams.put( "toiletNum","1" );
        houseParams.put( "kitchenNum","1" );
        houseParams.put( "roomFace","1" );
        //房间信息
        List rooms = new ArrayList();
        if (houseBO.getHouseMode()==1){
            JSONObject roomInfo = new JSONObject();
            List roomFacilityItemList = new ArrayList();
            roomFacilityItemList.add( "1" );
            roomFacilityItemList.add( "2" );
            roomFacilityItemList.add( "3" );
            roomFacilityItemList.add( "4" );
            roomFacilityItemList.add( "5" );
            List pics = new ArrayList();
            roomInfo.put( "facilityItemList",roomFacilityItemList);
            roomInfo.put( "pics",pics);
            //房间交租方式
            List rentTypes = new ArrayList();
            JSONObject rentTypesInfo = new JSONObject();
            rentTypesInfo.put( "depositMonthNum", "1");
            rentTypesInfo.put( "depositPrice", "9980.00");
            List feeTypes = new ArrayList();
            rentTypesInfo.put( "feeTypes", feeTypes);
            rentTypesInfo.put( "houseType", "");
            rentTypesInfo.put( "rentMonthNum", "1");
            rentTypesInfo.put( "rentMonthPrice", "9980");
            rentTypes.add( rentTypesInfo );
            roomInfo.put( "rentTypes", rentTypes);


            roomInfo.put( "roomArea","");
            JSONObject roomsDesc = new JSONObject();
            roomsDesc.put( "roomDesc","" );
            List roomTenancyCondList = new ArrayList();
            roomsDesc.put( "tenancyCondList",roomTenancyCondList );

            roomInfo.put( "roomsDesc",roomsDesc);
            roomInfo.put( "roomFace","");
            List roomItemTagLists = new ArrayList();
            roomInfo.put( "roomItemTagList",roomItemTagLists);
            roomInfo.put( "roomRentStatus",1);
            roomInfo.put( "roomType","");
            roomInfo.put( "houseMode","1");
            roomInfo.put( "roomName","A");
            rooms.add( roomInfo );
        }

        else {
            //房间A
            JSONObject roomInfoA = new JSONObject();
            List roomFacilityItemListA = new ArrayList();
            roomFacilityItemListA.add( "1" );
            roomFacilityItemListA.add( "2" );
            roomFacilityItemListA.add( "3" );
            roomFacilityItemListA.add( "4" );
            roomFacilityItemListA.add( "5" );

            //图片信息
            List picsA = new ArrayList();
            JSONObject picsInfoA = new JSONObject();
            picsInfoA.put( "picUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/202084112237871157.jpg" );
            picsInfoA.put( "originalUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/202084112237871157.jpg" );
            picsInfoA.put( "picTag", null );
            picsInfoA.put( "picName","202084112237871157.jpg" );

            picsA.add( picsInfoA );
            roomInfoA.put( "facilityItemList",roomFacilityItemListA);
            roomInfoA.put( "pics",picsA);
            //房间交租方式
            List rentTypesA = new ArrayList();
            JSONObject rentTypesInfoA = new JSONObject();
            rentTypesInfoA.put( "depositMonthNum", "1");
            rentTypesInfoA.put( "depositPrice", "9980.00");
            List feeTypesA = new ArrayList();
            rentTypesInfoA.put( "feeTypes", feeTypesA);
            rentTypesInfoA.put( "houseType", "");
            rentTypesInfoA.put( "rentMonthNum", "1");
            rentTypesInfoA.put( "rentMonthPrice", "9980");
            rentTypesA.add( rentTypesInfoA );
            roomInfoA.put( "rentTypes", rentTypesA);


            JSONObject roomDescA = new JSONObject();
            roomDescA.put( "roomDesc","自动化脚本测试房间,A编号："+System.currentTimeMillis() );
            List roomTenancyCondListA = new ArrayList();
            roomDescA.put( "tenancyCondList",roomTenancyCondListA );

            roomInfoA.put( "roomDesc",roomDescA);
            roomInfoA.put( "roomFace","1");
            List roomItemTagListsA = new ArrayList();
            roomItemTagListsA.add( "1" );
            roomItemTagListsA.add( "2" );
            roomItemTagListsA.add( "3" );

            roomInfoA.put( "roomItemTagList",roomItemTagListsA);
            roomInfoA.put( "roomRentStatus",1);
            roomInfoA.put( "roomType","");
            roomInfoA.put( "houseMode",houseBO.getHouseMode());
            roomInfoA.put( "roomName","A");
            roomInfoA.put( "roomType","1");
            roomInfoA.put( "roomArea","23");



            //房间B
            JSONObject roomInfoB = new JSONObject();
            List roomFacilityItemListB = new ArrayList();
            roomFacilityItemListB.add( "1" );
            roomFacilityItemListB.add( "2" );
            roomFacilityItemListB.add( "3" );
            roomFacilityItemListB.add( "4" );
            roomFacilityItemListB.add( "5" );

            //图片信息
            List picsB = new ArrayList();
            JSONObject picsInfoB = new JSONObject();
            picsInfoB.put( "picUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/202084112321155430.jpg" );
            picsInfoB.put( "originalUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/202084112321155430.jpg" );
            picsInfoB.put( "picTag", null );
            picsInfoB.put( "picName","202084112321155430.jpg" );

            picsB.add( picsInfoB );

            roomInfoB.put( "facilityItemList",roomFacilityItemListB);
            roomInfoB.put( "pics",picsB);
            //房间交租方式
            List rentTypesB = new ArrayList();
            JSONObject rentTypesInfoB = new JSONObject();
            rentTypesInfoB.put( "depositMonthNum", "1");
            rentTypesInfoB.put( "depositPrice", "9981.00");
            List feeTypesB = new ArrayList();
            rentTypesInfoB.put( "feeTypes", feeTypesB);
            rentTypesInfoB.put( "houseType", "");
            rentTypesInfoB.put( "rentMonthNum", "1");
            rentTypesInfoB.put( "rentMonthPrice", "9981");
            rentTypesB.add( rentTypesInfoB );
            roomInfoB.put( "rentTypes", rentTypesB);


            JSONObject roomDescB = new JSONObject();
            roomDescB.put( "roomDesc","自动化脚本测试房间,B编号："+System.currentTimeMillis() );
            List roomTenancyCondListB = new ArrayList();
            roomDescB.put( "tenancyCondList",roomTenancyCondListB );

            roomInfoB.put( "roomDesc",roomDescB);
            roomInfoB.put( "roomFace","1");
            List roomItemTagListsB = new ArrayList();
            roomItemTagListsB.add( "1" );
            roomItemTagListsB.add( "2" );
            roomItemTagListsB.add( "3" );

            roomInfoB.put( "roomItemTagList",roomItemTagListsB);
            roomInfoB.put( "roomRentStatus",1);
            roomInfoB.put( "roomType","");
            roomInfoB.put( "houseMode",houseBO.getHouseMode());
            roomInfoB.put( "roomName","B");
            roomInfoB.put( "roomType","2");
            roomInfoB.put( "roomArea","18");



            rooms.add( roomInfoA );
            rooms.add( roomInfoB );
        }

        houseParams.put( "rooms",rooms );
        //门店信息
        houseParams.put( "storeId",houseBO.getStoreId() );
        houseParams.put( "storeName",houseBO.getStoreName() );
        houseParams.put( "structure","1" );
        houseParams.put( "totalFloor",(Integer.valueOf( a.get( "currentFloor" ).toString() )+5)+"" );


        return houseParams;
    }

    /**
     * 整租房源
     */
    @Test(invocationCount = 1,groups = "HouseMode=1")
    public void saveHouse_Test(){
        UserBO userBO = getToken();
        //随机获取小区信息
        Community community;
        community = DataSupport.getCommunity();
        System.out.println("========================"+"随机获取小区信息"+"========================");
        System.out.println( community );
        PmsStore pmsStore;
        List<PmsStore> pmsStores;
        pmsStores = DataSupport.queryPmsStore(userBO.getTenantId(),community.getCityId().intValue());
        //根据小区信息查询是否存在门店
        if (pmsStores.size()>0){
            pmsStore =  pmsStores.get(0);
        }
        //没有门店就新增
        else {
            pmsStore = addStore( community,userBO.getToken(),userBO.getTenantId() );
        }

        HouseBO houseBO = new HouseBO();
        houseBO.setCommunity( community );
        houseBO.setHouseManagerId( userBO.getUserId() );
        houseBO.setHouseManagerName( userBO.getUserName() );
        houseBO.setHouseManagerTel( userBO.getPhone() );
        houseBO.setStoreId( pmsStore.getId() );
        houseBO.setStoreName( pmsStore.getDeptName() );
        houseBO.setHouseMode( 1 );//1-整租,2-合租
        JSONObject params = buildHouseParams( houseBO );
        System.out.println("========================"+"创建房源信息"+"========================");
        System.out.println( beautifyJson(params) );

        Map<String,String> header = new HashMap<String, String>();
        header.put( "Authorization",userBO.getToken() );
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setUrl(saveHouse);
        httpRequest.setHeader( header );
        httpRequest.setJsonObject( params );
        httpRequest.setContentType( applicationJson );

        String rep = HttpUtil.doPost(httpRequest);
        JSONObject repJson = JSONObject.fromObject( rep );
        System.out.println("========================"+"创建房源结果"+"========================");
        System.out.println(beautifyJson(repJson));
    }

    /**
     * 合租房源
     */
    @Test(invocationCount = 1,groups = "HouseMode=2")
    public void saveHouseRoom_Test(){
        UserBO userBO = getToken();
        //随机获取小区信息
        Community community;
        community = DataSupport.getCommunity();
        System.out.println("========================"+"随机获取小区信息"+"========================");
        System.out.println( community );
        PmsStore pmsStore;
        List<PmsStore> pmsStores;
        pmsStores = DataSupport.queryPmsStore(userBO.getTenantId(),community.getCityId().intValue());
        //根据小区信息查询是否存在门店
        if (pmsStores.size()>0){
            pmsStore =  pmsStores.get(0);
        }
        //没有门店就新增
        else {
            pmsStore = addStore( community,userBO.getToken(),userBO.getTenantId() );
        }

        HouseBO houseBO = new HouseBO();
        houseBO.setCommunity( community );
        houseBO.setHouseManagerId( userBO.getUserId() );
        houseBO.setHouseManagerName( userBO.getUserName() );
        houseBO.setHouseManagerTel( userBO.getPhone() );
        houseBO.setStoreId( pmsStore.getId() );
        houseBO.setStoreName( pmsStore.getDeptName() );
        houseBO.setHouseMode( 1 );//1-整租,2-合租
        JSONObject params = buildHouseParams( houseBO );
        System.out.println("========================"+"创建房源信息"+"========================");
        System.out.println( beautifyJson(params) );

        Map<String,String> header = new HashMap<String, String>();
        header.put( "Authorization",userBO.getToken() );
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setUrl(saveHouse);
        httpRequest.setHeader( header );
        httpRequest.setJsonObject( params );
        httpRequest.setContentType( applicationJson );

        String rep = HttpUtil.doPost(httpRequest);
        JSONObject repJson = JSONObject.fromObject( rep );
        System.out.println("========================"+"创建房源结果"+"========================");
        System.out.println(beautifyJson(repJson));
    }

    @Test
    public void roomRent_Test(){
        UserBO userBO = getToken();
        JSONObject roomRentParams = new JSONObject();
        roomRentParams.put( "houseType",2 );
        roomRentParams.put( "roomCode","d0005943" );
        roomRentParams.put( "operateType",1 );
        roomRentParams.put( "leaseType",1 );
        roomRentParams.put( "rentBeginTime","2020-07-30 17:02:18" );
        roomRentParams.put( "rentEndTime","2021-07-30 17:02:18" );
        roomRentParams.put( "rentName","杨橙" );

        int checkIn=1;
        int checkInOut=1;
        for (int i=0;i<=100;i++){

            HouseRoom houseRoom = DataSupport.queryRoomInfo( roomRentParams.getString( "roomCode" ) );

            if (houseRoom.getRoomStatus()==0){

                roomRentParams.put( "operateType",1 );
                System.out.println(roomRentParams);
                Map<String,String> header = new HashMap();
                header.put( "Authorization",userBO.getToken() );
                HttpRequest httpRequest = new HttpRequest();
                httpRequest.setUrl(roomRent);
                httpRequest.setHeader( header );
                httpRequest.setJsonObject( roomRentParams );
                httpRequest.setContentType( applicationJson );

                String rep = HttpUtil.doPost(httpRequest);
                JSONObject repJson = JSONObject.fromObject( rep );
                System.out.println("========================"+"第"+checkIn+"次"+"登记入住"+"========================");
                System.out.println(beautifyJson(repJson));

                HouseRoom houseRoom1 = DataSupport.queryRoomInfo( roomRentParams.getString( "roomCode" ) );
                if (houseRoom1.getRoomStatus()!=1){
                    System.out.println("========================"+"第"+checkIn+"次"+"登记入住结果异常"+"========================");
                }
                Assert.assertTrue( houseRoom1.getRoomStatus()==1 ,"房源入住失败");
                checkIn++;
            }

            else if(houseRoom.getRoomStatus()==1){

                roomRentParams.put( "operateType", 4);
                roomRentParams.put( "checkOutTime","2020-07-30 18:02:18" );
                System.out.println(roomRentParams);
                Map<String,String> header = new HashMap();
                header.put( "Authorization",userBO.getToken() );
                HttpRequest httpRequest = new HttpRequest();
                httpRequest.setUrl(roomRent);
                httpRequest.setHeader( header );
                httpRequest.setJsonObject( roomRentParams );
                httpRequest.setContentType( applicationJson );

                String rep = HttpUtil.doPost(httpRequest);
                JSONObject repJson = JSONObject.fromObject( rep );
                System.out.println("========================"+"第"+checkInOut+"次"+"退房"+"========================");
                System.out.println(beautifyJson(repJson));

                HouseRoom houseRoom2 = DataSupport.queryRoomInfo( roomRentParams.getString( "roomCode" ) );
                if (houseRoom2.getRoomStatus()!=0){
                    System.out.println("========================"+"第"+checkInOut+"次"+"退房结果异常"+"========================");
                }
                Assert.assertTrue( houseRoom2.getRoomStatus()==0 ,"房源退租失败");
                checkInOut++;

            }
        }
    }
}
