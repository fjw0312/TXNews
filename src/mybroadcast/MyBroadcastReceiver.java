package mybroadcast;
 
import myapplication.MyApplication;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
  
 
/***
 * 个人自定义    广播接收器
 * author:fjw0312
 * date:2017.7.12
 * 
 * 需要在AndroidMainfest.xml  注册
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
 * 调用发送：
 * 			Intent intent = new Intent("Fang.MyBroadcast.Error");
			intent.putExtra("fang", "HttpURLConnectionHAL-GET 字符 网络异常");
			MyApplication.getContextObject().sendBroadcast(intent);
 * */
public class MyBroadcastReceiver extends BroadcastReceiver{ 

	final static String Fang_Error = "Fang.MyBroadcast.Error";
	final static String Fang_MSG = "Fang.MyBroadcast.MSG";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//Log.i("MyBroadcastReceiver>","进入广播接收器"); 
		String extraStr = intent.getStringExtra("fang");
		if(intent.getAction().equals(Fang_Error)){
			Toast.makeText(context, "Error:"+extraStr, Toast.LENGTH_LONG).show();
		}else if(intent.getAction().equals(Fang_MSG)){
			Toast.makeText(context, "MSG:"+extraStr, Toast.LENGTH_LONG).show();
		}
	}
	
	
	
	//自定义外部  广播发送接口
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
