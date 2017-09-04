package TXNews.url;


/***
 * app:天行新闻
 * author:fjw0312 
 * E-mail:fjw0312@163.com
 * date:2017.7.26
 * 版权：个人所有
 * 
 * 类： 新闻url 数据请求地址
 * 请求地址 服务方：聚合数据  -- 新闻头条 
 * */
public class JHUrl {

	public JHUrl() {
		// TODO Auto-generated constructor stub
	}
	
	public static final String host = "http://v.juhe.cn/toutiao/index";

	public static final String key = "key=d06e07ba96be1937d43de5ccff262cb4";
	public static final String type = "type=";
	
	//新闻分类
	public static final String top = "top";            //头条
	public static final String shehui = "shehui";      //社会
	public static final String guonei = "guonei";      //国内
	public static final String guoji = "guoji";        //国际
	public static final String yule = "yule";          //娱乐
	public static final String tiyu = "tiyu";          //体育
	public static final String junshi = "junshi";      //军事
	public static final String keji = "keji";          //科技
	public static final String caijing = "caijing";    //财经
	public static final String shishang = "shishang";  //时尚
	public static final String jiankang = "jiankang";      //健康

	//url 
//	url:  "https://v.juhe.cn/toutiao/index?type=shishang&key=d06e07ba96be1937d43de5ccff262cb4";
	
	//获取url
	public static String getUrl(String typeValue){
		return host+"?"+type+typeValue+"&"+key;
	}

}
