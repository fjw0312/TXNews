package TXNews.url;

import java.util.ArrayList;
import java.util.List;


/***
 * app:��������
 * author:fjw0312 
 * E-mail:fjw0312@163.com
 * date:2017.7.26
 * ��Ȩ����������
 * 
 * �ࣺ �����ȵ�url ���������ַ  
 * �����ַ ���񷽣�����������      ��������������
 * */
public class TopUrl {

	public TopUrl() {
		// TODO Auto-generated constructor stub
	}
	public static final String host = "http://api.avatardata.cn/ActNews/";

	public static final String LookUp = "LookUp?";
	public static final String Query = "Query?";
	public static final String key = "key=a342376f893b47409f92d635ffab57aa";
	
	//����  �ȵ� ���ŵ�  ������������
	public List<String> title_lst = new ArrayList<String>();
	//��ʼ�� �ȵ���������
	public String init_NewsTitle(){
		String url = "";
		return url;
	}
	
	//��ȡ �ȵ�����url
	public String getUrl(){
		return host+LookUp+key;
	}
	//��ȡ  �����������url 
	public String getStringUrl(String search){
		String url = "";
		return url+Query+key+"&keyword="+search;
	}
	
}
