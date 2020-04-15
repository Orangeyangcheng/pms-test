package api_test.uac;

import common.HttpRequest;
import common.HttpUtil;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static common.HttpConfig.applicationJson;

public class UserToken {

    private static String getUserTokenUrl = "https://gmd.mdguanjia.com/pms-uac/oauth/token";


    public static String getToken(UserBO userBO){
        HttpRequest httpRequest = new HttpRequest();
        Map<String,String> params = new HashMap<String, String>();
        params.put("username",userBO.getPhone());
        params.put("password",userBO.getPwd());
        params.put("grant_type","password");
        params.put("scope","all");
        httpRequest.setParams(params);
        httpRequest.setUrl(getUserTokenUrl);
        httpRequest.setContentType(applicationJson);
        Map<String,String> header = new HashMap<String, String>();
        header.put("authorization","Basic c3dvcmQ6c3dvcmRfc2VjcmV0");
        httpRequest.setHeader(header);
        String rep = HttpUtil.doPost(httpRequest);
        JSONObject json = JSONObject.fromObject(rep);
        JSONObject data = json.getJSONObject("data");
        return data.getString("access_token");
    }

    @Test
    public void getTokenByPwdTest(){
        HttpRequest httpRequest = new HttpRequest();
        Map<String,String> params = new HashMap<String, String>();
        params.put("username","13175112092");
        params.put("password","1234567");
        params.put("grant_type","password");
        params.put("scope","all");
        httpRequest.setParams(params);
        httpRequest.setUrl(getUserTokenUrl);
        httpRequest.setContentType(applicationJson);
        Map<String,String> header = new HashMap<String, String>();
        header.put("authorization","Basic c3dvcmQ6c3dvcmRfc2VjcmV0");
        httpRequest.setHeader(header);
        String rep = HttpUtil.doPost(httpRequest);
        JSONObject json = JSONObject.fromObject(rep);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(json,true));
        JSONObject data = json.getJSONObject("data");
        System.out.println(data.getString("access_token"));
    }

    @Test
    public void getTokenTest(){
        UserBO userBO = new UserBO();
        userBO.setPhone("13175112092");
        userBO.setPwd("1234567");
        String token = getToken(userBO);
        System.out.println(token);
    }

}
