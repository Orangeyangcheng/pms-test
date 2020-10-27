package mybatis.dao;

import mybatis.pojo.PmsUser;

import java.util.List;

public interface PmsUserDao {

    //查询全部用户
    List<PmsUser> getPmsUserList();


    //根据手机号获取用户信息
    PmsUser getUserByPhone(String phone);
}
