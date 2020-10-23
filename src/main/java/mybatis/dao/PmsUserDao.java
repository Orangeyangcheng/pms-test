package mybatis.dao;

import mybatis.pojo.PmsUser;
import mybatis.util.Mapper;

import java.util.List;

public interface PmsUserDao extends Mapper {

    //查询全部用户
    List<PmsUser> getPmsUserList();


    //根据手机号获取用户信息
    PmsUser getUserByPhone(String phone);
}
