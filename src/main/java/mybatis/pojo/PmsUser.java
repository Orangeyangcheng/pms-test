package mybatis.pojo;

public class PmsUser {
    private int id;
    private String name;
    private String passWord;
    private String tenantId;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "PmsUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passWord='" + passWord + '\'' +
                ", tenantId=" + tenantId +
                ", phone='" + phone + '\'' +
                '}';
    }

    public PmsUser(int id, String name, String passWord, String tenantId, String phone) {
        this.id = id;
        this.name = name;
        this.passWord = passWord;
        this.tenantId = tenantId;
        this.phone = phone;
    }

    public PmsUser(){
    }


}
