package api_test.community_api;

import api_test.base_test.TestAccount;
import api_test.uac.UserBO;
import common.EVOcontrol;
import common.HttpRequest;
import common.HttpUtil;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static api_test.uac.UserToken.getToken;
import static api_test.uac.UserToken.online;
import static common.BeautifyJson.beautifyJson;
import static common.HttpConfig.applicationJson;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class CommunityTest extends EVOcontrol {
    //定义测试环境
    public static String testEvo = tpm1;

    private JSONObject getCommunityParams(){
        JSONObject communityParams = new JSONObject();
        communityParams.put( "provinceId", 330000);
        communityParams.put( "cityId", 330100);
        communityParams.put( "areaId", 330106);
        communityParams.put( "latitude", "30.274139");
        communityParams.put( "longitude", "120.135522");
        communityParams.put( "communityName", "百脑汇科技大厦");
        communityParams.put( "address", "教工路23号");
        return communityParams;
    }


    @Test
    public void addCommunityUrl_Test(){
        JSONObject res = getCommunityParams();

        UserBO userBO = TestAccount.getToken(testEvo,true);

        Map<String,String> header = new HashMap<String, String>();
        header.put( "Authorization",userBO.getToken() );

        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setUrl(getAddress( testEvo,addCommunityUrl ));
        httpRequest.setHeader( header );
        httpRequest.setJsonObject( res );
        httpRequest.setContentType( applicationJson );

        String rep = HttpUtil.doPost(httpRequest);
        JSONObject repJson = JSONObject.fromObject( rep );
        System.out.println("========================"+"添加小区响应"+"========================");
        System.out.println(beautifyJson(repJson));

    }

}
