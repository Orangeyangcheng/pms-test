package mybatis.dao;

import mybatis.pojo.HouseRoom;

public interface PmsHouseDao {

    /**
     * 查询房间租赁状态
     * @param roomCode
     * @return
     */
    HouseRoom queryRoomInfo(String roomCode);

    /**
     * 提供组织可租房源信息
     * @param tenantId
     * @return
     */
    HouseRoom queryRentableRoomInfo(String tenantId);
}
