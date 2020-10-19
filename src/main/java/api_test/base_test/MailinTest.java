package api_test.base_test;

import common.HttpRequest;
import common.HttpUtil;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;

import java.util.*;

import static common.BeautifyJson.beautifyJson;
import static common.HttpConfig.applicationJson;

public class MailinTest {

    private static String auditUrl = "http://tpm1-goa.mdguanjia.com/pms-hsc/houseauditinfo/audit";

    private static String auditPageUrl = "http://tpm1-goa.mdguanjia.com/pms-hsc/houseauditinfo/auditPage";

    private static String token = "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnRfaWQiOiIwMDAwMDAwMCIsInVzZXJfbmFtZSI6IuadqOapmSIsImRldmljZV90eXBlIjoid2ViIiwiYXZhdGFyIjoiaHR0cHM6Ly9ndy5hbGlwYXlvYmplY3RzLmNvbS96b3Mvcm1zcG9ydGFsL0JpYXpmYW54bWFtTlJveHhWeGthLnBuZyIsInByb2R1Y3RfY29kZSI6ImRhZ2dlciIsImF1dGhvcml0aWVzIjpbIua1i-ivleeIuOeIuCJdLCJjbGllbnRfaWQiOiJkYWdnZXIiLCJyb2xlX25hbWUiOiLmtYvor5XniLjniLgiLCJ1c2VyX2lkIjoiMjAyIiwicm9sZV9pZCI6IjIwMyIsInBob25lIjoiMTMxNzUxMTIwOTEiLCJoZWFkcXVhcnRlciI6IjAiLCJzY29wZSI6WyJhbGwiXSwibmlja19uYW1lIjoi5p2o5qmZIiwiZXhwIjoxNjAyOTAzMzQwLCJkZXB0X2lkIjoiMjAxIiwianRpIjoiOWU4YjRmODMtMGQwNS00YTc4LThjODQtYWYwY2U0Y2NkNjExIiwiYWNjb3VudCI6IjEzMTc1MTEyMDkxIn0.0eNhyG6N9HSAQb8HAE5wUS_pKzeEqFx-ijkmWzHyWiM";

    public static List getAuditId(){
        JSONObject params = new JSONObject();
        params.put( "auditFlag",1 );
        params.put( "current",1 );
        params.put( "size",20 );

        Map<String,String> header = new HashMap<String, String>();
        header.put( "Authorization",token );
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setUrl(auditPageUrl);
        httpRequest.setHeader( header );
        httpRequest.setJsonObject( params );
        httpRequest.setContentType( applicationJson );

        String rep = HttpUtil.doPost(httpRequest);

        JSONObject repJson = JSONObject.fromObject( rep );
        JSONObject data = repJson.getJSONObject( "data" );
        List records = data.getJSONArray( "records" );
        List ids = new ArrayList();
        for (int i=0;i<records.size();i++){
            JSONObject record = (JSONObject) records.get( i );
            ids.add( record.getInt( "id" ) );
        }


        return ids;
    }

    /**
     * 麦邻房源发布自动审核
     */
    @Test(invocationCount = 10)
    public void auditPass(){
        List ids = getAuditId();
        System.out.println(ids);
        for (int i = 0;i<ids.size();i++){
            JSONObject params = new JSONObject();
            params.put( "id",ids.get( i ) );
            params.put( "auditStatus",1 );
            params.put( "auditFailReason","" );

            System.out.println(params);
            Map<String,String> header = new HashMap<String, String>();
            header.put( "Authorization",token );
            HttpRequest httpRequest = new HttpRequest();
            httpRequest.setUrl(auditUrl);
            httpRequest.setHeader( header );
            httpRequest.setJsonObject( params );
            httpRequest.setContentType( applicationJson );


            String rep = HttpUtil.doPost(httpRequest);

            JSONObject repJson = JSONObject.fromObject( rep );
            System.out.println(beautifyJson(repJson));
        }

    }

    public static void main(String[] args) {
        List rep = getAuditId();
        System.out.println(rep);
    }

}
