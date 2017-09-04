package TXNews.bean;

import java.util.ArrayList;
import java.util.List;


/**
 * 历史 记录 数据模型
 * 
 * */
public class HistoryModel {

	public HistoryModel() {
		// TODO Auto-generated constructor stub
	}
	
	// 历史记录 数据链表  HashMap<url，title>      HashMap<统一资源定位符,标题>
	public static List<RecordModel> historyRecord_Map_lst = new ArrayList<RecordModel>();
	// 收藏记录 数据链表  HashMap<url，title>      HashMap<统一资源定位符,标题>
	public static List<RecordModel> collectRecord_Map_lst = new ArrayList<RecordModel>();


}
