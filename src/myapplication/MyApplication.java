package myapplication;

import android.app.Application;
import android.content.Context;

/***
 * ȫ�� Application  Context ��ȡ
 * author:fjw0312
 * date:2017.7.12
 * notice: ��Ҫ��AndroidMainfest.xml ��ӣ�
 * <application
 *     android:name="MyApplicationInit.MyApplication"
 * use: MyApplication.getContextObject();
 * */
public class MyApplication extends Application{  
	private static Context context;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		context = getApplicationContext();
	}

	//��ȡ context
	public static Context getContextObject(){
		return context;
	}

}
