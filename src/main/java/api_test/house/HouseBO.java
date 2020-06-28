package api_test.house;

public class HouseBO {

    private int communityId;

    private String latitude;

    private String longitude;

    private int houseManagerId;

    private String houseManagerName;

    private String houseManagerTel;

    private String flatBuilding;

    private String flatDoor;

    private String flatUnit;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getHouseManagerName() {
        return houseManagerName;
    }

    public void setHouseManagerName(String houseManagerName) {
        this.houseManagerName = houseManagerName;
    }

    public String getHouseManagerTel() {
        return houseManagerTel;
    }

    public void setHouseManagerTel(String houseManagerTel) {
        this.houseManagerTel = houseManagerTel;
    }

    public String getFlatBuilding() {
        return flatBuilding;
    }

    public void setFlatBuilding(String flatBuilding) {
        this.flatBuilding = flatBuilding;
    }

    public String getFlatDoor() {
        return flatDoor;
    }

    public void setFlatDoor(String flatDoor) {
        this.flatDoor = flatDoor;
    }

    public String getFlatUnit() {
        return flatUnit;
    }

    public void setFlatUnit(String flatUnit) {
        this.flatUnit = flatUnit;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public int getHouseManagerId() {
        return houseManagerId;
    }

    public void setHouseManagerId(int houseManagerId) {
        this.houseManagerId = houseManagerId;
    }
}