package api_test.base_test;

import api_test.house.HouseBO;
import api_test.uac.UserBO;
import api_test.uac.UserToken;
import net.sf.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class HouseTest {

    private static String access_token = "";

    @BeforeClass
    public static void getToken (){
        UserBO userBO = new UserBO();
        userBO.setPhone("13177778888");
        userBO.setPwd("1234567");
        access_token = UserToken.getToken(userBO);
    }






    public static JSONObject buildHouseParams(HouseBO houseBO){
        JSONObject houseParams = new JSONObject();
        houseParams.put( "cityId",330100 );
        houseParams.put( "cityName","杭州市" );
        List commonPics = new ArrayList();
        houseParams.put( "commonPics",commonPics );
        houseParams.put( "communityAddress","晴川街" );
        houseParams.put( "communityId",houseBO.getCommunityId());
        houseParams.put( "currentFloor","1" );
        houseParams.put( "decorateLevel","3" );
        houseParams.put( "districtId",330106 );
        houseParams.put( "districtName","" );
        List facilityItemList = new ArrayList();
        facilityItemList.add( "1" );
        facilityItemList.add( "2" );
        facilityItemList.add( "3" );
        houseParams.put( "facilityItemList",facilityItemList );
        List roomItemTagList = new ArrayList();
        roomItemTagList.add( "1" );
        roomItemTagList.add( "2" );
        houseParams.put( "roomItemTagList",roomItemTagList );
        houseParams.put( "flatBuilding","12" );
        houseParams.put( "flatDoor","209" );
        houseParams.put( "flatUnit","2" );
        houseParams.put( "houseArea","89" );
        houseParams.put( "houseCode","" );
        houseParams.put( "cityId",330100 );
        houseParams.put( "cityId",330100 );
        houseParams.put( "cityId",330100 );
        houseParams.put( "cityId",330100 );
        houseParams.put( "cityId",330100 );
        houseParams.put( "cityId",330100 );
        houseParams.put( "cityId",330100 );
        houseParams.put( "cityId",330100 );
        houseParams.put( "cityId",330100 );
        houseParams.put( "cityId",330100 );
        houseParams.put( "cityId",330100 );
        houseParams.put( "cityId",330100 );

        return null;
    }

    @Test
    public void saveHouse_新建整租房源(){

    }

}
