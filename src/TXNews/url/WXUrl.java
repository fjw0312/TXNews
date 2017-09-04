package TXNews.url;

import java.util.HashMap;


/***
 * app:天行新闻
 * author:fjw0312 
 * E-mail:fjw0312@163.com
 * date:2017.7.26
 * 版权：个人所有
 * 
 * 类： 微信精选url 数据请求地址
 * 请求地址 服务方：mob.com
 * */
public class WXUrl {

	public WXUrl() {
		// TODO Auto-generated constructor stub
	}
	
	public static final String host = "http://apicloud.mob.com/wx/article/";
	public static final String category = "category/query";
	public static final String search = "search";
	
	public static final String key = "key=1f5f243035280";
	
	// 分类  <name-cid> 链表map
	public static HashMap<String, String>  cidMap = new HashMap<String, String>();
	//初始化 分类   赋值 链表map
	public static void initCategory(HashMap<String, String> cid_map){
		cidMap.clear();
		cidMap = cid_map;
	}


	//获取分类 信息
	public static String getCategory(){
		String url= host+category+"?"+key;
		return url;
	}
	
	//获取列表查询
	public static String getSearch(String cid){
		String url = "";
		url = host+search+"?"+key+"&cid="+cid;
		return url;
	}
	//获取列表查询  传入参数  name
	public static String getSearchName(String name){
		String url = "";
		String cid = cidMap.get(name);
		url = host+search+"?"+key+"&cid="+cid;
		return url;
	}
}
