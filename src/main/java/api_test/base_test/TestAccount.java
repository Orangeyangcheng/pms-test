package api_test.base_test;

import api_test.uac.UserBO;
import api_test.uac.UserToken;
import common.DataSupport;
import mybatis.pojo.PmsTenant;
import mybatis.pojo.PmsUser;

import static api_test.uac.UserToken.tmp1;

public class TestAccount {

    private static String access_token = "";

    /**
     * 测试账号及环境配置
     * @return
     */
    public static UserBO getToken (){
        UserBO userBO = new UserBO();
        userBO.setPhone("13133373338");
        userBO.setPwd("1234567");
        userBO.setEnv( tmp1 );
        PmsUser pmsUser = DataSupport.queryUserInfoByPhone( userBO.getPhone() );
        access_token = UserToken.getToken(userBO);
        userBO.setToken( access_token );
        PmsTenant pmsTenant = DataSupport.queryTenantInfoByAdminPhone(userBO.getPhone());
        userBO.setUserName( pmsUser.getName() );
        userBO.setUserId(  pmsUser.getId()  );
        userBO.setTenantId( pmsTenant.getTenantId() );
        return userBO;
    }
}
