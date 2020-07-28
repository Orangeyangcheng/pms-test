package mybatis.dao;

import mybatis.pojo.PmsStore;
import mybatis.pojo.PmsTenant;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsTenantDao {

    //根据超管手机号获取组织信息
    PmsTenant getTenantInfo(String phone);

    //根据组织id和城市id查询当前组织在对应城市是否有门店
    List<PmsStore> getStoreInfo(@Param( "tenantId" ) int tenantId, @Param( "cityId" ) int cityId );
}
