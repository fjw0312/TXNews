package TXNews.bean;


/***
 * app:天行新闻
 * author:fjw0312 
 * E-mail:fjw0312@163.com
 * date:2017.7.27
 * 版权：个人所有
 * 
 * 类： 新闻url返回 数据  微信精选 文章模型
 * 请求地址 服务方：mob.com  -- 微信精选
 * */
public class WXArticleModel {

	public WXArticleModel() {
		// TODO Auto-generated constructor stub
	}
	
	public final static int TYPE_A = 0;  //数据风格A  视图风格A  有图标题
	public final static int TYPE_B = 1;  //数据风格B  视图风格B  无图标题
	public final static int TYPE_C = 2;  //数据风格C  视图风格C  有图标题
	
	//微信 精选 返回数据 模型
	public String id = "";        //文章 Id
	public String cid = "";       //文章分类Id
	public String hitCount = "";   //文章阅读 数
	public String pubTime = "";    //文章发表时间
	public String sourceUrl = "";   //文章Url
	public String subTitle = "";    //文章 主题
	public String thumbnails = "";   //文章图片 url
	public String title = "";        //文章标题
	
	public int type = TYPE_B;    //数据 类型  用于分辨使用于不同 视图风格

}
