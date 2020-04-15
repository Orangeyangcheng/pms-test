package common;

import net.sf.json.JSONObject;

import java.util.Map;

/**
 * http请求对象
 */
public class HttpRequest {
    /**
     * 请求头
     */
    private Map<String,String> header;

    /**
     * 参数类型
     */
    private String ContentType;

    /**
     * URL
     */
    private String Url;

    /**
     * jsonBody
     */
    private JSONObject jsonObject;

    /**
     * map参数
     */
    private Map<String,String> params;


    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public String getContentType() {
        return ContentType;
    }

    public void setContentType(String contentType) {
        ContentType = contentType;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
