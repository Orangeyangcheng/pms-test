package common;

import com.github.kevinsawicki.http.HttpRequest;
import mybatis.dao.PmsUserDao;
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


}
