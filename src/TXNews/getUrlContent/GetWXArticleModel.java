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
 * app:��������
 * author:fjw0312 
 * E-mail:fjw0312@163.com
 * date:2017.7.27
 * ��Ȩ����������
 * 
 * �ࣺ ��ȡurl �� ΢�ž�ѡ����  
 * ʹ�� volley ��Դ���
 * �����ַ ���񷽣�mob.com -- ΢�ž�ѡ
 * */
public class GetWXArticleModel {

	public GetWXArticleModel() {
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
					wxArticleModel.type = WXArticleModel.TYPE_B;   //Ĭ�� ��������B  ��ͼ��� 
					if(jsonObject.has("thumbnails")){ //���ж� �Ƿ���ͼƬ
						wxArticleModel.thumbnails = jsonObject.getString("thumbnails");
						wxArticleModel.type = WXArticleModel.TYPE_A;  //������������A  ��ͼ���
					}
					if(jsonObject.has("hitCount")){ //���ж� �Ƿ����Ķ�����
						wxArticleModel.hitCount = jsonObject.getString("hitCount");
					}
					wxArticleModel_lst.add(wxArticleModel);
				}
				
				//���� ��ȡ������������wxArticleModel_lst
				newsListView.Update(wxArticleModel_lst);  //ֱ�ӽ� ��ȡ������ ���µ��ؼ�
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				MyBroadcastReceiver.sendBroad_Error_HAL("volley��ȡ���� ΢�ž�ѡ  �������� ��������");
			} 
		}
		
	}
	private static class myRequestErrorListener implements ErrorListener{

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			MyBroadcastReceiver.sendBroad_Error_HAL("volley��ȡ���� ΢�ž�ѡ �������ݳ���");
		}
		
	}

}
