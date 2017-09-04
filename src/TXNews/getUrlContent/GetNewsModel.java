package TXNews.getUrlContent;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import mybroadcast.MyBroadcastReceiver;
import TXNews.bean.NewsModel;
import TXNews.customView.MyNewsListView;
import TXNews.utils.MyVolleyRequestQueue;
import android.view.View;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


/***
 * app:天行新闻
 * author:fjw0312 
 * E-mail:fjw0312@163.com
 * date:2017.7.27
 * 版权：个人所有
 * 
 * 类： 获取url 的新闻数据  
 * 使用 volley 开源框架
 * 请求地址 服务方：聚合数据  -- 新闻头条
 * */
public class GetNewsModel {

	public GetNewsModel() {
		// TODO Auto-generated constructor stub
	}
	
	
	//获取 网络url jsonObject
	public static void getNewsModel(String url, String strResultId,  String strDataId, View view){ 

	//	String url = "";
		JSONObject jsonObject = null;
		
		//1.创建 RequestQueue 对象
		RequestQueue mQueue = MyVolleyRequestQueue.instance(); //用单例
		//RequestQueue mQueue = Volley.newRequestQueue(MyApplication.getContextObject());
		//2.创建 JsonObjectRequest 对象
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, jsonObject,
				    new myRequestListener(strResultId,strDataId,view), new myRequestErrorListener() );
		//3.将Request 添加到RequestQueue
		mQueue.add(jsonObjectRequest);
	}
	
	private static class myRequestListener implements Listener<JSONObject>{

		public myRequestListener(String strResultId,String strDataId,View view) {
			super();
			// TODO Auto-generated constructor stub
			typeId = strResultId;
			dataId = strDataId;
			newsListView = (MyNewsListView)view;
		}

		String typeId = "";
		String dataId = "";
		MyNewsListView newsListView;
				
		@Override
		public void onResponse(JSONObject response) {
			// TODO Auto-generated method stub
			List<NewsModel> newsModel_lst = new ArrayList<NewsModel>();
			try {
				JSONObject jObject = response.getJSONObject(typeId);
				JSONArray jsonArray = jObject.getJSONArray(dataId);
				if(jsonArray == null)  return;
				for(int i=0;i<jsonArray.length();i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					NewsModel newsModel = new NewsModel();
					newsModel.id =  String.valueOf(i);
					newsModel.uniquekey = jsonObject.getString("uniquekey");
					newsModel.title = jsonObject.getString("title");
					newsModel.date = jsonObject.getString("date");
					newsModel.category = jsonObject.getString("category");
					newsModel.author_name = jsonObject.getString("author_name");
					newsModel.url = jsonObject.getString("url");
					newsModel.thumbnail_pic_s = jsonObject.getString("thumbnail_pic_s");
					if(jsonObject.has("thumbnail_pic_s02")){   //判断 是否有 第二张图片
						newsModel.thumbnail_pic_s02 = jsonObject.getString("thumbnail_pic_s02");
					}
					if(jsonObject.has("thumbnail_pic_s03")){   //判断 是否有 第三张图片
						newsModel.thumbnail_pic_s03 = jsonObject.getString("thumbnail_pic_s03");
						newsModel.type = NewsModel.TYPE_B;   //有3张图 模式
					}
					
					newsModel_lst.add(newsModel);	 		
				}
				
				//处理 获取到的数据链表newsModel_lst
				newsListView.Update(newsModel_lst);  //直接将 获取的数据 更新到控件 
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				MyBroadcastReceiver.sendBroad_Error_HAL("volley获取网络新闻返回数据 解析出错！");
			} 
		}
		
	}
	private static class myRequestErrorListener implements ErrorListener{

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			MyBroadcastReceiver.sendBroad_Error_HAL("volley获取网络新闻返回数据出错！");
		}
		
	}

}
