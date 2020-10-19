package api_test.house;

import mybatis.pojo.Community;

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

    private String communityAddress;

    private String communityName;

    private int cityId;

    private String cityName;

    private Community community;

    private int storeId;

    private String storeName;

    private int houseMode;

    private String houseCode;

    private String roomCode;

    private int tenantId;

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public int getHouseMode() {
        return houseMode;
    }

    public void setHouseMode(int houseMode) {
        this.houseMode = houseMode;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCommunityAddress() {
        return communityAddress;
    }

    public void setCommunityAddress(String communityAddress) {
        this.communityAddress = communityAddress;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

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
