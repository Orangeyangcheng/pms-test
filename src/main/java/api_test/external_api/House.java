package api_test.external_api;

import common.HttpRequest;
import common.HttpUtil;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static common.HttpConfig.applicationJson;

public class House {

    public static String url = "http://dpm1-gmd.mdguanjia.com/pms-external/house/save";

    public static JSONObject houseParams (){
        JSONObject body = new JSONObject();
        body.put( "communityId",42978 );
        body.put( "currentFloor",1 );
        body.put( "decorateLevel",3 );
        List facilityItemList = new ArrayList(  );
        body.put( "facilityItemList",facilityItemList );
        body.put( "flatBuilding","12");
        body.put( "flatDoor","101" );
        body.put( "flatUnit","1" );
        body.put( "hallNum",1 );
        body.put( "houseArea","99" );
        body.put( "houseManagerName","系统管理员" );
        body.put( "houseManagerTel","18899998888" );

        body.put( "houseMode",1 );
        body.put( "kitchenNum",1 );
        body.put( "outerHouseCode","WB"+String.valueOf( System.currentTimeMillis() ) );
        JSONObject roomDesc = new JSONObject(  );
        body.put( "roomDesc",roomDesc );
        body.put( "roomFace",0 );
        List roomItemTagList = new ArrayList(  );
        body.put( "roomItemTagList",roomItemTagList );
        body.put( "roomNum",3 );
        List rooms = new ArrayList(  );
        JSONObject roomA = new JSONObject(  );
        roomA.put( "outerRoomCode","WBR"+System.currentTimeMillis() );
        List rentTypes = new ArrayList(  );
        JSONObject rentTypesjson = new JSONObject(  );
        rentTypesjson.put( "depositMonthNum",1 );
        rentTypesjson.put( "depositPrice",3000 );
        rentTypesjson.put( "rentMonthNum",1 );
        rentTypesjson.put( "rentMonthPrice",3000 );
        rentTypes.add( rentTypesjson );

        roomA.put( "rentTypes",rentTypes );
        roomA.put( "roomArea",33.3 );
        roomA.put( "roomFace",1);
        roomA.put( "roomName","A" );
        roomA.put( "roomRentStatus",1 );
        roomA.put( "roomType",1 );

        rooms.add( roomA );
        body.put( "rooms",rooms );
        body.put( "storeId",3133 );
        body.put( "structure",1 );
        body.put( "toiletNum",1 );
        body.put( "totalFloor",1 );

        return body;

    }


    @Test
    public static void saveHouse (){
        Map<String,String> header = new HashMap<String, String>();
        HttpRequest httpRequest = new HttpRequest();

        header.put("authorization",authorization);
        httpRequest.setHeader(header);
        httpRequest.setUrl(url);
        httpRequest.setContentType(applicationJson);
        JSONObject houseParams = houseParams();
        httpRequest.setJsonObject( houseParams );
        System.out.println( com.alibaba.fastjson.JSONObject.toJSONString( houseParams,true ) );

        String rep = HttpUtil.doPost( httpRequest );
        JSONObject repJson = JSONObject.fromObject( rep );

        System.out.println( com.alibaba.fastjson.JSONObject.toJSONString( repJson,true ) );

    }

    public static String authorization = "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnRfaWQiOiIyNzg0NDYwMCIsInVzZXJfbmFtZSI6IjE4ODk5OTk4ODg4IiwiZGV2aWNlX3R5cGUiOiJ3ZWIiLCJhdmF0YXIiOiJodHRwczovL2d3LmFsaXBheW9iamVjdHMuY29tL3pvcy9ybXNwb3J0YWwvQmlhemZhbnhtYW1OUm94eFZ4a2EucG5nIiwicHJvZHVjdF9jb2RlIjoic3dvcmQiLCJhdXRob3JpdGllcyI6WyLns7vnu5_nrqHnkIblkZgiXSwiY2xpZW50X2lkIjoic3dvcmQiLCJyb2xlX25hbWUiOiLns7vnu5_nrqHnkIblkZgiLCJ1c2VyX2lkIjoiMTA2NTUiLCJyb2xlX2lkIjoiMTg3MSIsInBob25lIjoiMTg4OTk5OTg4ODgiLCJoZWFkcXVhcnRlciI6IjEiLCJzY29wZSI6WyJhbGwiXSwibmlja19uYW1lIjoi57O757uf566h55CG5ZGYIiwiZXhwIjoxNTg4MzMwOTYxLCJkZXB0X2lkIjoiMzEzMiIsImp0aSI6IjZkNDJmYWU0LTU1ZTUtNGQ0MC05Y2Y1LTc4YjU1ZTQ0MDk5NSIsImFjY291bnQiOiIxODg5OTk5ODg4OCJ9.LHS-5d6AuRIpHioKyDxqKacTSViz3LbBj0v9Q1ugDZk";

}
