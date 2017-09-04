package myapplication;

import android.app.Application;
import android.content.Context;

/***
 * 全局 Application  Context 获取
 * author:fjw0312
 * date:2017.7.12
 * notice: 需要在AndroidMainfest.xml 添加：
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

	//获取 context
	public static Context getContextObject(){
		return context;
	}

}
