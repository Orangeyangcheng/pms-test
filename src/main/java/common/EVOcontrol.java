package common;



public class EVOcontrol {

    //分散式房源保存
    public final static String saveHouse = "gmd.mdguanjia.com/pms-hsc/house/inner/save";
    //集中式房源保存
    public final static String saveEsate = "gmd.mdguanjia.com/pms-hsc/house/inner/save";
    //房源入住
    public final static String roomRent = "gmd.mdguanjia.com/pms-hsc/client/room/rent";
    //token获取
    public final static String oauthToken = "gmd.mdguanjia.com/pms-uac/oauth/token";
    //电子合同字典信息初始化
    public final static String dictInitUrl = "gmd.mdguanjia.com/pms-uac/configpreferencecontract/dictInit";
    //新签租约
    public final static String createLease = "gmd.mdguanjia.com/pms-omc/xq/createLease";
    //新签初始化
    public final static String xqInit = "gmd.mdguanjia.com/pms-omc/xq/xqInit?houseType";
    //获取用户信息
    public final static String getUserUrl = "gmd.mdguanjia.com/pms-uac/user/getUser";
    //添加小区
    public final static String addCommunityUrl = "gmd.mdguanjia.com/pms-mdc/library/community/house/diyInput";
    //发房渠道
    public final static String getByTenantId = "gmd.mdguanjia.com/pms-ota/channel/getByTenantId";
    //查询发房渠道
    public final static String getChannelList = "gmd.mdguanjia.com/pms-ota/channel/getChannelList";

    /**
     * 拼接环境变量和地址
     * @param evo
     * @param address
     * @return
     */
    public static String getAddress(String evo, String address){
        String URL = "";
        StringBuilder str = new StringBuilder();
        str.append( evo );
        str.append( address );
        URL = str.toString();
        return URL;
    }


    public final static String tpm1 = "http://tpm1-";

    public final static String tpm3 = "http://tpm3-";

    public final static String online = "https://";

    public static void main(String[] args) {
        System.out.println(getAddress( online , dictInitUrl));
    }
}
