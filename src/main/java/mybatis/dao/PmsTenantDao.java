package mybatis.dao;

import api_test.uac.UserBO;
import mybatis.pojo.PmsStore;
import mybatis.pojo.PmsTenant;
import mybatis.pojo.PmsUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsTenantDao {

    /**
     * 根据超管手机号获取组织信息
     * @param phone
     * @return
     */
    PmsTenant getTenantInfo(String phone);

    /**
     * 根据组织id和城市id查询当前组织在对应城市是否有门店
     * @param tenantId
     * @param cityId
     * @return
     */
    List<PmsStore> getStoreInfo(@Param( "tenantId" ) int tenantId, @Param( "cityId" ) int cityId );

    /**
     * 根据手机号查询用户信息
     * @param phone
     * @return
     */
    PmsUser queryUserInfo(String phone);
}
