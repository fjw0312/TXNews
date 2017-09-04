package TXNews.utils;

import myapplication.MyApplication;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * ȫ�� Volley ������� ����
 * 
 * */
public class MyVolleyRequestQueue {

	public static RequestQueue mQueue;
	public static synchronized RequestQueue instance(){
		if(mQueue == null){
			mQueue = Volley.newRequestQueue(MyApplication.getContextObject());
		}
		return mQueue;
	}

}
