package TXNews.url;


/***
 * app:��������
 * author:fjw0312 
 * E-mail:fjw0312@163.com
 * date:2017.7.26
 * ��Ȩ����������
 * 
 * �ࣺ ����url ���������ַ
 * �����ַ ���񷽣� ��������
 * */
public class NewsUrl {

	public NewsUrl() {
		// TODO Auto-generated constructor stub
	}
	
	public static final String host = "http://api.tianapi.com/";

	public static final String key = "key=ef3e0ab8a3e7728803b26c7a4efea22e";
	
	//���ŷ���
	public static final String social = "social/";      //���
	public static final String guonei = "guonei/";      //����
	public static final String world = "world/";        //����
	public static final String huabian = "huabian/";    //����
	public static final String tiyu = "tiyu/";          //����
	public static final String nba = "nba/";            //NBA
	public static final String football = "football/";  //����
	public static final String keji = "keji/";          //�Ƽ�
	public static final String startup = "startup/";    //��ҵ
	public static final String apple = "apple/";        //ƻ��
	public static final String military = "military/";  //����
	public static final String mobile = "mobile/";      //�ƶ�������
	public static final String travel = "travel/";      //����
	public static final String health = "health/";      //����
	public static final String qiwen = "qiwen/";        //����
	public static final String meinv = "meinv/";        //��Ů
	public static final String vr = "vr/";              //VR
	public static final String it = "it/";              //IT��ҵ
	
	//demo:
	//url = host +social+"?"+key+"&"+"num=10";
	public String getUrl(String type, String num){ //������ 1����������   2����������  eg: NewsUrl.social  10
		String url = host +type+"?"+key+"&"+"num="+num;
		return url;
	}
	

}
