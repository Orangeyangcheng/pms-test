package mybatis.pojo;

public class PmsStore {

    private int id;

    private int tenantId;

    private String deptName;

    private int provinceId;

    private int cityId;

    private int countyId;

    private String address;

    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public PmsStore(int id, int tenantId, String deptName, int provinceId, int cityId, int countyId, String address, String remark) {
        this.id = id;
        this.tenantId = tenantId;
        this.deptName = deptName;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.countyId = countyId;
        this.address = address;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "PmsStore{" +
                "id=" + id +
                ", tenantId=" + tenantId +
                ", deptName='" + deptName + '\'' +
                ", provinceId=" + provinceId +
                ", cityId=" + cityId +
                ", countyId=" + countyId +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public PmsStore() {
    }
}
