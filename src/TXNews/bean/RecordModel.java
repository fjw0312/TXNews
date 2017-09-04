package TXNews.bean;


/**
 * 收藏 记录 数据模型
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
	public int  id = 0;  //列表id
	public boolean hasChoice = false;  //被选择  1

}
