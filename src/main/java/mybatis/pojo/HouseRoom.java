package mybatis.pojo;

public class HouseRoom {

    private String roomCode;

    private int roomStatus;

    private String houseCode;

    private String tenantId;

    private int houseMode;

    public int getHouseMode() {
        return houseMode;
    }

    public void setHouseMode(int houseMode) {
        this.houseMode = houseMode;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public int getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(int roomStatus) {
        this.roomStatus = roomStatus;
    }

    public HouseRoom(String roomCode, int roomStatus) {
        this.roomCode = roomCode;
        this.roomStatus = roomStatus;
    }

    public HouseRoom() {
    }

    @Override
    public String toString() {
        return "HouseRoom{" +
                "roomCode='" + roomCode + '\'' +
                ", roomStatus=" + roomStatus +
                ", houseCode='" + houseCode + '\'' +
                ", tenantId=" + tenantId +
                ", houseMode=" + houseMode +
                '}';
    }


    public HouseRoom(String roomCode, int roomStatus, String houseCode, String tenantId, int houseMode) {
        this.roomCode = roomCode;
        this.roomStatus = roomStatus;
        this.houseCode = houseCode;
        this.tenantId = tenantId;
        this.houseMode = houseMode;
    }

}
