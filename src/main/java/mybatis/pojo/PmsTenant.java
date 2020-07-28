package mybatis.pojo;

public class PmsTenant {

    private int id;

    private int tenantId;

    private String tenantName;

    private String adminPhone;

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

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public PmsTenant(int id, int tenantId, String tenantName, String adminPhone) {
        this.id = id;
        this.tenantId = tenantId;
        this.tenantName = tenantName;
        this.adminPhone = adminPhone;
    }

    @Override
    public String toString() {
        return "PmsTenant{" +
                "id=" + id +
                ", tenantId=" + tenantId +
                ", tenantName='" + tenantName + '\'' +
                ", adminPhone='" + adminPhone + '\'' +
                '}';
    }

    public PmsTenant() {
    }
}
