package api_test.community_api;

import api_test.uac.UserBO;
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

public class CommunityTest {

    public static String addCommunityUrl = "http://tpm3-gmd.mdguanjia.com/pms-mdc/library/community/house/diyInput";

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

        UserBO userBO = new UserBO();
        userBO.setPhone("13175112092");
        userBO.setPwd("1234567");
        userBO.setEnv( online );
        String token = getToken(userBO);

        Map<String,String> header = new HashMap<String, String>();
        header.put( "Authorization",token );

        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setUrl(addCommunityUrl);
        httpRequest.setHeader( header );
        httpRequest.setJsonObject( res );
        httpRequest.setContentType( applicationJson );

        String rep = HttpUtil.doPost(httpRequest);
        JSONObject repJson = JSONObject.fromObject( rep );

        System.out.println(beautifyJson(repJson));

    }

}
