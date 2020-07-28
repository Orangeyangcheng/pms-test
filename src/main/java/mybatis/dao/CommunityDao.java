package mybatis.dao;

import mybatis.pojo.Community;
import mybatis.pojo.PmsUser;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface CommunityDao {

        Community  getARandCommunity();
}
