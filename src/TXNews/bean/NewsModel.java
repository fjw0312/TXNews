package TXNews.bean;


/***
 * app:��������
 * author:fjw0312 
 * E-mail:fjw0312@163.com
 * date:2017.7.27
 * ��Ȩ����������
 * 
 * �ࣺ ����url���� ����  ����ģ��
 * �����ַ ���񷽣��ۺ�����  -- ����ͷ��
 * */
public class NewsModel {

	public NewsModel() {
		// TODO Auto-generated constructor stub
	}
	
	public final static int TYPE_A = 0;  //���ݷ��A  ��ͼ���A  1ͼ����     ע�⣺����������ô�0 ��ʼ
	public final static int TYPE_B = 1;  //���ݷ��B  ��ͼ���B  3ͼ����
	
	//���� ��������ģ��  ��Ա����
	public String id = "";                      //�Զ���  id 
	public String uniquekey = "";                
	public String title = "";                   //����
	public String date = "";                    //ʱ��
	public String category = "";                //����
	public String author_name = "";             //��Դ
	public String url = "";                     //���ӵ�ַ
	public String thumbnail_pic_s = "";         //ͼƬ��ַ
	public String thumbnail_pic_s02 = "";       //ͼƬ2��ַ  ����һ���У�
	public String thumbnail_pic_s03 = "";       //ͼƬ3��ַ  ����һ���У�
	
	public int type = TYPE_A;    //���� ����  ���ڷֱ�ʹ���ڲ�ͬ ��ͼ���

}
