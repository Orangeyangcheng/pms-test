package common;

import mybatis.dao.CommunityDao;
import mybatis.dao.PmsTenantDao;
import mybatis.pojo.Community;
import mybatis.pojo.PmsStore;
import mybatis.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DataSupport {

    /**
     * 随机获取一个小区信息
     * @return
     */
    public static Community getCommunity(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        CommunityDao communityDao = sqlSession.getMapper( CommunityDao.class );
        Community community = communityDao.getARandCommunity();
        sqlSession.close();
        return community;
    }

    /**
     * 查询组织对应城市的门店信息
     * @return
     */
    public static List<PmsStore> queryPmsStore(int tenantId,int cityId){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        PmsTenantDao pmsTenantDao = sqlSession.getMapper( PmsTenantDao.class );
        List<PmsStore> pmsStores = pmsTenantDao.getStoreInfo( tenantId,cityId );
        sqlSession.close();
        return pmsStores;
    }

    public static void main(String[] args) {
        Community community = new Community();
        community =  getCommunity();
        System.out.println(community);
    }
}
