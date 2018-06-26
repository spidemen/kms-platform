package com.nbclass.util;

/**
 * Created by Administrator on 2018/1/30.
 */
public class CoreConst {
    public static final String  SUCCESS            = "success";
    public static final String  FAIL            = "fail";
    public static final Integer  STATUS_VALID            = 1;
    public static final Integer  STATUS_INVALID          = 2;
    public static final String  music_sys_user      = "0";/*系统音乐的userid*/
    public static final String sys_common_role_id   ="3";/*初始化的普通用户角色id*/
    public static Integer top_menu_id         = 0;
    public static String top_menu_name       = "顶层菜单";
    public static String sys_config_cloud = "CLOUD_STORAGE_CONFIG";/*云配置*/

    /*云存储类型 1-七牛云，2-阿里，3-腾讯*/
    public static int cloudType;
    //七牛绑定的域名
    public static String qiniuDomain="";
    //七牛路径前缀
    public static String qiniuPrefix="";
    //七牛ACCESS_KEY
    public static String qiniuAccessKey="";
    //七牛SECRET_KEY
    public static String qiniuSecretKey="";
    //七牛存储空间名
    public static String qiniuBucketName="";
}
