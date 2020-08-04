package api_test.idlefish_api;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.*;
import com.taobao.api.response.*;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        req.setItemId(Long.valueOf("623993003691"));
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
        req.setItemId(623999658255L);
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
        req.setUserNick("cheng_cui25");
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
        req.setUserNick("tb059804036");
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
        req.setUserNick("cheng_cui25");
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
        req.setContractId(23578412220L);
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
        req.setContractId("24113342220");
        AlibabaIdleHouseIdleRentTradeCancelcontractResponse rsp = client.execute(req);
        JSONObject rspJson = JSONObject.fromObject( rsp.getBody() );
        System.out.println(beautifyJson(rspJson));
    }

    /**
     * 查询闲鱼订单接口
     * @throws ApiException
     */
    @Test
    public void querycontract() throws ApiException{

        TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
        AlibabaIdleHouseIdleRentTradeQuerycontractRequest req = new AlibabaIdleHouseIdleRentTradeQuerycontractRequest();
        req.setContractId("24113342220");
        AlibabaIdleHouseIdleRentTradeQuerycontractResponse rsp = client.execute(req);
        JSONObject rspJson = JSONObject.fromObject( rsp.getBody() );
        System.out.println(beautifyJson(rspJson));
    }

    @Test
    public static void queryOrder() throws ApiException {

        String filePath = "C:\\Users\\10057\\Desktop\\contractId.txt";
        List list = readTxtFile2(filePath);
        for (int i = 0; i < list.size(); i++) {
            int no = 1;
            for ( int a = 0;a<=list.size();a++){

                TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
                AlibabaIdleHouseIdleRentTradeQuerycontractRequest req = new AlibabaIdleHouseIdleRentTradeQuerycontractRequest();
                req.setContractId(list.get( a ).toString());
                AlibabaIdleHouseIdleRentTradeQuerycontractResponse rsp = client.execute(req);
                JSONObject rspJson = JSONObject.fromObject( rsp.getBody() );
                JSONObject querycontract_response = rspJson.getJSONObject( "alibaba_idle_house_idle_rent_trade_querycontract_response" );
                JSONObject rent_top_result = querycontract_response.getJSONObject( "rent_top_result" );
                JSONObject contract_top_d_t_o = rent_top_result.getJSONObject( "contract_top_d_t_o" );
                int status = contract_top_d_t_o.getInt( "status" );
                if (status != 6){
                    System.out.println("闲鱼订单号："+list.get( a ).toString()+","+"状态是"+status+"序号"+no);
                    no++;
//                    System.out.println(list.get( a ).toString());

                }

//                System.out.println(beautifyJson(rspJson));

            }


        }

    }


    public static List readTxtFile2(String filePath){


        //定义存储读取到的数据记录的集合
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        List contractIds = new  ArrayList();

        try {

            String encoding="UTF-8";
            File file=new File(filePath);
            //判断文件是否存在
            if(file.isFile() && file.exists()){
                //考虑到编码格式
                InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                //记录读取的数据文件的行数
                int count = 0;
                //定义字段的数组
                String[] fields = null;
                //定义每条记录取出的字段值数组
                String[] fieldValue = null;
                //定义Map集合
                HashMap<String, String> map = new HashMap<String, String>();
                while((lineTxt = bufferedReader.readLine()) != null){
                    if(count == 0)
                    {
                        fields = lineTxt.split("\\|");
                        System.out.println("fields:"+fields);
                    }else
                    {
                        fieldValue = lineTxt.split("\\|");
                        //System.out.println("fieldValue:"+fieldValue);
                        for(int i = 0; i < fields.length; i++)
                        {
                            for (int j = 0; j < fieldValue.length; j++) {
                                if(i == j)
                                {
                                    contractIds.add( fieldValue[j]);
                                }
                            }
                        }
                        //将读取的每一行的记录存入到list集合中
                        list.add(map);
                    }

                    count ++;
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return contractIds;
    }

}
