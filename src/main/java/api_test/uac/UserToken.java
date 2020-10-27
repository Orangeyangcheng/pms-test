package api_test.uac;

import common.EVOcontrol;
import common.HttpRequest;
import common.HttpUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.time.DateUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static common.HttpConfig.applicationJson;

public class UserToken extends EVOcontrol {


    public static String getToken(UserBO userBO){
        HttpRequest httpRequest = new HttpRequest();
        Map<String,String> params = new HashMap<String, String>();
        params.put("username",userBO.getPhone());
        params.put("password",userBO.getPwd());
        params.put("grant_type","password");
        params.put("scope","all");
        httpRequest.setParams(params);
        httpRequest.setUrl(getAddress( userBO.getEnv(), oauthToken));
        httpRequest.setContentType(applicationJson);
        Map<String,String> header = new HashMap<String, String>();
        header.put("authorization","Basic c3dvcmQ6c3dvcmRfc2VjcmV0");
        httpRequest.setHeader(header);
        String rep = HttpUtil.doPost(httpRequest);
        JSONObject json = JSONObject.fromObject(rep);
        JSONObject data = json.getJSONObject("data");

        StringBuffer str = new StringBuffer();
        str.append( "bearer" );
        str.append( " " );
        str.append( data.getString( "access_token" ) );

        return str.toString();
    }



    @Test
    public void getTokenTest(){
        UserBO userBO = new UserBO();
        userBO.setPhone("13133373338");
        userBO.setPwd("1234567");
        userBO.setEnv(tpm1);
        System.out.println(getToken(userBO));
    }



}
