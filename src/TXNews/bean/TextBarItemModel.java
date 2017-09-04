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
	
	//新闻分类  标题 标签
	public static final String top = "头条";             // "top";        //头条
	public static final String shehui = "社会";          //"shehui";      //社会
	public static final String guonei = "国内";          //"guonei";      //国内
	public static final String guoji = "国际";          //"guoji";        //国际
	public static final String yule = "娱乐";          //"yule";          //娱乐
	public static final String tiyu = "体育";          //"tiyu";          //体育
	public static final String junshi = "军事";          //"junshi";      //军事
	public static final String keji = "科技";            //"keji";          //科技
	public static final String caijing = "财经";          //"caijing";    //财经
	public static final String shishang = "时尚";          //"shishang";  //时尚
	public static final String jiankang = "健康";          //"jiankang";      //健康

}
