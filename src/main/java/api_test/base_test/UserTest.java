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
import static common.HttpConfig.applicationJson;

public class UserTest {




    @Test
    public void loginByPassWord_通过账号密码登录(){
        HttpRequest httpRequest = new HttpRequest();
        Map<String,String> params = new HashMap<String, String>();
        String username = "13175112092";
        String password = "1234567";


        params.put("username",username);
        params.put("password",password);
        params.put("grant_type","password");
        params.put("scope","all");
        httpRequest.setParams(params);
        httpRequest.setUrl(online);
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
    }
}
