package common;

import com.github.kevinsawicki.http.HttpRequest;
import mybatis.dao.CommunityDao;
import mybatis.dao.PmsTenantDao;
import mybatis.dao.PmsUserDao;
import mybatis.pojo.Community;
import mybatis.pojo.PmsStore;
import mybatis.pojo.PmsTenant;
import mybatis.pojo.PmsUser;
import mybatis.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.util.List;

public class test {

    @Test
    public void apitest(){
        String response = HttpRequest.get("https://www.baidu.com").body();
        System.out.println(response);
    }

    @Test
    public void mysqlTest(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        PmsUserDao pmsUserDao = sqlSession.getMapper(PmsUserDao.class);
        List<PmsUser> pmsUsers = pmsUserDao.getPmsUserList();

        for (PmsUser pmsUser :pmsUsers){
            System.out.println(pmsUser);
        }
        sqlSession.close();
    }

    @Test
    public void getUserTest(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        PmsUserDao pmsUserDao = sqlSession.getMapper(PmsUserDao.class);
        PmsUser pmsUsers = pmsUserDao.getUserByPhone("13175112091");
        System.out.println( pmsUsers );
        sqlSession.close();
    }

    @Test
    public void getCommunity(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        CommunityDao communityDao = sqlSession.getMapper( CommunityDao.class );
        Community community = communityDao.getARandCommunity();
        System.out.println( community );
        sqlSession.close();
    }

    @Test
    public void getTenantInfo(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        PmsTenantDao pmsTenantDao = sqlSession.getMapper( PmsTenantDao.class );
        PmsTenant pmsTenant = pmsTenantDao.getTenantInfo( "13133373338" );
        System.out.println( pmsTenant );
        sqlSession.close();
    }

    @Test
    public void getPmsStorefo(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        PmsTenantDao pmsTenantDao = sqlSession.getMapper( PmsTenantDao.class );
        List<PmsStore> pmsStores = pmsTenantDao.getStoreInfo( 95023566,330100 );
        for (PmsStore pmsStore:pmsStores){
            System.out.println( pmsStore );
        }
        sqlSession.close();
    }


}
