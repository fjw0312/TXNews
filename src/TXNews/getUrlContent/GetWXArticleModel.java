package TXNews.getUrlContent;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import mybroadcast.MyBroadcastReceiver;
import TXNews.bean.WXArticleModel;
import TXNews.customView.MyWXarticleListView;
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
 * 类： 获取url 的 微信精选数据  
 * 使用 volley 开源框架
 * 请求地址 服务方：mob.com -- 微信精选
 * */
public class GetWXArticleModel {

	public GetWXArticleModel() {
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
			newsListView = (MyWXarticleListView)view;
		}

		String typeId = "";
		String dataId = "";
		MyWXarticleListView newsListView;
				
		@Override
		public void onResponse(JSONObject response) {
			// TODO Auto-generated method stub
			List<WXArticleModel> wxArticleModel_lst = new ArrayList<WXArticleModel>();
			try {
				JSONObject jObject = response.getJSONObject(typeId);
				JSONArray jsonArray = jObject.getJSONArray(dataId);
				if(jsonArray == null)  return;
				for(int i=0;i<jsonArray.length();i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					WXArticleModel  wxArticleModel = new WXArticleModel();				
					wxArticleModel.cid = jsonObject.getString("cid");			
					wxArticleModel.id = jsonObject.getString("id");
					wxArticleModel.pubTime = jsonObject.getString("pubTime");
					wxArticleModel.sourceUrl = jsonObject.getString("sourceUrl");
					wxArticleModel.subTitle = jsonObject.getString("subTitle");				
					wxArticleModel.title = jsonObject.getString("title"); 	
					wxArticleModel.type = WXArticleModel.TYPE_B;   //默认 数据类型B  无图风格 
					if(jsonObject.has("thumbnails")){ //先判断 是否有图片
						wxArticleModel.thumbnails = jsonObject.getString("thumbnails");
						wxArticleModel.type = WXArticleModel.TYPE_A;  //设置数据类型A  有图风格
					}
					if(jsonObject.has("hitCount")){ //先判断 是否有阅读数量
						wxArticleModel.hitCount = jsonObject.getString("hitCount");
					}
					wxArticleModel_lst.add(wxArticleModel);
				}
				
				//处理 获取到的数据链表wxArticleModel_lst
				newsListView.Update(wxArticleModel_lst);  //直接将 获取的数据 更新到控件
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				MyBroadcastReceiver.sendBroad_Error_HAL("volley获取网络 微信精选  返回数据 解析出错！");
			} 
		}
		
	}
	private static class myRequestErrorListener implements ErrorListener{

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			MyBroadcastReceiver.sendBroad_Error_HAL("volley获取网络 微信精选 返回数据出错！");
		}
		
	}

}
