package api_test.ota_api;

import api_test.uac.UserBO;
import common.HttpRequest;
import common.HttpUtil;
import net.sf.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static api_test.uac.UserToken.getToken;
import static api_test.uac.UserToken.online;
import static common.BeautifyJson.beautifyJson;
import static common.HttpConfig.applicationJson;

public class ChannelTest {

    public static String getChannelList = "https://gmd.mdguanjia.com/pms-ota/channel/getChannelList";

    public static String getByTenantId = "https://gmd.mdguanjia.com/pms-ota/channel/getByTenantId";

    public static String token = "";

    @BeforeClass
    public void getTokenTest(){
        UserBO userBO = new UserBO();
        userBO.setPhone("13175112092");
        userBO.setPwd("1234567");
        userBO.setEnv( online );
        token = getToken(userBO);
    }


    @Test
    public void getChannelList_Test(){
        Map<String,String> header = new HashMap<String, String>();
        header.put( "Authorization",token );

        JSONObject res = new JSONObject(  );

        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setUrl(getChannelList);
        httpRequest.setHeader( header );
        httpRequest.setJsonObject( res );
        httpRequest.setContentType( applicationJson );

        String rep = HttpUtil.doPost(httpRequest);
        JSONObject repJson = JSONObject.fromObject( rep );

        System.out.println(beautifyJson(repJson));
    }

}
