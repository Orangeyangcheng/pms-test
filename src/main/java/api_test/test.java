package api_test;

import common.HttpRequest;
import common.HttpUtil;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static common.HttpConfig.applicationJson;

public class test {

    public static String url = "https://gmd.mdguanjia.com/pms-mdc/library/region/area/listAreaByIds";


    @Test(enabled = true,groups = "PMS_OMC")
    public void test(){
        HttpRequest httpRequest = new HttpRequest();
        Map<String,String> header = new HashMap<String, String>();
        header.put("authorization",authorization);
        httpRequest.setHeader(header);
        httpRequest.setUrl(url);
        httpRequest.setContentType(applicationJson);

        JSONObject params = new JSONObject();
        params.put("ids","330100");
        httpRequest.setJsonObject(params);
        String response = HttpUtil.doPost(httpRequest);
        JSONObject rep = JSONObject.fromObject(response);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(rep,true));

    }


    public static String authorization = "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnRfaWQiOiIyOTk1OTYxMyIsInVzZXJfbmFtZSI6IjEzMTc1MTEyMDkyIiwiZGV2aWNlX3R5cGUiOiJ3ZWIiLCJhdmF0YXIiOiJodHRwczovL2d3LmFsaXBheW9iamVjdHMuY29tL3pvcy9ybXNwb3J0YWwvQmlhemZhbnhtYW1OUm94eFZ4a2EucG5nIiwicHJvZHVjdF9jb2RlIjoic3dvcmQiLCJhdXRob3JpdGllcyI6WyJRQVFBIl0sImNsaWVudF9pZCI6InN3b3JkIiwicm9sZV9uYW1lIjoiUUFRQSIsInVzZXJfaWQiOiIzMTYiLCJyb2xlX2lkIjoiMjk0IiwicGhvbmUiOiIxMzE3NTExMjA5MiIsImhlYWRxdWFydGVyIjoiMSIsInNjb3BlIjpbImFsbCJdLCJuaWNrX25hbWUiOiJDeWFuZyIsImV4cCI6MTU4NzEzODgwMywiZGVwdF9pZCI6IjM5NSIsImp0aSI6Ijg5M2FlMmNiLTczM2QtNGM0ZS05NWEyLTk2MTNjNzhhNjgwNyIsImFjY291bnQiOiIxMzE3NTExMjA5MiJ9.oJ7VXnMQKHcb8e-hW4CwGRTO5QCZlHP_N6T3rqTuxng";
}
