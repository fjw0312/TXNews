package TXNews.url;


/***
 * app:��������
 * author:fjw0312 
 * E-mail:fjw0312@163.com
 * date:2017.7.26
 * ��Ȩ����������
 * 
 * �ࣺ ����url ���������ַ
 * �����ַ ���񷽣��ۺ�����  -- ����ͷ�� 
 * */
public class JHUrl {

	public JHUrl() {
		// TODO Auto-generated constructor stub
	}
	
	public static final String host = "http://v.juhe.cn/toutiao/index";

	public static final String key = "key=d06e07ba96be1937d43de5ccff262cb4";
	public static final String type = "type=";
	
	//���ŷ���
	public static final String top = "top";            //ͷ��
	public static final String shehui = "shehui";      //���
	public static final String guonei = "guonei";      //����
	public static final String guoji = "guoji";        //����
	public static final String yule = "yule";          //����
	public static final String tiyu = "tiyu";          //����
	public static final String junshi = "junshi";      //����
	public static final String keji = "keji";          //�Ƽ�
	public static final String caijing = "caijing";    //�ƾ�
	public static final String shishang = "shishang";  //ʱ��
	public static final String jiankang = "jiankang";      //����

	//url 
//	url:  "https://v.juhe.cn/toutiao/index?type=shishang&key=d06e07ba96be1937d43de5ccff262cb4";
	
	//��ȡurl
	public static String getUrl(String typeValue){
		return host+"?"+type+typeValue+"&"+key;
	}

}
