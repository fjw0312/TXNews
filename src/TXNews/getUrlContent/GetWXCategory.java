package TXNews.getUrlContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import mybroadcast.MyBroadcastReceiver;
import TXNews.customView.MyHorizontalListView;
import TXNews.url.WXUrl;
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
 * 类： 获取url 的 微信精选新闻数据  
 * 使用 volley 开源框架
 * 请求地址 服务方：mob.com  -- 微信精选
 * */
public class GetWXCategory {

	public GetWXCategory() {
		// TODO Auto-generated constructor stub
	}
	
	 
	//获取 网络url jsonObject
	public static void getWXCategory(String url, String strResultId, View view){ 

	//	String url = "";
		JSONObject jsonObject = null;
		
		//1.创建 RequestQueue 对象
		RequestQueue mQueue = MyVolleyRequestQueue.instance(); //用单例
		//RequestQueue mQueue = Volley.newRequestQueue(MyApplication.getContextObject());		
		//2.创建 JsonObjectRequest 对象
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, jsonObject,
				    new myRequestListener(strResultId,view), new myRequestErrorListener() );
		//3.将Request 添加到RequestQueue
		mQueue.add(jsonObjectRequest);
	}
	
	private static class myRequestListener implements Listener<JSONObject>{

		public myRequestListener(String strResultId,View view) {
			super();
			// TODO Auto-generated constructor stub
			typeId = strResultId;
			horizontalListView = (MyHorizontalListView)view;
		}

		String typeId = "";
		HashMap<String, String>  cidMap;
		MyHorizontalListView horizontalListView; 
		List<String> text_lst;
				
		@Override
		public void onResponse(JSONObject response) {
			// TODO Auto-generated method stub
			
			try {
				JSONArray jsonArray = response.getJSONArray(typeId);	
				if(jsonArray == null)  return;
				cidMap = new HashMap<String, String>();
				text_lst = new ArrayList<String>();
				for(int i=0;i<jsonArray.length();i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					
					String cid = jsonObject.getString("cid");
					String name = jsonObject.getString("name");
						
					cidMap.put(name,cid); 	
					text_lst.add(name);
				}
				
				//处理 获取到的数据链表 及 更新 控件
				WXUrl.initCategory(cidMap);
				horizontalListView.Update(text_lst);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				MyBroadcastReceiver.sendBroad_Error_HAL("volley获取  微信精选分类  返回数据 解析出错！");
			} 
		}
		
	}
	private static class myRequestErrorListener implements ErrorListener{

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			MyBroadcastReceiver.sendBroad_Error_HAL("volley获取 微信精选分类 返回数据出错！");
		}
		
	}

}
