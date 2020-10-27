package api_test.ota_api;

import api_test.base_test.TestAccount;
import api_test.uac.UserBO;
import common.EVOcontrol;
import common.HttpRequest;
import common.HttpUtil;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static api_test.uac.UserToken.getToken;
import static api_test.uac.UserToken.online;
import static common.BeautifyJson.beautifyJson;
import static common.HttpConfig.applicationJson;

public class ChannelTest extends EVOcontrol {

    //定义测试环境
    public static String testEvo = online;


    @Test(invocationCount = 10)
    public void getChannelList_Test(){
        UserBO userBO = TestAccount.getToken(testEvo,true);
        Map<String,String> header = new HashMap<String, String>();
        header.put( "Authorization",userBO.getToken() );

        JSONObject res = new JSONObject(  );

        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setUrl(getAddress( testEvo,getChannelList ));
        httpRequest.setHeader( header );
        httpRequest.setJsonObject( res );
        httpRequest.setContentType( applicationJson );

        String rep = HttpUtil.doPost(httpRequest);
        JSONObject repJson = JSONObject.fromObject( rep );

        System.out.println(beautifyJson(repJson));

        Assert.assertTrue( repJson.getBoolean( "success" ),"assert success fail" );
        Assert.assertEquals( repJson.getInt( "code" ),200,"assert code fail" );

    }

}
