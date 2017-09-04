package TXNews.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import TXNews.url.JHUrl;

public class TextBarItemModel {

	public TextBarItemModel() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	public static void init(){
		text_lst.clear();
		text_lst.add(top);
		text_lst.add(shehui);
		text_lst.add(guonei);
		text_lst.add(guoji);
		text_lst.add(yule);
		text_lst.add(tiyu);
		text_lst.add(junshi);
		text_lst.add(keji);
		text_lst.add(caijing);
		text_lst.add(shishang);
		text_lst.add(jiankang);
		text_hash.clear();
		text_hash.put(top, JHUrl.top);
		text_hash.put(shehui, JHUrl.shehui);
		text_hash.put(guonei, JHUrl.guonei);
		text_hash.put(guoji, JHUrl.guoji);
		text_hash.put(yule, JHUrl.yule);
		text_hash.put(tiyu, JHUrl.tiyu);
		text_hash.put(junshi, JHUrl.junshi);
		text_hash.put(keji, JHUrl.keji);
		text_hash.put(caijing, JHUrl.caijing);
		text_hash.put(shishang, JHUrl.shishang);
		text_hash.put(jiankang, JHUrl.jiankang);
	}
	
	public static List<String> text_lst = new ArrayList<String>();
	public static HashMap<String, String> text_hash = new HashMap<String, String>();
	
	//���ŷ���  ���� ��ǩ
	public static final String top = "ͷ��";             // "top";        //ͷ��
	public static final String shehui = "���";          //"shehui";      //���
	public static final String guonei = "����";          //"guonei";      //����
	public static final String guoji = "����";          //"guoji";        //����
	public static final String yule = "����";          //"yule";          //����
	public static final String tiyu = "����";          //"tiyu";          //����
	public static final String junshi = "����";          //"junshi";      //����
	public static final String keji = "�Ƽ�";            //"keji";          //�Ƽ�
	public static final String caijing = "�ƾ�";          //"caijing";    //�ƾ�
	public static final String shishang = "ʱ��";          //"shishang";  //ʱ��
	public static final String jiankang = "����";          //"jiankang";      //����

}
