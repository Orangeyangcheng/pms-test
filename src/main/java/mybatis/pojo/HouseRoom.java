package mybatis.pojo;

public class HouseRoom {

    private String roomCode;

    private int roomStatus;

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
                '}';
    }
}
