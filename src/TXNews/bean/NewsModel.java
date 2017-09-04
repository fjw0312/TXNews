package TXNews.bean;


/***
 * app:天行新闻
 * author:fjw0312 
 * E-mail:fjw0312@163.com
 * date:2017.7.27
 * 版权：个人所有
 * 
 * 类： 新闻url返回 数据  新闻模型
 * 请求地址 服务方：聚合数据  -- 新闻头条
 * */
public class NewsModel {

	public NewsModel() {
		// TODO Auto-generated constructor stub
	}
	
	public final static int TYPE_A = 0;  //数据风格A  视图风格A  1图标题     注意：数据类型最好从0 开始
	public final static int TYPE_B = 1;  //数据风格B  视图风格B  3图标题
	
	//新闻 返回数据模型  成员变量
	public String id = "";                      //自定义  id 
	public String uniquekey = "";                
	public String title = "";                   //标题
	public String date = "";                    //时间
	public String category = "";                //分类
	public String author_name = "";             //来源
	public String url = "";                     //链接地址
	public String thumbnail_pic_s = "";         //图片地址
	public String thumbnail_pic_s02 = "";       //图片2地址  （不一定有）
	public String thumbnail_pic_s03 = "";       //图片3地址  （不一定有）
	
	public int type = TYPE_A;    //数据 类型  用于分辨使用于不同 视图风格

}
