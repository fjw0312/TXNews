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
 * app:��������
 * author:fjw0312 
 * E-mail:fjw0312@163.com
 * date:2017.7.27
 * ��Ȩ����������
 * 
 * �ࣺ ��ȡurl ����������  
 * ʹ�� volley ��Դ���
 * �����ַ ���񷽣��ۺ�����  -- ����ͷ��
 * */
public class GetNewsModel {

	public GetNewsModel() {
		// TODO Auto-generated constructor stub
	}
	
	
	//��ȡ ����url jsonObject
	public static void getNewsModel(String url, String strResultId,  String strDataId, View view){ 

	//	String url = "";
		JSONObject jsonObject = null;
		
		//1.���� RequestQueue ����
		RequestQueue mQueue = MyVolleyRequestQueue.instance(); //�õ���
		//RequestQueue mQueue = Volley.newRequestQueue(MyApplication.getContextObject());
		//2.���� JsonObjectRequest ����
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, jsonObject,
				    new myRequestListener(strResultId,strDataId,view), new myRequestErrorListener() );
		//3.��Request ��ӵ�RequestQueue
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
					if(jsonObject.has("thumbnail_pic_s02")){   //�ж� �Ƿ��� �ڶ���ͼƬ
						newsModel.thumbnail_pic_s02 = jsonObject.getString("thumbnail_pic_s02");
					}
					if(jsonObject.has("thumbnail_pic_s03")){   //�ж� �Ƿ��� ������ͼƬ
						newsModel.thumbnail_pic_s03 = jsonObject.getString("thumbnail_pic_s03");
						newsModel.type = NewsModel.TYPE_B;   //��3��ͼ ģʽ
					}
					
					newsModel_lst.add(newsModel);	 		
				}
				
				//���� ��ȡ������������newsModel_lst
				newsListView.Update(newsModel_lst);  //ֱ�ӽ� ��ȡ������ ���µ��ؼ� 
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				MyBroadcastReceiver.sendBroad_Error_HAL("volley��ȡ�������ŷ������� ��������");
			} 
		}
		
	}
	private static class myRequestErrorListener implements ErrorListener{

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			MyBroadcastReceiver.sendBroad_Error_HAL("volley��ȡ�������ŷ������ݳ���");
		}
		
	}

}
