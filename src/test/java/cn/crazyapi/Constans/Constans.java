package cn.crazyapi.Constans;

//crm后台接口
public class Constans {
    //host
    public static final  String Base_URL_DIV="dev-api.aeonbuy.com";

    public static final  String Base_URL_SIT="sit-api.aeonbuy.com";

    //登录
    public static final  String Login_UR="/api/app-api/member/account/login";

    //会员发送消息
    public static final String SendSms_URL="/api/app-api/sms/send";

    //会员注册
    public  static final String Regist_URL="/member/memberRegist";

    //个人信息
    public static final  String MyInfo_URL="/api/app-api/member/personal/my/info";

    //积分明细
    public static final  String PointRecord_URL="/api/app-api/member/card/point/record/listByMemberId";

    //会籍列表
    public static final  String MembershipList_URL="/api/app-api/member/card/point/main";

    //查看会员信息
    public static final  String Personal_URL="/api/app-api/member/personal/main/info";

    //修改个人信息
    public static final  String PersonalUpdate_URL="/api/app-api/member/personal/info/update";

    //会籍详情
    public static final  String CorporationDetail_URL="/api/app-api/member/card/point/corporation/detail";

    //计分/扣分
    public static final  String PointDeduct_URL="/api/app-api/member/pointcalc/pointDeduct";




}
