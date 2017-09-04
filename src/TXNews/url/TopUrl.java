package TXNews.url;

import java.util.ArrayList;
import java.util.List;


/***
 * app:天行新闻
 * author:fjw0312 
 * E-mail:fjw0312@163.com
 * date:2017.7.26
 * 版权：个人所有
 * 
 * 类： 新闻热点url 数据请求地址  
 * 请求地址 服务方：阿凡达数据      可用于新闻搜索
 * */
public class TopUrl {

	public TopUrl() {
		// TODO Auto-generated constructor stub
	}
	public static final String host = "http://api.avatardata.cn/ActNews/";

	public static final String LookUp = "LookUp?";
	public static final String Query = "Query?";
	public static final String key = "key=a342376f893b47409f92d635ffab57aa";
	
	//定义  热点 新闻的  标题新闻数组
	public List<String> title_lst = new ArrayList<String>();
	//初始化 热点新闻数组
	public String init_NewsTitle(){
		String url = "";
		return url;
	}
	
	//获取 热点新闻url
	public String getUrl(){
		return host+LookUp+key;
	}
	//获取  具体检索新闻url 
	public String getStringUrl(String search){
		String url = "";
		return url+Query+key+"&keyword="+search;
	}
	
}
