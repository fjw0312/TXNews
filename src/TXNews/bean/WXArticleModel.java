package TXNews.bean;


/***
 * app:��������
 * author:fjw0312 
 * E-mail:fjw0312@163.com
 * date:2017.7.27
 * ��Ȩ����������
 * 
 * �ࣺ ����url���� ����  ΢�ž�ѡ ����ģ��
 * �����ַ ���񷽣�mob.com  -- ΢�ž�ѡ
 * */
public class WXArticleModel {

	public WXArticleModel() {
		// TODO Auto-generated constructor stub
	}
	
	public final static int TYPE_A = 0;  //���ݷ��A  ��ͼ���A  ��ͼ����
	public final static int TYPE_B = 1;  //���ݷ��B  ��ͼ���B  ��ͼ����
	public final static int TYPE_C = 2;  //���ݷ��C  ��ͼ���C  ��ͼ����
	
	//΢�� ��ѡ �������� ģ��
	public String id = "";        //���� Id
	public String cid = "";       //���·���Id
	public String hitCount = "";   //�����Ķ� ��
	public String pubTime = "";    //���·���ʱ��
	public String sourceUrl = "";   //����Url
	public String subTitle = "";    //���� ����
	public String thumbnails = "";   //����ͼƬ url
	public String title = "";        //���±���
	
	public int type = TYPE_B;    //���� ����  ���ڷֱ�ʹ���ڲ�ͬ ��ͼ���

}
