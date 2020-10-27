package api_test.base_test;

import api_test.easte.EsateBO;
import api_test.uac.UserBO;
import common.DataSupport;
import common.EVOcontrol;
import common.HttpRequest;
import common.HttpUtil;
import mybatis.pojo.Community;
import mybatis.pojo.PmsStore;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static api_test.uac.PmsTenantOperation.addStore;
import static common.BeautifyJson.beautifyJson;
import static common.HttpConfig.applicationJson;

public class EasteTest extends EVOcontrol {
    //定义测试环境
    public static String testEvo = tpm1;

    public static JSONObject buildEsateParams(EsateBO esateBo) {
        JSONObject esateParams = new JSONObject();
        //公寓信息
        List facilityItemListe=new ArrayList();
        facilityItemListe.add("1");
        facilityItemListe.add("2");
        facilityItemListe.add("3");
        facilityItemListe.add("4");
        facilityItemListe.add("5");
        facilityItemListe.add("6");
        facilityItemListe.add("8");
        facilityItemListe.add("9");
        //公寓图片
        List pics = new ArrayList();
        JSONObject commonPics0 = new JSONObject();
        commonPics0.put( "picUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/2020617103948219013.jpg");
        commonPics0.put( "originalUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/2020617103948219013.jpg");
        commonPics0.put( "picTag","");
        commonPics0.put( "picName","2020617103948219013.jpg");

        JSONObject commonPics1 = new JSONObject();
        commonPics1.put( "picUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/2020617103949505584.jpg");
        commonPics1.put( "originalUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/2020617103949505584.jpg");
        commonPics1.put( "picTag","");
        commonPics1.put( "picName","2020617103949505584.jpg");

        JSONObject commonPics2 = new JSONObject();
        commonPics2.put( "picUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/2020617103950753032.jpg");
        commonPics2.put( "originalUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/2020617103950753032.jpg");
        commonPics2.put( "picTag","");
        commonPics2.put( "picName","2020617103950753032.jpg");

        pics.add( commonPics0 );
        pics.add( commonPics1 );
        pics.add( commonPics2 );
        //楼层信息
        List floorsList=new ArrayList();
        JSONObject floors0= new JSONObject();
        floors0.put( "floorSeq",1);
        floors0.put( "hasExclude",0 );
        floors0.put( "roomNum","5");

        floors0.put( "rooms",greateRooms(floors0));
        JSONObject floors1= new JSONObject();
        floors1.put( "floorSeq",2 );
        floors1.put( "hasExclude",0 );
        floors1.put( "roomNum","5" );

        floors1.put( "rooms",greateRooms(floors1));
        JSONObject floors2= new JSONObject();
        floors2.put( "floorSeq",3 );
        floors2.put( "hasExclude",0 );
        floors2.put( "roomNum","5");

        floors2.put( "rooms",greateRooms(floors2));

        floorsList.add( floors0 );
        floorsList.add( floors1 );
        floorsList.add( floors2 );
        //房型
        List layoutsList=new ArrayList();
        //房型信息
        //房型A
        JSONObject layouts0=new JSONObject();
        JSONObject layouts0Desc =new JSONObject();
        layouts0Desc.put( "roomDesc" ,"测试房源房型A");
        List tenancyCondList=new ArrayList();
        tenancyCondList.add( "6" );
        tenancyCondList.add( "9" );
        tenancyCondList.add( "8" );
        layouts0Desc.put( "tenancyCondList" ,tenancyCondList);
        layouts0.put( "desc",layouts0Desc);

        List facilityItemList = new ArrayList();
        facilityItemList.add( "1" );
        facilityItemList.add( "2" );
        facilityItemList.add( "3" );
        facilityItemList.add( "4" );
        facilityItemList.add( "5" );
        facilityItemList.add( "6" );
        facilityItemList.add( "7" );
        facilityItemList.add( "8" );
        facilityItemList.add( "9" );
        layouts0.put( "facilityItemList",facilityItemList );
        List floors=new ArrayList();
        JSONObject floorsa=new JSONObject();
        floorsa.put( "floorSeq",1);
        List roomsLista=new ArrayList();
        JSONObject rooms0=new JSONObject();
        rooms0.put( "layoutName","" );
        rooms0.put( "roomName",101 );
        rooms0.put( "roomNo",101);
        JSONObject rooms1=new JSONObject();
        rooms1.put( "layoutName","" );
        rooms1.put( "roomName",102 );
        rooms1.put( "roomNo",102 );
        JSONObject rooms2=new JSONObject();
        rooms2.put( "layoutName","" );
        rooms2.put( "roomName",103 );
        rooms2.put( "roomNo",103);
        JSONObject rooms3=new JSONObject();
        rooms3.put( "layoutName","" );
        rooms3.put( "roomName",104 );
        rooms3.put( "roomNo",104 );
        JSONObject rooms4=new JSONObject();
        rooms4.put( "layoutName","" );
        rooms4.put( "roomName",105 );
        rooms4.put( "roomNo",105 );
        roomsLista.add( rooms0);
        roomsLista.add( rooms1);
        roomsLista.add( rooms2);
        roomsLista.add( rooms3);
        roomsLista.add( rooms4);
        floorsa.put( "rooms",roomsLista ) ;
        List roomsListb=new ArrayList();

        JSONObject floorsb=new JSONObject();
        floorsb.put( "floorSeq",2 );
        JSONObject rooms6=new JSONObject();
        rooms6.put( "layoutName","" );
        rooms6.put( "roomName",201 );
        rooms6.put( "roomNo",201 );
        JSONObject rooms7=new JSONObject();
        rooms7.put( "layoutName","" );
        rooms7.put( "roomName",202 );
        rooms7.put( "roomNo",202 );
        JSONObject rooms8=new JSONObject();
        rooms8.put( "layoutName","" );
        rooms8.put( "roomName",203 );
        rooms8.put( "roomNo",203 );
        JSONObject rooms9=new JSONObject();
        rooms9.put( "layoutName","" );
        rooms9.put( "roomName",204 );
        rooms9.put( "roomNo",204 );
        JSONObject rooms10=new JSONObject();
        rooms10.put( "layoutName","" );
        rooms10.put( "roomName",205 );
        rooms10.put( "roomNo",205 );
        roomsListb.add( rooms6);
        roomsListb.add( rooms7);
        roomsListb.add( rooms8);
        roomsListb.add( rooms9);
        roomsListb.add( rooms10);
        floorsb.put( "rooms",roomsListb );
        floors.add( floorsa );
        floors.add( floorsb );

        List layouts0RoomItemTagList=new ArrayList();
        layouts0RoomItemTagList.add( "1" );
        layouts0RoomItemTagList.add( "2" );
        layouts0RoomItemTagList.add( "3" );
        layouts0RoomItemTagList.add( "4" );
        layouts0RoomItemTagList.add( "5" );
        List layoutsPics = new ArrayList();
        JSONObject layoutsPicsa1 = new JSONObject();
        layoutsPicsa1.put( "picUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/2020617103949505584.jpg");
        layoutsPicsa1.put( "originalUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/2020617103949505584.jpg");
        layoutsPicsa1.put( "picTag","");
        layoutsPicsa1.put( "picName","2020617103949505584.jpg");

        JSONObject layoutsPicsa2 = new JSONObject();
        layoutsPicsa2.put( "picUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/20201021152126269227.jpg");
        layoutsPicsa2.put( "originalUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/20201021152126269227.jpg");
        layoutsPicsa2.put( "picTag","");
        layoutsPicsa2.put( "picName","20201021152126269227.jpg");
        layoutsPics.add(layoutsPicsa1);
        layoutsPics.add( layoutsPicsa2);
        layouts0.put( "hallNum" ,"1");
        layouts0.put( "kitchenNum","1" );
        layouts0.put( "layoutArea","85" );
        layouts0.put( "layoutFace","2" );
        layouts0.put( "layoutName","房型A" );
        layouts0.put( "pics",layoutsPics);
        layouts0.put( "floors",floors );
        layouts0.put( "roomItemTagList",layouts0RoomItemTagList );
        layouts0.put( "roomNum","2");
        layouts0.put( "toiletNum","1");
        layouts0.put( "houseMode",esateBo.getHouseMode());



        //房型B
        JSONObject layouts1=new JSONObject();
        JSONObject layouts1Desc =new JSONObject();
        layouts1Desc.put( "roomDesc" ,"测试房源房型B");
        List tenancyCondList1=new ArrayList();
        tenancyCondList1.add( "6" );
        tenancyCondList1.add( "9" );
        tenancyCondList1.add( "8" );
        layouts1Desc.put( "tenancyCondList" ,tenancyCondList);

        List facilityItemList1 = new ArrayList();
        facilityItemList1.add( "1" );
        facilityItemList1.add( "2" );
        facilityItemList1.add( "3" );
        facilityItemList1.add( "4" );
        facilityItemList1.add( "5" );
        facilityItemList1.add( "6" );
        facilityItemList1.add( "7" );
        facilityItemList1.add( "8" );
        facilityItemList1.add( "9" );
        List floors11=new ArrayList();
        JSONObject floorsa1=new JSONObject();
        floorsa1.put( "floorSeq",3);
        List roomsLista1=new ArrayList();
        JSONObject rooms01=new JSONObject();
        rooms01.put( "layoutName","" );
        rooms01.put( "roomName",301 );
        rooms01.put( "roomNo",301 );
        JSONObject rooms11=new JSONObject();
        rooms11.put( "layoutName","" );
        rooms11.put( "roomName",302 );
        rooms11.put( "roomNo",302 );
        JSONObject rooms21=new JSONObject();
        rooms21.put( "layoutName","" );
        rooms21.put( "roomName",303 );
        rooms21.put( "roomNo",303 );
        JSONObject rooms31=new JSONObject();
        rooms31.put( "layoutName","" );
        rooms31.put( "roomName",304 );
        rooms31.put( "roomNo",304 );
        JSONObject rooms41=new JSONObject();
        rooms41.put( "layoutName","" );
        rooms41.put( "roomName",305 );
        rooms41.put( "roomNo",305 );
        roomsLista1.add( rooms01);
        roomsLista1.add( rooms11);
        roomsLista1.add( rooms21);
        roomsLista1.add( rooms31);
        roomsLista1.add( rooms41);
        floorsa1.put( "rooms",roomsLista1 ) ;
        floors11.add( floorsa1);
        List layouts1RoomItemTagList=new ArrayList();
        layouts1RoomItemTagList.add( "1" );
        layouts1RoomItemTagList.add( "2" );
        layouts1RoomItemTagList.add( "3" );
        layouts1RoomItemTagList.add( "4" );
        layouts1RoomItemTagList.add( "5" );
        List layoutsPicsb = new ArrayList();
        JSONObject layoutsPicsb2 = new JSONObject();
        layoutsPicsb2.put( "picUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/20201021152727944541.jpg");
        layoutsPicsb2.put( "originalUrl","http://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/20201021152727944541.jpg");
        layoutsPicsb2.put( "picTag","");
        layoutsPicsb2.put( "picName","20201021152727944541.jpg");
        layoutsPicsb.add(layoutsPicsb2);
        layoutsList.add( layouts0 );
        layoutsList.add( layouts1 );
        layouts1.put( "desc",layouts1Desc);
        layouts1.put( "facilityItemList",facilityItemList1 );
        layouts1.put( "hallNum" ,"1");
        layouts1.put( "kitchenNum","1" );
        layouts1.put( "layoutArea","85" );
        layouts1.put( "layoutCode","" );
        layouts1.put( "layoutFace","1" );
        layouts1.put( "layoutName","房型B" );
        layouts1.put("pics",layoutsPicsb);
        layouts1.put( "floors",floors11 );
        layouts1.put( "roomItemTagList",layouts1RoomItemTagList );
        layouts1.put( "roomNum","2");
        layouts1.put( "toiletNum","1");
        layouts1.put( "houseMode",esateBo.getHouseMode());

        esateParams.put( "buildingNo","3" );
        esateParams.put( "cityId",esateBo.getCommunity().getCityId() );
        esateParams.put( "communityAddress",esateBo.getCommunity().getAddress());
        esateParams.put( "communityName",esateBo.getCommunity().getCommunityName());
        esateParams.put( "districtId",esateBo.getCommunity().getAreaId() );
        esateParams.put( "estateName",esateBo.getCommunity().getCommunityName() );

        esateParams.put("facilityItemList",facilityItemListe);
        esateParams.put( "floorRoomNum","5" );
        esateParams.put( "floors",floorsList );
        esateParams.put( "latitude",esateBo.getCommunity().getLatitude() );
        esateParams.put( "longitude",esateBo.getCommunity().getLongitude() );
        esateParams.put( "layouts",layoutsList );
        esateParams.put( "pics", pics);
        esateParams.put( "roomNamePrefix" ,"");
        esateParams.put( "sameNum","" );
        esateParams.put( "totalFloor","3" );
        esateParams.put( "storeId",String.valueOf( esateBo.getStoreId() ) );
        esateParams.put( "houseMode",esateBo.getHouseMode());
        esateParams.put( "cityName",esateBo.getCommunity().getCityName() );
        System.out.println( esateParams );
        return  esateParams;
    }

    public static List greateRooms(JSONObject floors0){
        List roomList=new ArrayList();
        int floorSeq= (int) floors0.get( "floorSeq" );
        int hasExclude=(int)floors0.get("hasExclude");
        for (int i=0; i<5; i++){
            JSONObject floors0rooms= new JSONObject();

            String roomNo=String.valueOf(floorSeq) +String.valueOf(hasExclude)+String.valueOf(i);
            floors0rooms.put( "roomNo",Integer.parseInt(roomNo));
            floors0rooms.put( "layoutName","");
            floors0rooms.put( "roomName",Integer.parseInt(roomNo));
            roomList.add( floors0rooms);
        }
        return  roomList;
    }

    @BeforeClass
    private static boolean isCheck(){
        boolean isCheck = true;
        return isCheck;
    }
    @Test(invocationCount = 1,groups = "HouseMode=3",threadPoolSize = 1)
    public void saveEsate_Test(){
        UserBO userBO = TestAccount.getToken(testEvo);
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

        EsateBO esateBO = new EsateBO();
        esateBO.setCommunity( community );
        esateBO.setHouseManagerId( userBO.getUserId() );
        esateBO.setHouseManagerName( userBO.getUserName() );
        esateBO.setHouseManagerTel( userBO.getPhone() );
        esateBO.setStoreId( pmsStore.getId() );
        esateBO.setStoreName( pmsStore.getDeptName() );
        esateBO.setHouseMode( "3" );
        JSONObject esateParams = buildEsateParams( esateBO );


        System.out.println("========================"+"创建房源信息"+"========================");
        System.out.println( beautifyJson(esateParams) );

        Map<String,String> header = new HashMap<String, String>();
        header.put( "Authorization",userBO.getToken() );
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setUrl(getAddress( testEvo,saveEsate ));
        httpRequest.setHeader( header );
        httpRequest.setJsonObject( esateParams );
        httpRequest.setContentType( applicationJson );

        String rep = HttpUtil.doPost(httpRequest);
        JSONObject repJson = JSONObject.fromObject( rep );
        System.out.println("========================"+"创建房源结果"+"========================");
        System.out.println(beautifyJson(repJson));

        boolean i = isCheck();
        if (i==true){
            saveEsateCheck( repJson );
        }
    }
    /**
     * 房源接口校验通用方法
     * @param jsonObject
     * @return
     */
    public boolean saveEsateCheck(JSONObject jsonObject){
        Assert.assertEquals( jsonObject.getInt( "code" ),200,"接口状态码校验失败" );
        Assert.assertEquals( jsonObject.getBoolean( "success" ),true,"接口返回失败" );
        return true;
    }

}
