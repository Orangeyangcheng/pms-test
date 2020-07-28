package api_test.base_test;

import api_test.house.HouseBO;
import api_test.uac.UserBO;
import api_test.uac.UserToken;
import common.BeautifyJson;
import common.DataSupport;
import common.HttpRequest;
import common.HttpUtil;
import mybatis.pojo.Community;
import mybatis.pojo.PmsStore;
import net.sf.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api_test.uac.UserToken.tmp1;
import static common.BeautifyJson.beautifyJson;
import static common.HttpConfig.applicationJson;

public class HouseTest {

    private static String access_token = "";

    private static String account = "";

    private static String saveHouse = "http://tpm1-gmd.mdguanjia.com/pms-hsc/house/inner/save";

    @BeforeClass(enabled = true)
    public static void getToken (){
        UserBO userBO = new UserBO();
        account = "13133373338";
        userBO.setPhone(account);
        userBO.setPwd("1234567");
        userBO.setEnv( tmp1 );
        access_token = UserToken.getToken(userBO);
    }






    public static JSONObject buildHouseParams(HouseBO houseBO){
        JSONObject houseParams = new JSONObject();
        houseParams.put( "cityId",houseBO.getCommunity().getCityId() );
        houseParams.put( "cityName",houseBO.getCommunity().getCityName() );
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
        houseParams.put( "communityAddress",houseBO.getCommunity().getAddress());
        houseParams.put( "communityId",houseBO.getCommunity().getId());
        houseParams.put( "communityName",houseBO.getCommunity().getCommunityName());
        houseParams.put( "currentFloor","1" );
        houseParams.put( "decorateLevel","3" );
        houseParams.put( "districtId",411281 );
        houseParams.put( "districtName","" );
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
        houseParams.put( "flatBuilding",houseBO.getFlatBuilding() );
        houseParams.put( "flatDoor",houseBO.getFlatDoor() );
        houseParams.put( "flatUnit",houseBO.getFlatUnit() );
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
        List tenancyCondList = new ArrayList();
        roomDesc.put( "tenancyCondList", tenancyCondList);
        houseParams.put( "roomDesc",roomDesc );
        //房源基础信息
        houseParams.put( "houseMode","1" );
        houseParams.put( "hallNum","1" );
        houseParams.put( "roomNum","3" );
        houseParams.put( "toiletNum","1" );
        houseParams.put( "kitchenNum","1" );
        houseParams.put( "roomFace","1" );
        //房间信息
        List rooms = new ArrayList();
        JSONObject roomInfo = new JSONObject();
        List roomFacilityItemList = new ArrayList();
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
        houseParams.put( "rooms",rooms );
        //门店信息
        houseParams.put( "storeId","741" );
        houseParams.put( "storeName","" );
        houseParams.put( "structure","1" );
        houseParams.put( "totalFloor","99" );


        return houseParams;
    }

    @Test
    public void saveHouse_新建整租房源(){
        //随机获取小区信息
        Community community = new Community();
        community = DataSupport.getCommunity();

        List<PmsStore> pmsStores = (List<PmsStore>) new PmsStore();
        pmsStores = DataSupport.queryPmsStore(1,2);


        HouseBO houseBO = new HouseBO();
        houseBO.setCommunity( community );
        houseBO.setHouseManagerId( 409 );
        houseBO.setHouseManagerName( "系统管理员" );
        houseBO.setHouseManagerTel( "13133373338" );
        houseBO.setFlatBuilding( "27" );
        houseBO.setFlatUnit( "2" );
        houseBO.setFlatDoor( "308" );
        JSONObject params = buildHouseParams( houseBO );
        System.out.println( beautifyJson(params) );

        Map<String,String> header = new HashMap<String, String>();
        header.put( "Authorization",access_token );
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setUrl(saveHouse);
        httpRequest.setHeader( header );
        httpRequest.setJsonObject( params );
        httpRequest.setContentType( applicationJson );

        String rep = HttpUtil.doPost(httpRequest);
        JSONObject repJson = JSONObject.fromObject( rep );
        System.out.println(beautifyJson(repJson));
    }

}
