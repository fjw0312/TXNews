package mybroadcast;
 
import myapplication.MyApplication;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
  
 
/***
 * �����Զ���    �㲥������
 * author:fjw0312
 * date:2017.7.12
 * 
 * ��Ҫ��AndroidMainfest.xml  ע��
        <receiver           
             android:exported="false"
             android:name="mybroadcast.MyBroadcastReceiver">
	        <intent-filter>
	            <action android:name="Fang.MyBroadcast.Error" />
	            <action android:name="Fang.MyBroadcast.MSG" />
	        </intent-filter>
    	</receiver>
 * 
 * 
 * ���÷��ͣ�
 * 			Intent intent = new Intent("Fang.MyBroadcast.Error");
			intent.putExtra("fang", "HttpURLConnectionHAL-GET �ַ� �����쳣");
			MyApplication.getContextObject().sendBroadcast(intent);
 * */
public class MyBroadcastReceiver extends BroadcastReceiver{ 

	final static String Fang_Error = "Fang.MyBroadcast.Error";
	final static String Fang_MSG = "Fang.MyBroadcast.MSG";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//Log.i("MyBroadcastReceiver>","����㲥������"); 
		String extraStr = intent.getStringExtra("fang");
		if(intent.getAction().equals(Fang_Error)){
			Toast.makeText(context, "Error:"+extraStr, Toast.LENGTH_LONG).show();
		}else if(intent.getAction().equals(Fang_MSG)){
			Toast.makeText(context, "MSG:"+extraStr, Toast.LENGTH_LONG).show();
		}
	}
	
	
	
	//�Զ����ⲿ  �㲥���ͽӿ�
	public static void sendBroad_Error_HAL(String strContent){
	    Intent intent = new Intent(Fang_Error);
		intent.putExtra("fang", strContent);
		MyApplication.getContextObject().sendBroadcast(intent);
	}
	public static void sendBroad_MSG_HAL(String strContent){
	    Intent intent = new Intent(Fang_MSG);
		intent.putExtra("fang", strContent);
		MyApplication.getContextObject().sendBroadcast(intent);
	}
	

}
