package com.rollling.net;

/**
 * @author zhangyao
 * @date 2017/11/20:18:00
 * @E-mail android_n@163.com
 */

public class HttpConfig {


    /**
     * 主站vs测试站开关
     * true：主站
     * false：测试站
     */
    static boolean isProduction = false;

    /**
     * 主域名
     */
    public static String URL_HOST = isProduction ? "https://paipianbang.com" : "https://api.paipianbang.com";
    /**
     * 我的作品前缀
     */
    public static String URL_CINEHELLO_WORKS= isProduction ? "http://cinehello.com/works/" : "http://cinehello.107cine.com/works/";
    /**
     * 测试学习
     */
    public static String URL_CINEHELLO_LEARN =  "https://study.api.cinehello.com/";

    /**
     * 圈子动态文章、视频web页前缀
     */
    public static String URL_CINEHELLO_107CINE = isProduction ? "https://cinehello.com/" : "https://cinehello.107cine.com/";
    /**
     * 登录页-关于我们web
     */
    public static String URL_WBB_ABOUT = isProduction ? "https://cinehello.com/static-pages/about.html" : "https://cinehello.107cine.com/static-pages/about.html";
    /**
     * 用户信息-作品上传引导web
     */
    public static String URL_WEB_FILM_UPLOAD = isProduction ? "https://cinehello.com/static-pages/upload-tips.html?kind=film&member_id=" : "https://cinehello.107cine.com/static-pages/upload-tips.html?kind=film&member_id=";
    /**
     * web-文章
     */
    public static String URL_ARTICLES =  "articles/";

    /**
     * web-视频
     */
    public static String URL_VIDEOS =  "videos/";
    /**
     * 分享圈子
     */
    public static String URL_SHARE_BOARD = "boards/";
    /**
     * 圈子分享用户
     */
    public static String URL_SHARE_MEMBERS = "members/";
    /**
     * 圈子分享文档
     */
    public static String URL_SHARE_RESOURCES = "resources/";

    /**
     * web-作品
     */
    public static String URL_WORKS =  "works/";

    public static String API = "/api/v2";
    /**
     * 拍片帮分享用户
     */
    public static String members = "/members/";
    /**
     * 分享需求
     */
    public static String jobs = "/jobs/";
    /**
     * 分享租赁
     */
    public static String leases = "/leases/";
    /**
     * 页数
     */
    public static String KEY_PAGE = "page";
    /**
     * 每页请求数量
     */
    public static String KEY_PAGE_PER = "per_page";
    /**
     * 每页请求数量
     */
    public static String KEY_PAGE_PER_VALUE = "20";


    public static final String X_MEMBER_TOKEN = "X-Member-Token";
    public static final String X_MEMBER_UUID = "X-Member-UUID";

    public static String APP_ID = "CNEAPP";
    public static String APP_KEY = "TEST-KEY";
    public static String ACCSEETOKEN = "token";
    public static String STR_JSON = ".json";

    public static String DEF_VIDEO_COVER = "http://dns1.cinehello.com/static/resources/default_video_package.jpg";

    /**
     * 小秘书头像
     */
    public static String DEF_HEAD_MSG_SYS = "https://dns1.cinehello.com/defaults/assistant.png";

    /**
     * 登录
     */
    public static String URL_LOGIN = URL_HOST + "/members/sign_in";
    /**
     * 登出
     */
    public static String URL_LOGOUT = URL_HOST + "/members/sign_out";
    /**
     * 注册
     */
    public static String URL_REGISTER = URL_HOST + "/api/people";
    /**
     *
     * 加载广告图
     */
    public static String URL_ACCESS_COVERS_CURRENT = URL_HOST + API + "/covers/current";
    /**
     * ACCESS_TOKEN
     */
    public static String URL_ACCESS_TOKEN = URL_HOST + API + "/access_token";
    /**
     * 获取短信验证码
     */
    public static String URL_VERIFICATION_CODE = URL_HOST + API + "/members/get_verification_code";
    /**
     * 获取启动页广告图
     */
    public static String URL_CURRENT = URL_HOST + API + "/covers/current";
    /**
     * 首页最新动态
     */
    public static String URL_SHORTCUTS = URL_HOST + API + "/shortcuts";
    /**
     * 获取指定用户信息
     */
    public static String URL_MEMBERS_SEARCH = URL_HOST + API + "/members/search";
    /**
     * 获取用户信息
     */
    public static String URL_MEMBERS = URL_HOST + API + "/members";
    /**
     * 资源库-个人
     */
    public static String URL_MEMBER_BUSINESSES_SEARCH = URL_HOST + API + "/member_businesses/search";
    /**
     * 获取是否微信绑定
     */
    public static String URL_WECHAT_SINGIN = URL_HOST + "/members/auth/wechat/login.json";

//    public static String URL_WECHAT_SINGIN = "http://wxlogin.test.paipianbang.com:6060" + "/members/auth/wechat/login.json";
    /**
     * 微信登录用户绑定（新用户，未有账号）
     */
    public static String URL_MEMBE_BIND_WECHET = URL_HOST + "/members/auth/integrate.json";
    /**
     * 微信登录用户绑定(已有账号)
     */
    public static String URL_MEMBE_BIND_WECHET_SSO = URL_HOST + "/sso/set_mobile.json";
    /**
     * 微信登录创建用户
     */
    public static String URL_CREATE_FROM_WECHAT = URL_HOST + API + "/members/create_from_wechat";
    /**
     * 筛选-拍过的作品
     */
    public static String URL_FILM_CATES = URL_HOST + API + "/film_cates";
    /**
     * 筛选-工作类型-个人
     */
    public static String URL_BUSINESSES = URL_HOST + API + "/businesses/tree/person";
    /**
     * 筛选-工作类型-机构
     */
    public static String URL_BUSINESSES_ORG = URL_HOST + API + "/businesses/tree/organization";

    /**
     * 筛选-类型-资源
     */
    public static String URL_BUSINESSES_LIBRAY = URL_HOST + API + "/lease_cates";


    /**
     * 筛选-工作类型-租赁
     */
    public static String URL_HOST_LEASE_CATES = URL_HOST + API + "/lease_cates";
    /**
     * 筛选-符合条件的条目数
     */
    public static String URL_BUSINESSES_ANALYSIS = URL_HOST + API + "/member_businesses/analysis";
    /**
     * 筛选-租赁-条目数
     */
    public static String URL_LEASES_ANALYSIS_KIND = URL_HOST + API + "/leases/analysis";
    /**
     * 筛选-租赁-
     */
    public static String URL_LEASES_SEARCH = URL_HOST + API + "/leases/search/";
    /**
     * 筛选-租赁-全部
     */
    public static String URL_LEASES_SEARCH_EQUIPMENT = "equipment";
    /**
     * 筛选-租赁-特种拍摄
     */
    public static String URL_LEASES_SEARCH_SHOOT = "shoot";
    /**
     * 筛选-租赁-录音棚
     */
    public static String URL_LEASES_SEARCH_RECORDING = "recording";
    /**
     * 筛选-租赁- 摄影棚
     */
    public static String URL_LEASES_SEARCH_FILM = "film";
    /**
     * 筛选-获取城市列表
     */
    public static String URL_AREAS_LEVEL = URL_HOST + "/api/areas/level/";
    /**
     * 用户信息-与用户的关注状态
     */
    public static String URL_FOLLOWS = URL_HOST + API + "/follows/";
    /**
     * 用户信息-简介
     */
    public static String URL_MEMBER_BUSINESSES = "/member_businesses";
    /**
     * 用户信息-作品
     */
    public static String URL_MEMBER_FILMOGRAPHIES = "/films";
    /**
     * 用户信息-见组
     */
    public static String URL_MEMBER_INTERVIEWS = "/interviews";
    /**
     * 发布的需求列表
     */
    public static String URL_MEMBER_NEED = URL_HOST + API + "/publications/search";
    /**
     * 获取语言列表
     */
    public static String URL_LANGUAGES = URL_HOST + API + "/languages";
    /**
     * api
     */
    public static String URL_API = URL_HOST + API;
    /**
     * api
     */
    public static String URL_API_SHARE = URL_HOST + API + "/snapshots/";
    /**
     * 需求-收藏—后缀
     */
    public static String URL_ADD_FAVORITE = "/add_favorite";
    /**
     * 需求-删除收藏—后缀
     */
    public static String URL_REMOVE_FAVORITE = "/remove_favorite";
    /**
     * 用户收藏列表
     */
    public static String URL_FAVORITE = URL_HOST + API + "/favorites";
    /**
     * 修改需求状态
     */
    public static String URL_PUBLICATION_UPDATA = URL_HOST + API + "/publications";

    /**
     * 修改需求状态
     */
    public static String URL_PUBLICATION_REFERESH = "/refresh";
    /**
     * 租赁
     */
    public static String URL_LEASES = "/leases";
    /**
     * 我关注的
     */
    public static String URL_MY_FOLLOWS = HttpConfig.URL_FOLLOWS + "follows";
    /**
     * 关注我的
     */
    public static String URL_MY_FOLLOWERS = HttpConfig.URL_FOLLOWS + "followers";
    /**
     * 需求列表
     */
    public static String URL_MY_PUBLICATION_SEARCH = URL_HOST + API + "/publications/search";
    /**
     * 项目列表
     */
    public static String URL_MY_PROJECTS = URL_HOST + API + "/projects";
    /**
     * 订单
     */
    public static String URL_ORDER = URL_HOST + API + "/orders";
    /**
     * 我的接单列表
     */
    public static String URL_HOST_MEMBERS = URL_HOST + API + "/members";
    /**
     * 接单
     */
    public static String URL_HOST_PUBLICATION = URL_HOST + API + "/publications/";
    /**
     * 用户信息&用户工作类型
     */
    public static String URL_HOST_MEMBERS_UPDATA = URL_HOST + API + "/members";
    /**
     * 获取见组七牛上传token
     */
    public static String URL_HOST_MEMBERS_QINIU_UPLOAD_TOKEN = URL_HOST + API + "/interviews/qiniu_upload_token";
    /**
     * 获取头像七牛上传token
     */
    public static String URL_HOST_MEMBERS_QINIU_UPLOAD_TOKEN_MEMBERS = URL_HOST + API + "/members/qiniu_upload_token";
    /**
     * 用户工作类型
     */
    public static String URL_HOST_MEMBERS_BUSINESSES = URL_HOST + API + "/member_businesses";
    /**
     * 作品
     */
    public static String URL_HOST_FIMOGRAPHIES = URL_HOST + API + "/films";
    /**
    * 编辑作品
     */
    public static String URL_HOST_FIMOGRAPHIES_EDIT = "/edit";
    /**
     * 查作品是否存在
     */
    public static String URL_HOST_FILMS_SEARCH = URL_HOST + API + "/films/search";
    /**
     * 签到排行榜
     */
    public static String URL_HOST_CHECKONS_CHARTS = URL_HOST + API + "/checkins/charts";
    /**
     * 签到
     */
    public static String URL_HOST_CHECKINS = URL_HOST + API + "/checkins";
    /**
     * 提交身份资料
     */
    public static String URL_HOST_AUTHORITIES = URL_HOST + API + "/authorities";
    /**
     * 提交身份资料-认证作品
     */
    public static String URL_HOST_AUTHORITIES_FILMOGRAPHY = URL_HOST + API + "/authorities/batch_create/filmography";
    /**
     * 消息
     */
    public static String URL_HOST_MESSAGE = URL_HOST + API + "/messages";
    /**
     * 会话详情
     */
    public static String URL_HOST_MESSAGE_DIALOGUE = URL_HOST + API + "/messages/dialogue/";
    /**
     * 标记会话已读
     */
    public static String URL_HOST_MESSAGE_READ = URL_HOST + API + "/messages/set_read_by_contact/";

    /**
     * 我的见组列表
     */
    public static String URL_MEMBER_INTERVIEWS_LIST = URL_HOST + API + "/interviews";
    /**
     * 修改密码
     */
    public static String URL_HOST_CHAND_PSD= "/change_password";
    /**
     * 找回密码
     */
    public static String URL_HOST_FORGET_PSD= "/forget_password";
    /**
     * 解除微信绑定
     */
    public static String URL_HOST_UNBIND_WECHAT= "/unbind_wechat";
    /**
     * 绑定手机号
     */
    public static String URL_MEMBER_MEMBER_BIND_MOBILE = URL_HOST + API + "/members/bind_mobile";
    /**
     *用户统计信息
     */
    public static String URL_HOST_STATISTICS = URL_HOST + API + "/members/statistics";
    /**
     *需求详情-推荐
     */
    public static String URL_CANDIDATES = "/candidates";
    /**
     * 首页-学习课程列表
     */
    public static String URL_HOST_LEARN_LIST = URL_CINEHELLO_LEARN + "api/v1/members/";
    /**
     * 首页-学习课程列表-后缀
     */
    public static String URL_HOST_LEARN_COURSES = "courses?";
    /**
     * 学习课程目录列表
     */
    public static String URL_HOST_LEARN_CONTENTS_LIST = URL_CINEHELLO_LEARN + "api/v1/courses/";
    /**
     * 用户详情-推介用户数量界面-后缀
     */
    public static String URL_HOST_USER_RECOMMEND = "/comments/rank/good";
    /**
     * 圈子
     */
    public static String URL_HOST_BOARDS = URL_HOST + API + "/boards/";
    /**
     * 发现-推荐圈子
     */
    public static String URL_HOST_BOARDS_RECOMMEND = URL_HOST_BOARDS + "recommend";
    /**
     * 动态图文
     */
    public static String URL_HOST_ACTIVITIES = URL_HOST + API + "/activities";
    /**
     * 发现-推荐热门图文
     */
    public static String URL_HOST_ACTIVITIES_HOT = URL_HOST + API + "/activities/hot_posts";
    /**
     * 发现-推荐圈子-搜索
     */
    public static String URL_HOST_BOARDS_SEARCH = URL_HOST_BOARDS + "search";
    /**
     * 圈子-公告
     */
    public static String URL_HOST_BOARDS_NOTICES = "/notices";
    /**
     * 圈子-加入的用户
     */
    public static String URL_HOST_BOARDS_MEMBERS = URL_HOST_BOARDS + "members";

    /**
     * 圈子-加入的用户
     */
    public static String URL_HOST_BOARDS_FOOTPRINTS = URL_HOST + API + "/footprints";
    /**
     * 圈子-工作类型
     */
    public static String URL_BUSINESSES_COMMUNITY = URL_HOST + API + "/businesses/tree/community";
    /**
     * 圈子-城市
     */
    public static String URL_BUSINESSES_AREAS_TREE = URL_HOST + API + "/areas/tree";
    /**
     * 圈子-用户列表-角色
     */
    public static String URL_BUSINESSES_IDENTITY = "/identity";
    /**
     * 圈子-创建-上传图片到七牛-获取token
     */
    public static String URL_HOST_QINIU_UPLOAD_TOKEN = URL_HOST + API + "/boards/qiniu_upload_token";
    /**
     * 圈子-创建-上传图片到七牛-获取token
     */
    public static String URL_HOST_MEDIA_QINIU_UPLOAD_TOKEN = URL_HOST + API + "/media/qiniu_upload_token";
    /**
     * 圈子-首页
     */
    public static String URL_HOST_BOARDS_MEMBERS_HOME = URL_HOST + API + "/members/";
    /**
     * 圈子-首页
     */
    public static String URL_BOARDS = "/boards";
    /**
     * 加入圈子
     */
    public static String URL_BOARDS_JOIN = "/join";
    /**
     * 圈子-入圈申请
     */
    public static String URL_HOST_BOARDS_MEMBERS_APPLICATIONS = URL_HOST + API + "/member_applications";
    /**
     * 圈子-返回当前用户的社交状况
     */
    public static String URL_HOST_BOARDS_SOCIALITY = URL_HOST + API + "/follows/sociality";
    /**
     * 个人信息-获取加入的圈子
     */
    public static String URL_HOST_BOARDS_INTROS = URL_HOST + API + "/member_board_intros";
    /**
     * 圈子-获取-个人信息
     */
    public static String URL_HOST_BOARDS_INTROS_HOMEPAGES = URL_HOST + API + "/homepages/";
    /**
     * 把用户移出圈子
     */
    public static String URL_BOARDS_DEL = URL_HOST + API  + "/boards";
    /**
     * 创建文章-封面图
     */
    public static String URL_BOARDS_CREATE_ARTICLE = URL_HOST + API  + "/boards/qiniu_upload_token";
    /**
     * 创建帖子-上传图片
     */
    public static String URL_BOARDS_CREATE_IMG = URL_HOST + API  + "/posts/qiniu_upload_token";
    /**
     * 创建视频
     */
    public static String URL_BOARDS_CREATE_POSTS = URL_HOST + API  + "/posts";
    /**
     * 上传文档token
     */
    public static String URL_BOARDS_RESOURCES_QINIU_UPLOAD_TOKEN = URL_HOST + API  + "/resources/qiniu_upload_token";
    /**
     *返回多媒体资源所有类别
     */
    public static String URL_MEDIA_KINDS = URL_HOST + API  + "/media/kinds";
    /**
     * 圈子帖子动态列表
     */
    public static String URL_BOARDS_POSTS = URL_HOST + API  +"/posts/";
    /**
     * 帖子点赞
     */
    public static String URL_BOARDS_POSTS_ADD_FAVORITE = "/add_favorite";
    /**
     * 取消帖子点赞
     */
    public static String URL_BOARDS_POSTS_REMOVE_FAVORITE = "/remove_favorite";
    /**
     * 获取用户的通知列表-影工小秘书
     */
    public static String URL_MSG_INFORMATION= URL_HOST + API + "/information";
    /**
     * 影工小秘书-已读
     */
    public static String URL_MSG_INFORMATION_READ = URL_HOST + API + "/information/set_all_read";
    /**
     * 文档详情
     */
    public static String URL_DOC_RESOUBRCES = URL_HOST + API + "/resources";
    /**
     * 取消关注
     */
    public static String URL_BOARDS_QUIT = "/quit";
    /**
     * 置顶文章
     */
    public static String URL_BOARDS_ADD_SUGGEST = "/add_suggest";
    /**
     * 取消置顶文章
     */
    public static String URL_BOARDS_REMOVE_SUGGEST = "/remove_suggest";
    /**
     * 分享文章
     */
    public static String URL_LINKS = URL_HOST + API + "/links";
    /**
     * 获取谁看了这篇文章的用户列表
     */
    public static String URL_SEARCH_POST = URL_HOST + API + "/tracks/search/post/";
}
