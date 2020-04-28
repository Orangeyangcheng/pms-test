package common;

import net.sf.json.JSONObject;

public class BeautifyJson {

    public static String beautifyJson(JSONObject jsonObject){
        return com.alibaba.fastjson.JSONObject.toJSONString( jsonObject,true );
    }
}
