package api_test.base_test;


import api_test.lease.LeaseBO;
import api_test.uac.UserBO;
import common.DataSupport;
import common.HttpRequest;
import common.HttpUtil;
import mybatis.pojo.HouseRoom;
import net.sf.json.JSONObject;

import java.util.HashMap;
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
        JSONObject repJson = JSONObject.fromObject( rep );
        System.out.println(beautifyJson(repJson));

        JSONObject leaseParam = new JSONObject();
        leaseParam.put( "signType",leaseBO.getSignType());
        leaseParam.put( "orderType",1);
        leaseParam.put( "roomCode",houseRoom.getRoomCode());
        leaseParam.put( "houseType",houseRoom.getHouseMode());


        return repJson;
    }

    public static void main(String[] args) {
        LeaseBO leaseBO = new LeaseBO();
        System.out.println(buildLeaseParams(leaseBO));
    }
}
