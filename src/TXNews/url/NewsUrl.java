package TXNews.url;


/***
 * app:天行新闻
 * author:fjw0312 
 * E-mail:fjw0312@163.com
 * date:2017.7.26
 * 版权：个人所有
 * 
 * 类： 新闻url 数据请求地址
 * 请求地址 服务方： 天行数据
 * */
public class NewsUrl {

	public NewsUrl() {
		// TODO Auto-generated constructor stub
	}
	
	public static final String host = "http://api.tianapi.com/";

	public static final String key = "key=ef3e0ab8a3e7728803b26c7a4efea22e";
	
	//新闻分类
	public static final String social = "social/";      //社会
	public static final String guonei = "guonei/";      //国内
	public static final String world = "world/";        //国际
	public static final String huabian = "huabian/";    //娱乐
	public static final String tiyu = "tiyu/";          //体育
	public static final String nba = "nba/";            //NBA
	public static final String football = "football/";  //足球
	public static final String keji = "keji/";          //科技
	public static final String startup = "startup/";    //创业
	public static final String apple = "apple/";        //苹果
	public static final String military = "military/";  //军事
	public static final String mobile = "mobile/";      //移动互联网
	public static final String travel = "travel/";      //旅游
	public static final String health = "health/";      //健康
	public static final String qiwen = "qiwen/";        //奇闻
	public static final String meinv = "meinv/";        //美女
	public static final String vr = "vr/";              //VR
	public static final String it = "it/";              //IT行业
	
	//demo:
	//url = host +social+"?"+key+"&"+"num=10";
	public String getUrl(String type, String num){ //参数： 1：新闻类型   2：请求数量  eg: NewsUrl.social  10
		String url = host +type+"?"+key+"&"+"num="+num;
		return url;
	}
	

}
