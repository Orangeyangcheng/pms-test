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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static common.BeautifyJson.beautifyJson;
import static common.HttpConfig.applicationJson;

public class OrderTest {

    //创建租约
    private static String createLease = "http://tpm1-gmd.mdguanjia.com/pms-omc/xq/createLease";

    //新签初始化
    private static String xqInit = "http://tpm1-gmd.mdguanjia.com/pms-omc/xq/xqInit?houseType";


    private static JSONObject buildLeaseParams(LeaseBO leaseBO){
        UserBO userBO = TestAccount.getToken();
        //根据当前账号查询该组织内的可租房源
        HouseRoom houseRoom = DataSupport.queryRentableRoomInfo( String.valueOf( userBO.getTenantId() ) );
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
        leaseParam.put( "houseType",houseRoom.getHouseMode());

        JSONObject lessee = new JSONObject();
        lessee.put( "realName",leaseBO.getRealName() );
        lessee.put( "cardNo",leaseBO.getCardNo() );
        lessee.put( "mobile",leaseBO.getMobile() );
        lessee.put( "cardType",leaseBO.getCardType() );
        lessee.put( "sex",1 );
        lessee.put( "birthday","" );
        leaseParam.put( "lessee",lessee);
        leaseParam.put( "startDate",leaseBO.getStartDate());
        if (StringUtils.isNotBlank(leaseBO.getEndDate())){
            leaseParam.put( "endDate",leaseBO.getEndDate() );
        }
        leaseParam.put( "rentType",1 );
        leaseParam.put( "rentWay",1 );
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

        List contractPicUrlList = new ArrayList();
        leaseParam.put( "contractPicUrlList",contractPicUrlList);

        leaseParam.put( "tenantSourceCode","1");
        leaseParam.put( "leaseIntentCode","84");
        leaseParam.put( "currentRentType","1");
        leaseParam.put( "roomPriceStr",xqRepJsonData.getString( "roomPriceStr" ));
        leaseParam.put( "defaultSignType",1);
        leaseParam.put( "defaultLeaseIntentDictCode","");
        leaseParam.put( "rentWayInits",xqRepJsonData.getJSONObject( "rentWayInits" ));
        leaseParam.put( "contractPicUrlList",contractPicUrlList);
        leaseParam.put( "contractPicUrlList",contractPicUrlList);
        leaseParam.put( "contractPicUrlList",contractPicUrlList);
        leaseParam.put( "contractPicUrlList",contractPicUrlList);
        leaseParam.put( "contractPicUrlList",contractPicUrlList);
        leaseParam.put( "contractPicUrlList",contractPicUrlList);
        leaseParam.put( "contractPicUrlList",contractPicUrlList);
        leaseParam.put( "contractPicUrlList",contractPicUrlList);
        leaseParam.put( "contractPicUrlList",contractPicUrlList);
        leaseParam.put( "contractPicUrlList",contractPicUrlList);
        leaseParam.put( "contractPicUrlList",contractPicUrlList);

        return xqRepJson;
    }

    public static void main(String[] args) {
        LeaseBO leaseBO = new LeaseBO();
        System.out.println(buildLeaseParams(leaseBO));
    }
}
