package api_test.uac;

import common.DataSupport;
import common.HttpRequest;
import common.HttpUtil;
import mybatis.pojo.Community;
import mybatis.pojo.PmsStore;
import net.sf.json.JSONObject;
import org.joda.time.DateTime;

import java.time.DateTimeException;
import java.util.*;

import static common.HttpConfig.applicationJson;

public class PmsTenantOperation {

    private static String addStoreUrl = "http://tpm1-gmd.mdguanjia.com/pms-uac/dept/addStore";

    public static PmsStore addStore(Community community,String token,String tenantId){

        JSONObject StoreParams = new JSONObject();
        StoreParams.put( "deptName", String.format( community.getCityName() + "门店") );
        StoreParams.put( "provinceId",community.getProvinceId() );
        StoreParams.put( "cityId",community.getCityId() );
        StoreParams.put( "countyId",community.getAreaId() );
        StoreParams.put( "address",community.getAddress() );

        List area = new ArrayList();
        area.add( community.getProvinceId() );
        area.add( community.getCityId() );
        area.add( community.getAreaId() );
        StoreParams.put( "area", area);

        Map<String,String> header = new HashMap<String, String>();
        header.put( "Authorization",token );
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setUrl(addStoreUrl);
        httpRequest.setHeader( header );
        httpRequest.setJsonObject( StoreParams );
        httpRequest.setContentType( applicationJson );

        List<PmsStore> pmsStores = null;
        String rep = HttpUtil.doPost(httpRequest);
        JSONObject repJson = JSONObject.fromObject( rep );
        if (repJson.getBoolean( "success" )){
            pmsStores = DataSupport.queryPmsStore( tenantId,community.getCityId().intValue());
        }
        return pmsStores.get(0);
    }
}
