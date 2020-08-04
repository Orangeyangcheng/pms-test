package api_test.base_test;

import api_test.uac.UserBO;
import api_test.uac.UserToken;
import common.DataSupport;
import common.HttpRequest;
import common.HttpUtil;
import mybatis.pojo.PmsTenant;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api_test.uac.UserToken.*;
import static common.BeautifyJson.beautifyJson;
import static common.HttpConfig.applicationJson;

public class BasicConfigurationTest {

    private static String dictInitUrl = "https://gmd.mdguanjia.com/pms-uac/configpreferencecontract/dictInit";

    private static String access_token = "";


    private static UserBO getToken (){
        UserBO userBO = new UserBO();
        userBO.setPhone("13175110031");
        userBO.setPwd("1234567");
        userBO.setEnv( tmp1 );
        access_token = UserToken.getToken(userBO);
        userBO.setToken( access_token );
//        PmsTenant pmsTenant = DataSupport.queryTenantInfoByAdminPhone(userBO.getPhone());
//        userBO.setTenantId( pmsTenant.getTenantId() );
        return userBO;
    }

    @Test
    public void dictInit_Test(){
        UserBO userBO = getToken();
        JSONObject params = new JSONObject();
        params.put( "customerType","1" );


        for (int i=0;i<=100;i++){
            HttpRequest httpRequest = new HttpRequest();

            Map<String,String> header = new HashMap();
            header.put( "Authorization",userBO.getToken() );
            httpRequest.setUrl(dictInitUrl);
            httpRequest.setHeader( header );
            httpRequest.setJsonObject( params );
            httpRequest.setContentType( applicationJson );

            String rep = HttpUtil.doPost(httpRequest);
            JSONObject repJson = JSONObject.fromObject( rep );
            System.out.println(beautifyJson(repJson));


            List data =  repJson.getJSONArray( "data" );
            String group1 = data.get( 0 ).toString();
            JSONObject jso1 = JSONObject.fromObject( group1 );
            List lis1 = jso1.getJSONArray( "detailList" );
            Assert.assertTrue( lis1.size()==18,"租赁信息缺少" );

            String group2 = data.get( 1 ).toString();
            JSONObject jso2 = JSONObject.fromObject( group2 );
            List lis2 = jso2.getJSONArray( "detailList" );
            Assert.assertTrue( lis2.size()==1,"其他信息缺少" );

            String group3 = data.get( 2 ).toString();
            JSONObject jso3 = JSONObject.fromObject( group3 );
            List lis3 = jso3.getJSONArray( "detailList" );
            Assert.assertTrue( lis3.size()==5,"签约信息缺少" );

            String group4 = data.get( 3 ).toString();
            JSONObject jso4 = JSONObject.fromObject( group4 );
            List lis4 = jso4.getJSONArray( "detailList" );
            Assert.assertTrue( lis4.size()==3,"运营商信息缺少" );

            System.out.println("==============="+"第"+i+"次测试通过"+"===============");
        }



    }

}
