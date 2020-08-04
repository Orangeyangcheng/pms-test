package mybatis.dao;

import mybatis.pojo.HouseRoom;

public interface PmsHouseDao {

    HouseRoom queryRoomInfo(String roomCode);
}
