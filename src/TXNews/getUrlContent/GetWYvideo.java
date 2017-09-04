package TXNews.getUrlContent;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import mybroadcast.MyBroadcastReceiver;
import TXNews.bean.VideoItemModel;
import TXNews.customView.MyVideoListView;
import TXNews.utils.MyVolleyRequestQueue;
import android.view.View;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


/***
 * app:��������
 * author:fjw0312 
 * E-mail:fjw0312@163.com
 * date:2017.8.7
 * ��Ȩ����������
 * 
 * �ࣺ ��ȡurl ���� ��Ƶ
 * ʹ�� volley ��Դ���
 * �����ַ ���񷽣����� ��Ƶ
 * */
public class GetWYvideo {

	public GetWYvideo() {
		// TODO Auto-generated constructor stub
	}
	
	 
	//��ȡ ����url jsonObject
	public static void getWYvideo(String url, String strResultId, View view){ 

	//	String url = "";
		JSONObject jsonObject = null;
		
		//1.���� RequestQueue ����
		RequestQueue mQueue = MyVolleyRequestQueue.instance(); //�õ���
		//RequestQueue mQueue = Volley.newRequestQueue(MyApplication.getContextObject());
		//2.���� JsonObjectRequest ����
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, jsonObject,
				    new myRequestListener(strResultId,view), new myRequestErrorListener() );
		//3.��Request ��ӵ�RequestQueue
		mQueue.add(jsonObjectRequest);
	}
	
	private static class myRequestListener implements Listener<JSONObject>{

		public myRequestListener(String strResultId,View view) {
			super();
			// TODO Auto-generated constructor stub
			typeId = strResultId;
			videoListView = (MyVideoListView)view; 
		}

		String typeId = "";
		MyVideoListView videoListView; 
		
				
		@Override
		public void onResponse(JSONObject response) {
			// TODO Auto-generated method stub
			
			try {
				JSONArray jsonArray = response.getJSONArray(typeId);
				if(jsonArray == null)  return;
				List<VideoItemModel> videoItemModel_lst = new ArrayList<VideoItemModel>();
				for(int i=0;i<jsonArray.length();i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					VideoItemModel videoItemModel = new VideoItemModel();
					videoItemModel.title = jsonObject.getString("title");
					videoItemModel.description = jsonObject.getString("description");
					videoItemModel.sizeHD = jsonObject.getString("sizeHD");	
					videoItemModel.mp4_url = jsonObject.getString("mp4_url");	
					videoItemModel.cover = jsonObject.getString("cover");
					
					videoItemModel_lst.add(videoItemModel);
				}
				
				//���� ��ȡ������������ �� ���� �ؼ�  
				videoListView.Update(videoItemModel_lst);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				MyBroadcastReceiver.sendBroad_Error_HAL("volley��ȡ  ������Ƶ  �������� ��������");
			} 
		}
		
	}
	private static class myRequestErrorListener implements ErrorListener{

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			MyBroadcastReceiver.sendBroad_Error_HAL("volley��ȡ ������Ƶ �������ݳ���");
		}
		
	}

}
