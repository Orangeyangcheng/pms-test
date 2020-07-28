package mybatis.pojo;

import java.util.Date;

public class Community {

    private Long id;
    /**
     * 位置名称：小区或者地名
     */
    private String communityName;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 省份ID
     */
    private Long provinceId;
    /**
     * 省名称
     */
    private String provinceName;
    /**
     * 城市ID
     */
    private Long cityId;
    /**
     * 市名称
     */
    private String cityName;
    /**
     * 地区ID
     */
    private Long areaId;
    /**
     * 区名称
     */
    private String areaName;
    /**
     * 来源：1：标准认证库 2：saas 3：快租 4：bop 5：爬虫
     */
    private Integer source;
    /**
     * 1：未认证，2：已认证，4-已被合并
     */
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Community{" +
                "id=" + id +
                ", communityName='" + communityName + '\'' +
                ", address='" + address + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", provinceId=" + provinceId +
                ", provinceName='" + provinceName + '\'' +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                ", source=" + source +
                ", status=" + status +
                '}';
    }

    public Community(Long id, String communityName, String address, String longitude, String latitude, Long provinceId, String provinceName, Long cityId, String cityName, Long areaId, String areaName, Integer source, Integer status, String notes, Date updateTime, String updateUser, String tenantName, String createUsername, String updateUsername) {
        this.id = id;
        this.communityName = communityName;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.cityId = cityId;
        this.cityName = cityName;
        this.areaId = areaId;
        this.areaName = areaName;
        this.source = source;
        this.status = status;
    }

    public Community() {
    }
}
