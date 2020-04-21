package api_test.data_move;

import api_test.uac.UserBO;
import api_test.uac.UserToken;
import common.HttpRequest;
import common.HttpUtil;
import net.sf.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static common.HttpConfig.applicationJson;

public class HouseMove {

    public String token;
    //集中式房源迁移
    public static String estateMoveUrl = "https://dts.mdguanjia.com/hsc/estate/save";

    //分散式房源迁移
    public static String houseMoveUrl = "https://dts.mdguanjia.com/hsc/house/save";

    @BeforeTest
    public void getToken(){
        UserBO userBO = new UserBO();
        userBO.setPhone( "16619786787" );
        userBO.setPwd( "123456Aa" );
        token = UserToken.getToken( userBO );
    }

    @Test(enabled = true,groups = "PMS_OMC")
    public void estateMove(){
        JSONObject params = new JSONObject();
        int tenantId = 9839;
        params.put( "tenantId",tenantId);
        params.put( "limit",1 );

        Map<String,String> header = new HashMap<String, String>(  );
        header.put( "authorization",token );


        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setUrl( estateMoveUrl );
        httpRequest.setHeader( header );
        httpRequest.setJsonObject( params );
        httpRequest.setContentType( applicationJson );

        for (int i=0;i<=2500;i++){
            String result = HttpUtil.doPost(httpRequest);
            StringBuffer stringBuffer = new StringBuffer(  );
            stringBuffer.append( "=============" );
            stringBuffer.append( "执行第"+i+"次循环" );
            stringBuffer.append( "同步"+params.getInt( "limit" )+"套集中式" );
            stringBuffer.append( "=============" );
            System.out.println( stringBuffer );
            JSONObject resultJson = JSONObject.fromObject( result );
            System.out.println( com.alibaba.fastjson.JSONObject.toJSONString( resultJson,true ) );
        }

    }

    @Test(enabled = true,groups = "PMS_OMC")
    public void houseMove(){
        JSONObject params = new JSONObject();
        int tenantId = 9899;
        params.put( "tenantId",tenantId);
        params.put( "limit",1 );
        Map<String,String> header = new HashMap<String, String>(  );
        header.put( "authorization",token );


        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setUrl( houseMoveUrl );
        httpRequest.setHeader( header );
        httpRequest.setJsonObject( params );
        httpRequest.setContentType( applicationJson );

        for (int i=0;i<=2500;i++){
            String result = HttpUtil.doPost(httpRequest);
            StringBuffer stringBuffer = new StringBuffer(  );
            stringBuffer.append( "=============" );
            stringBuffer.append( "执行第"+i+"次循环" );
            stringBuffer.append( "同步"+params.getInt( "limit" )+"套分散式" );
            stringBuffer.append( "=============" );
            System.out.println( stringBuffer );
            JSONObject resultJson = JSONObject.fromObject( result );
            System.out.println( com.alibaba.fastjson.JSONObject.toJSONString( resultJson,true ) );
        }
    }
}
