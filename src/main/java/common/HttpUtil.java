package common;

import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class HttpUtil {

    /**
     *
     * @param httpRequesth
     * @return
     */
    public static String doPost(HttpRequest httpRequesth){

        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        String response = new String();
        HttpPost post = new HttpPost(httpRequesth.getUrl());
        if (!httpRequesth.getContentType().isEmpty()){
            post.addHeader(HTTP.CONTENT_TYPE,httpRequesth.getContentType());
        }
        if (!httpRequesth.getHeader().isEmpty()){
            for (String key:httpRequesth.getHeader().keySet()){
                String val = httpRequesth.getHeader().get(key).toString();
                post.addHeader(key,val);
            }
        }
        StringEntity reqEntity = new StringEntity(httpRequesth.getJsonObject().toString(),HTTP.UTF_8);
        reqEntity.setContentEncoding("UTF-8");
        post.setEntity(reqEntity);
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
     * @param url params
     * @return
     */
    public static JSONObject doGet(String url, Map<String, String> params) {
        JSONObject httpGetResponse = null;
        try {
            String requestUrl = getRequestUrl(url, params);
            httpGetResponse = null;
            HttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(requestUrl);
            HttpResponse response = client.execute(request);
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
                httpGetResponse = JSONObject.fromObject(strResult);
            }
            else{
                String strResult = EntityUtils.toString(response.getEntity());
                httpGetResponse = JSONObject.fromObject(strResult);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpGetResponse;
    }


    /**
     * post 请求
     * @param url params
     * @return
     */
    public static JSONObject doPostByUrl(String url, Map<String, String> params) {
        JSONObject httpGetResponse = null;
        try {
            String requestUrl = getRequestUrl(url, params);
            httpGetResponse = null;
            HttpClient client = new DefaultHttpClient();
            //发送POST请求
            HttpPost request = new HttpPost(requestUrl);
            HttpResponse response = client.execute(request);
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
                httpGetResponse = JSONObject.fromObject(strResult);
            }
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
        System.out.println("==============="+"请求Url"+"===============");
        System.out.println(builder.toString());
        return builder.toString();
    }




    /**
     * post请求 -添加请求头
     * @param url
     * @param json
     * @return
     */
    public static JSONObject doPost(String url, JSONObject json,String headerName,String headerValue){

        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        post.addHeader(HTTP.CONTENT_TYPE,"application/json");
        post.addHeader(headerName,headerValue);
        JSONObject response = null;
        try {
            StringEntity reqEntity = new StringEntity(json.toString(),HTTP.UTF_8);
            reqEntity.setContentEncoding("UTF-8");
            reqEntity.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(reqEntity);
            HttpResponse res = httpclient.execute(post);
            res.setHeader("Content-type", "application/json;charset=UTF-8");
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.fromObject(result);
            }
            else{
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.fromObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }


    /**
     * post请求
     * @param url
     * @param json
     * @return
     */
    public static JSONObject doPost(String url, JSONObject json){

        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        post.addHeader(HTTP.CONTENT_TYPE,"application/json");

        JSONObject response = null;
        try {
            StringEntity reqEntity = new StringEntity(json.toString(),HTTP.UTF_8);
            reqEntity.setContentEncoding("UTF-8");
            reqEntity.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(reqEntity);
            HttpResponse res = httpclient.execute(post);
            res.setHeader("Content-type", "application/json;charset=UTF-8");
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.fromObject(result);
            }
            else{
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.fromObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    /**
     * post请求在map对象中增加请求头
     * @param url
     * @param json
     * @param header
     * @return
     */
    public static JSONObject doPost(String url, JSONObject json,Map<String,String> header){

        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        if (header.size()!=0){
            for (Map.Entry<String,String> vo:header.entrySet()){
                post.addHeader(vo.getKey(),vo.getValue());
            }
        }
        post.addHeader(HTTP.CONTENT_TYPE,"application/json");

        JSONObject response = null;
        try {
            StringEntity reqEntity = new StringEntity(json.toString(),HTTP.UTF_8);
            reqEntity.setContentEncoding("UTF-8");
            reqEntity.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(reqEntity);
            HttpResponse res = httpclient.execute(post);
            res.setHeader("Content-type", "application/json;charset=UTF-8");
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.fromObject(result);
            }
            else{
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.fromObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    /**
     * post请求包含单个StringValues
     * @param url
     * @param values
     * @return
     */
    public static JSONObject doPost(String url, String values){

        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        post.addHeader(HTTP.CONTENT_TYPE,"application/json");
        JSONObject response = null;
        try {
            StringEntity reqEntity = new StringEntity(values,HTTP.UTF_8);
            reqEntity.setContentEncoding("UTF-8");
            reqEntity.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(reqEntity);
            HttpResponse res = httpclient.execute(post);
            res.setHeader("Content-type", "application/json;charset=UTF-8");
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.fromObject(result);
            }
            else{
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.fromObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    /**
     * post请求包含单个ListValues
     * @param url
     * @param values
     * @return
     */
    public static JSONObject doPost(String url, List values){

        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        post.addHeader(HTTP.CONTENT_TYPE,"application/json");
        JSONObject response = null;
        try {
            StringEntity reqEntity = new StringEntity(values.toString(),HTTP.UTF_8);
            reqEntity.setContentEncoding("UTF-8");
            reqEntity.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(reqEntity);
            HttpResponse res = httpclient.execute(post);
            res.setHeader("Content-type", "application/json;charset=UTF-8");
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.fromObject(result);
            }
            else{
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.fromObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
