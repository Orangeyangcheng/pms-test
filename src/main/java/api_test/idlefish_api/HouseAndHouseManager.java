package api_test.idlefish_api;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.*;
import com.taobao.api.response.*;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;

import static common.BeautifyJson.beautifyJson;

public class HouseAndHouseManager {
    // 闲鱼请求地址
    public static String serverUrl = "https://eco.taobao.com/router/rest";

    // 闲鱼appKey
    public static String appKey = "24829706";

    // 闲鱼appSecret
    public static String appSecret = "e1d0284c8cf01d15e49863a1d46e5d9e";

    /**
     * 修改闲鱼房态
     * @throws ApiException
     */
    @Test
    public void updateRoomStatus() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
        AlibabaIdleHouseItemUpdatestatusRequest req = new AlibabaIdleHouseItemUpdatestatusRequest();
        // 闲鱼房源itemId
        req.setItemId(Long.valueOf("616056911382"));
        // 房源的目标状态，-1表示删除；-2表示下架；0表示上架(只有下架的房源可以上架）
        req.setHouseStatus(-1L);
        AlibabaIdleHouseItemUpdatestatusResponse rsp = client.execute(req);
        JSONObject rspJson = JSONObject.fromObject( rsp.getBody() );
        System.out.println(beautifyJson(rspJson));
    }

    /**
     * 查询闲鱼房源信息
     * @throws ApiException
     */
    @Test
    public void queryRoomStatus() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
        AlibabaIdleHouseItemQueryRequest req = new AlibabaIdleHouseItemQueryRequest();
        req.setItemId(616056911382L);
        AlibabaIdleHouseItemQueryResponse rsp = client.execute(req);
        JSONObject rspJson = JSONObject.fromObject( rsp.getBody() );
        System.out.println(beautifyJson(rspJson));

    }


    /**
     * 查询账号状态
     * @throws ApiException
     */
    @Test
    public void queryuser() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
        AlibabaIdleHouseUserQueryuserRequest req = new AlibabaIdleHouseUserQueryuserRequest();
        req.setUserNick("程悦个人的");
        AlibabaIdleHouseUserQueryuserResponse rsp = client.execute(req);
        JSONObject rspJson = JSONObject.fromObject( rsp.getBody() );
        System.out.println(beautifyJson(rspJson));
    }

    /**
     * 解绑账号
     * @throws ApiException
     */
    @Test
    public void dellender() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
        AlibabaIdleHouseUserDellenderRequest req = new AlibabaIdleHouseUserDellenderRequest();
        req.setUserNick("潘小凯panxiaokai");
        AlibabaIdleHouseUserDellenderResponse rsp = client.execute(req);
        JSONObject rspJson = JSONObject.fromObject( rsp.getBody() );
        System.out.println(beautifyJson(rspJson));
    }

    /**
     * 解绑子账号
     * @throws ApiException
     */
    @Test
    public void delhousemanager() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
        AlibabaIdleHouseUserDelhousemanagerRequest req = new AlibabaIdleHouseUserDelhousemanagerRequest();
        req.setUserNick("woshizyx123456");
        AlibabaIdleHouseUserDelhousemanagerResponse rsp = client.execute(req);
        JSONObject rspJson = JSONObject.fromObject( rsp.getBody() );
        System.out.println(beautifyJson(rspJson));
    }


    /**
     * 闲鱼租约退租接口
     * @throws ApiException
     */
    @Test
    public void rentTrade()throws ApiException{
        TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
        AlibabaIdleHouseIdleRentTradeNotifyrentcloseRequest req = new AlibabaIdleHouseIdleRentTradeNotifyrentcloseRequest();
        //闲鱼租约Id
        req.setContractId(6331242220L);
        AlibabaIdleHouseIdleRentTradeNotifyrentcloseResponse rsp = client.execute(req);
        JSONObject rspJson = JSONObject.fromObject( rsp.getBody() );
        System.out.println(beautifyJson(rspJson));
    }

    /**
     * 闲鱼取消租约接口
     * @throws ApiException
     */
    @Test
    public void cancelcontract() throws ApiException{
        TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
        AlibabaIdleHouseIdleRentTradeCancelcontractRequest req = new AlibabaIdleHouseIdleRentTradeCancelcontractRequest();
        //闲鱼租约Id
        req.setContractId("42451353");
        AlibabaIdleHouseIdleRentTradeCancelcontractResponse rsp = client.execute(req);
        JSONObject rspJson = JSONObject.fromObject( rsp.getBody() );
        System.out.println(beautifyJson(rspJson));
    }
}
