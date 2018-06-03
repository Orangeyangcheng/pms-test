package common;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import java.util.Map;
import java.util.Set;


public class HttpUtil {

    /**
     * post请求
     * @param httpRequesth
     * @return
     */
    public static String doPost(HttpRequest httpRequesth){

        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        String response = new String();
        String requestUrl;
        if(StringUtils.isNotBlank(ObjectUtils.toString(httpRequesth.getParams()))){
            requestUrl = getRequestUrl(httpRequesth.getUrl(), httpRequesth.getParams());
        }
        else {
            requestUrl = httpRequesth.getUrl();
        }
        HttpPost post = new HttpPost(requestUrl);
        if (!httpRequesth.getContentType().isEmpty()){
            post.addHeader(HTTP.CONTENT_TYPE,httpRequesth.getContentType());
        }
        if (!httpRequesth.getHeader().isEmpty()){
            for (String key:httpRequesth.getHeader().keySet()){
                String val = httpRequesth.getHeader().get(key).toString();
                post.addHeader(key,val);
            }
        }
        if(StringUtils.isNotBlank(ObjectUtils.toString(httpRequesth.getJsonObject()))){
            StringEntity reqEntity = new StringEntity(httpRequesth.getJsonObject().toString(), HTTP.UTF_8);
            reqEntity.setContentEncoding("UTF-8");
            post.setEntity(reqEntity);
        }
        try {
            HttpResponse res = httpclient.execute(post);
            System.out.println("HttpStatus:"+res.getStatusLine().getStatusCode());
            response = EntityUtils.toString(res.getEntity());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }


    /**
     * get请求
     * @param httpRequest
     * @return
     */
    public static String doGet(HttpRequest httpRequest) {
        String httpGetResponse = null;
        String requestUrl;
        if (!httpRequest.getParams().isEmpty()){
            requestUrl = getRequestUrl(httpRequest.getUrl(), httpRequest.getParams());
        }
        else {
            requestUrl = httpRequest.getUrl();
        }
        HttpGet httpGet = new HttpGet(requestUrl);
         if (!httpRequest.getHeader().isEmpty()){
             for (String key:httpRequest.getHeader().keySet()){
                 String val = httpRequest.getHeader().get(key).toString();
                 httpGet.addHeader(key,val);
             }
         }
        try {
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(httpGet);
            System.out.println("HttpStatus:"+response.getStatusLine().getStatusCode());
            httpGetResponse = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpGetResponse;
    }


    /**
     * map转换URL
     * @param params
     * @return URL
     */
    public static String paramsConvertUrl(Map<String, Object> params) {
        StringBuilder urlParams = new StringBuilder("?");
        Set<Map.Entry<String, Object>> entries = params.entrySet();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            urlParams.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        String urlParamsStr = urlParams.toString();
        return urlParamsStr.substring(0, urlParamsStr.length()-1);
    }

    /**
     * 拼接get请求的url请求地址
     */
    public static String getRequestUrl(String url, Map<String, String> params) {
        StringBuilder builder = new StringBuilder(url);
        boolean isFirst = true;
        for (String key : params.keySet()) {
            if (key != null && params.get(key) != null) {
                if (isFirst) {
                    isFirst = false;
                    builder.append("?");
                } else {
                    builder.append("&");
                }
                builder.append(key)
                        .append("=")
                        .append(params.get(key));
            }
        }
//        System.out.println("==============="+"请求Url"+"===============");
//        System.out.println(builder.toString());
        return builder.toString();
    }
}
