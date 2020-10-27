package mybatis.dao;

import mybatis.pojo.Community;
import mybatis.pojo.PmsUser;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface CommunityDao {
        /**
         * 随机获取小区
         * @return
         */
        Community getARandCommunity();

        /**
         * 根据城市id获取一个小区
         * @param cityId
         * @return
         */
        Community getCommunityByCity(int cityId);
}
