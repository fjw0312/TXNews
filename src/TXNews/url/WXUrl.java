package TXNews.url;

import java.util.HashMap;


/***
 * app:��������
 * author:fjw0312 
 * E-mail:fjw0312@163.com
 * date:2017.7.26
 * ��Ȩ����������
 * 
 * �ࣺ ΢�ž�ѡurl ���������ַ
 * �����ַ ���񷽣�mob.com
 * */
public class WXUrl {

	public WXUrl() {
		// TODO Auto-generated constructor stub
	}
	
	public static final String host = "http://apicloud.mob.com/wx/article/";
	public static final String category = "category/query";
	public static final String search = "search";
	
	public static final String key = "key=1f5f243035280";
	
	// ����  <name-cid> ����map
	public static HashMap<String, String>  cidMap = new HashMap<String, String>();
	//��ʼ�� ����   ��ֵ ����map
	public static void initCategory(HashMap<String, String> cid_map){
		cidMap.clear();
		cidMap = cid_map;
	}


	//��ȡ���� ��Ϣ
	public static String getCategory(){
		String url= host+category+"?"+key;
		return url;
	}
	
	//��ȡ�б��ѯ
	public static String getSearch(String cid){
		String url = "";
		url = host+search+"?"+key+"&cid="+cid;
		return url;
	}
	//��ȡ�б��ѯ  �������  name
	public static String getSearchName(String name){
		String url = "";
		String cid = cidMap.get(name);
		url = host+search+"?"+key+"&cid="+cid;
		return url;
	}
}
