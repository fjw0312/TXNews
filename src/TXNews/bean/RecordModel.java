package TXNews.bean;


/**
 * �ղ� ��¼ ����ģ��
 * 
 * */
public class RecordModel {

	public RecordModel(String strUrl, String strTitle) {
		// TODO Auto-generated constructor stub
		url = strUrl;
		title = strTitle;
	}
	public RecordModel(String strUrl, String strTitle,int Id) { 
		// TODO Auto-generated constructor stub
		url = strUrl;
		title = strTitle;
		id = Id;
	}
		
	public String url = "";
	public String title = "";
	public int  id = 0;  //�б�id
	public boolean hasChoice = false;  //��ѡ��  1

}
