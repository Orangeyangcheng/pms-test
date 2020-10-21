package api_test.base_test;


import api_test.lease.LeaseBO;
import api_test.uac.UserBO;
import com.sun.xml.internal.stream.events.NamedEvent;
import common.DataSupport;
import common.HttpRequest;
import common.HttpUtil;
import mybatis.pojo.HouseRoom;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static common.BeautifyJson.beautifyJson;
import static common.HttpConfig.applicationJson;

public class OrderTest {

    //创建租约
    private static String createLease = "http://tpm1-gmd.mdguanjia.com/pms-omc/xq/createLease";

    //新签初始化
    private static String xqInit = "http://tpm1-gmd.mdguanjia.com/pms-omc/xq/xqInit?houseType";

    static UserBO userBO;

    @BeforeClass
    private static boolean isCheck(){
        boolean isCheck = true;
        return isCheck;
    }

    private static JSONObject buildLeaseParams(LeaseBO leaseBO) throws ParseException {
        userBO = TestAccount.getToken();
        //根据当前账号查询该组织内的可租房源
        HouseRoom houseRoom = DataSupport.queryRentableRoomInfo( String.valueOf( userBO.getTenantId() ) );
        Assert.assertNotNull( houseRoom,"该组织没有可租房源" );
        System.out.println("========================"+"查询该组织可租房源信息"+"========================");
        System.out.println(houseRoom);
        JSONObject houseParam = new JSONObject();
        houseParam.put( "houseType",houseRoom.getHouseMode() );
        houseParam.put( "roomCode",houseRoom.getRoomCode() );

        Map<String,String> header = new HashMap<String, String>();
        header.put( "Authorization",userBO.getToken() );
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setUrl(xqInit);
        httpRequest.setHeader( header );
        httpRequest.setJsonObject( houseParam );
        httpRequest.setContentType( applicationJson );

        String rep = HttpUtil.doPost(httpRequest);
        System.out.println("========================"+"新签初始化信息"+"========================");
        JSONObject xqRepJson = JSONObject.fromObject( rep );
        System.out.println(beautifyJson(xqRepJson));

        JSONObject leaseParam = new JSONObject();
        leaseParam.put( "signType",leaseBO.getSignType());
        leaseParam.put( "orderType",1);
        leaseParam.put( "roomCode",houseRoom.getRoomCode());
        leaseParam.put( "houseCode",houseRoom.getHouseCode());
        leaseParam.put( "houseType",houseRoom.getHouseMode());

        JSONObject lessee = new JSONObject();
        lessee.put( "realName",leaseBO.getRealName() );
        lessee.put( "cardNo",leaseBO.getCardNo() );
        lessee.put( "mobile",leaseBO.getMobile() );
        lessee.put( "cardType",leaseBO.getCardType() );
        lessee.put( "sex",1 );
        lessee.put( "birthday","" );
        leaseParam.put( "lessee",lessee);

        if (StringUtils.isNotBlank(leaseBO.getEndDate())&&StringUtils.isNotBlank(leaseBO.getStartDate())){
            leaseParam.put( "endDate",leaseBO.getEndDate() );
            leaseParam.put( "startDate",leaseBO.getStartDate());

        }
        else if (StringUtils.isNotBlank(String.valueOf( leaseBO.getMonths() ))){
            leaseBO = getStartAndEndDte(leaseBO);
            leaseParam.put( "endDate",leaseBO.getEndDate() );
            leaseParam.put( "startDate",leaseBO.getStartDate());
        }
        else {
            leaseBO = getStartAndEndDte(leaseBO);
            leaseParam.put( "endDate",leaseBO.getEndDate() );
            leaseParam.put( "startDate",leaseBO.getStartDate());
        }

        leaseParam.put( "rentType",1 );
        leaseParam.put( "rentFee",leaseBO.getRentFee() );
        leaseParam.put( "rentFeeStr",leaseBO.getRentFee());
        leaseParam.put( "rentMonth",leaseBO.getRentMonth());
        leaseParam.put( "salesmanId",userBO.getUserId());
        leaseParam.put( "salesmanName",userBO.getUserName());
        leaseParam.put( "remark","自动化测试租约，编号:"+System.currentTimeMillis());
        leaseParam.put( "shackUpPersons",new ArrayList<String>() );

        List leaseDepositCosts = new ArrayList();
        JSONObject xqRepJsonData = xqRepJson.getJSONObject( "data" );
        List leaseDepositDicts = xqRepJsonData.getJSONArray( "leaseDepositDicts" );
        JSONObject leaseDepositDict = (JSONObject) leaseDepositDicts.get( 0 );

        JSONObject leaseDepositCost = new JSONObject();
        leaseDepositCost.put( "itemName", leaseDepositDict.getString( "dictItemName" ));
        leaseDepositCost.put( "itemCode", leaseDepositDict.getString( "dictItemValue" ));
        leaseDepositCost.put( "typeCode", leaseDepositDict.getString( "dictTypeCode" ));
        leaseDepositCost.put( "itemFeeStr", leaseBO.getDepositFee());
        leaseDepositCost.put( "rentDepositType", 1 );
        leaseDepositCost.put( "depositMonthNum", 1);
        leaseDepositCost.put( "isDefault", true);
        leaseDepositCosts.add( leaseDepositCost );

        leaseParam.put( "leaseDepositCosts",leaseDepositCosts);

        List leaseOtherCosts = new ArrayList();
        leaseParam.put( "leaseOtherCosts",leaseOtherCosts);
        List leaseIrregularRents = new ArrayList();
        leaseParam.put( "leaseIrregularRents",leaseIrregularRents);

        List leaseRenters = new ArrayList();
        JSONObject leaseRenter = new JSONObject();
        leaseRenter.put( "renterType",1 );
        leaseRenter.put( "cardNo",leaseBO.getCardNo() );
        leaseRenter.put( "cardType",1 );
        leaseRenter.put( "mobile",leaseBO.getMobile() );
        leaseRenter.put( "realName",leaseBO.getRealName() );
        leaseRenter.put( "birthday","" );
        leaseRenter.put( "sex",1 );
        List paperworkUrlList = new ArrayList();
        leaseRenter.put( "paperworkUrlList",paperworkUrlList );
        leaseRenters.add(leaseRenter);
        leaseParam.put( "leaseRenters",leaseRenters);

        List contractPicUrlList = new ArrayList();
        leaseParam.put( "contractPicUrlList",contractPicUrlList);

        leaseParam.put( "tenantSourceCode","1");
        leaseParam.put( "leaseIntentCode","84");
        leaseParam.put( "currentRentType","1");
        leaseParam.put( "roomPriceStr",xqRepJsonData.getString( "roomPriceStr" ));
        leaseParam.put( "defaultSignType",1);
        leaseParam.put( "defaultLeaseIntentDictCode","");
        List rentWayInits = xqRepJsonData.getJSONArray( "rentWayInits" );
        leaseParam.put( "rentWayInits",rentWayInits);
        JSONObject rentWay = (JSONObject) rentWayInits.get( 0 );
        leaseParam.put( "rentWay",rentWay.get( "rentWay" ) );
        leaseParam.put( "rentDay",rentWay.get( "rentDay" ) );
        leaseParam.put( "rentMonthPriceStr",xqRepJsonData.getString( "roomPriceStr" ));
        leaseParam.put( "rentMonthNum",xqRepJsonData.getString( "rentMonthNum" ));
        leaseParam.put( "contractTemplates",xqRepJsonData.getString( "contractTemplates" ));
        leaseParam.put( "parentLeaseDepositStr","");
        leaseParam.put( "checkinExpireFlag",0);
        leaseParam.put( "checkinExpireDay",null);
        leaseParam.put( "rentPlanFlag",0);
        leaseParam.put( "roomName",xqRepJsonData.getString( "roomName" ));
        leaseParam.put( "communityAddress",xqRepJsonData.getString( "communityAddress" ));
        leaseParam.put( "flatNoInfo",xqRepJsonData.getString( "flatNoInfo" ));
        System.out.println("========================"+"新签信息"+"========================");
        System.out.println(beautifyJson(leaseParam));
        return leaseParam;
    }

    @Test(groups = "orderTest",invocationCount = 1,enabled = true)
    public void saveLease_Test() throws ParseException {
        LeaseBO leaseBO = new LeaseBO();
        leaseBO.setRealName( "杨橙" );
        leaseBO.setCardNo( "41128119940809001X" );
        leaseBO.setMobile( "13175110032" );
        leaseBO.setCardType( 1 );
        leaseBO.setRentFee( "1000" );
        leaseBO.setDepositFee( "1000" );
        leaseBO.setSignType( 2 );
        leaseBO.setRentMonth( "1" );
//        设置租期
//        leaseBO.setStartDate( "2020-04-18" );
//        leaseBO.setEndDate( "2021-04-17" );
//        leaseBO.setMonths( 1 );
        JSONObject leaseParams = buildLeaseParams(leaseBO);

        Map<String,String> header = new HashMap<String, String>();
        header.put( "Authorization",userBO.getToken() );
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setUrl(createLease);
        httpRequest.setHeader( header );
        httpRequest.setJsonObject( leaseParams );
        httpRequest.setContentType( applicationJson );

        String rep = HttpUtil.doPost(httpRequest);
        System.out.println("========================"+"新签登记返回信息"+"========================");
        JSONObject xqRepJson = JSONObject.fromObject( rep );
        System.out.println(beautifyJson(xqRepJson));

        boolean i = isCheck();
        if (i==true){
            saveLeaseCheck( xqRepJson );
        }
    }

    public void saveLeaseCheck(JSONObject jsonObject){
        Assert.assertEquals( jsonObject.get( "code" ),200,"接口状态码校验失败" );
        Assert.assertEquals( jsonObject.get( "success" ),true,"接口返回失败" );
        JSONObject data =jsonObject.getJSONObject( "data" );
        Assert.assertNotNull( data.getString( "url" ),"签约地址为空");
        Assert.assertNotNull( data.getString( "orderNo" ),"租约编号为空");
    }


//    public static void main(String[] args) throws ParseException {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(df.format( new Date() ).toString());
//        Calendar cd = Calendar.getInstance();
//        cd.set(Calendar.MONTH,11);
//        System.out.println(cd);
//        LeaseBO leaseBO = new LeaseBO();

//        leaseBO.setStartDate( "2020-04-18" );
//        leaseBO.setMonths( 11 );
//
//        leaseBO = getStartAndEndDte( leaseBO );
//        System.out.println(leaseBO.getStartDate());
//        System.out.println(leaseBO.getEndDate());

//    }

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
}
