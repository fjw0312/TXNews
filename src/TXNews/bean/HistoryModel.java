package TXNews.bean;

import java.util.ArrayList;
import java.util.List;


/**
 * ��ʷ ��¼ ����ģ��
 * 
 * */
public class HistoryModel {

	public HistoryModel() {
		// TODO Auto-generated constructor stub
	}
	
	// ��ʷ��¼ ��������  HashMap<url��title>      HashMap<ͳһ��Դ��λ��,����>
	public static List<RecordModel> historyRecord_Map_lst = new ArrayList<RecordModel>();
	// �ղؼ�¼ ��������  HashMap<url��title>      HashMap<ͳһ��Դ��λ��,����>
	public static List<RecordModel> collectRecord_Map_lst = new ArrayList<RecordModel>();


}
