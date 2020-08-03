package cn.crazyapi.Constans;

/**
 * @Author chenjiafneg
 * @Date 2020/7/28 13:40
 * @Version 1.0
 */
public class RainbowcnCrm {
    //host
    public static final  String Base_URL_DIV="dev-hlj.rainbowcn.com";
    public static final  String Base_URL_SIT="sit-hlj.rainbowcn.com";

    //登录接口
    public static  final String Rainbowcn_Logind_URL="/api/v1/dubhe-auth/user/th-idm/login";

     //查询所有事件
    public static  final  String Rainbowcn_EventAll_URL="/api/v1/scrm-member/life-cycle/event/all";

    public static  final String SaveAndSubmit_URL="/api/v1/scrm-member/life-cycle/save-and-submit";
     //驳回申请
    public static  final String Audit_URL="/api/v1/scrm-member/life-cycle/audit";

}
