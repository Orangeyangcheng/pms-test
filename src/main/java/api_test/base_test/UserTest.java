package api_test.base_test;

import common.HttpRequest;
import common.HttpUtil;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static api_test.uac.UserToken.online;
import static api_test.uac.UserToken.tmp1;
import static common.BeautifyJson.beautifyJson;
import static common.HttpConfig.applicationJson;

public class UserTest {

    StringBuilder token = new StringBuilder();

    public static String getUserUrl = "http://tpm1-gmd.mdguanjia.com/pms-uac/user/getUser";

    @Test
    public void loginByPassWord_Test(){
        HttpRequest httpRequest = new HttpRequest();
        Map<String,String> params = new HashMap<String, String>();
        String username = "13133373338";
        String password = "1234567";


        params.put("username",username);
        params.put("password",password);
        params.put("grant_type","password");
        params.put("scope","all");
        httpRequest.setParams(params);
        httpRequest.setUrl(tmp1);
        httpRequest.setContentType(applicationJson);
        Map<String,String> header = new HashMap<String, String>();
        header.put("authorization","Basic c3dvcmQ6c3dvcmRfc2VjcmV0");
        httpRequest.setHeader(header);
        String rep = HttpUtil.doPost(httpRequest);
        JSONObject json = JSONObject.fromObject(rep);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(json,true));
        JSONObject data = json.getJSONObject("data");
        int code = json.getInt("code");


        //登录校验
        Assert.assertEquals( code,200,"接口状态码异常" );
        Assert.assertEquals( data.getString( "account" ),username,"登录账号返回异常");
        Assert.assertEquals( data.getString( "phone" ),username,"登录手机号返回异常" );
        Assert.assertNotNull( data.getString( "access_token" ),"token为空" );
        Assert.assertNotNull( data.getString( "refresh_token" ),"token为空" );

        token.append( "bearer" );
        token.append( " " );
        token.append( data.getString("access_token"  ) );
    }

    @Test(priority = 1)
    public void getUser_Test(){
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setUrl(getUserUrl);
        httpRequest.setContentType(applicationJson);
        Map<String,String> header = new HashMap<String, String>();
        header.put("Authorization",token.toString());
        httpRequest.setHeader(header);
        String rep = HttpUtil.doPost(httpRequest);
        JSONObject json = JSONObject.fromObject(rep);
        System.out.println(beautifyJson(json));

        Boolean isSuccess = json.getBoolean("success");
        int code = json.getInt("code");
        JSONObject data = json.getJSONObject("data");

        Assert.assertEquals( code,200,"接口状态码异常" );
        Assert.assertTrue( isSuccess,"is not success" );
        Assert.assertNotNull( data,"data is empty" );
    }


}
