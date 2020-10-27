package api_test.base_test;

import api_test.uac.UserBO;
import api_test.uac.UserToken;
import common.DataSupport;
import mybatis.pojo.PmsTenant;
import mybatis.pojo.PmsUser;


public class TestAccount {

    private static String access_token = "";


    /**
     * 账号设置
     * @param evo
     * @return
     */
    public static UserBO accountSet(String evo){
        UserBO userBO = new UserBO();
        userBO.setPhone("13175112092");
        userBO.setPwd("1234567");
        userBO.setEnv( evo );
        return userBO;
    }

    /**
     * 查询用户信息和组织信息
     * @param evo
     * @return
     */
    public static UserBO getToken(String evo){
        UserBO userBO = accountSet(evo);
        PmsUser pmsUser = DataSupport.queryUserInfoByPhone( userBO.getPhone() );
        access_token = UserToken.getToken(userBO);
        userBO.setToken( access_token );
        PmsTenant pmsTenant = DataSupport.queryTenantInfoByAdminPhone(userBO.getPhone());
        userBO.setUserName( pmsUser.getName() );
        userBO.setUserId(  pmsUser.getId()  );
        userBO.setTenantId( pmsTenant.getTenantId() );
        return userBO;
    }

    /**
     * 只获取用户token
     * @param evo
     * @param onlyToken
     * @return
     */
    public static UserBO getToken (String evo,boolean onlyToken){
        UserBO userBO = accountSet(evo);
        justGetToken(userBO);
        return userBO;
    }


    public static UserBO justGetToken(UserBO userBO){
        access_token = UserToken.getToken(userBO);
        userBO.setToken( access_token );
        return userBO;
    }
}
