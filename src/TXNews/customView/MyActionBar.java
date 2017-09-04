package TXNews.customView;


import mybroadcast.MyBroadcastReceiver;
import TXNews.Main.R;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 自定义  标题栏
 * author:fjw0312
 * date: 2017.8.2 
 * 
 * */
public class MyActionBar {

	TextView  Tx_Title;
	ImageView imageView1;
	ImageView imageView2;
	ImageView imageView3; 
	
	public MyActionBar(Activity activity) {
		// TODO Auto-generated constructor stub
		
		//对标题栏进行 自定义修改
		ActionBar actionBar = activity.getActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);//使能自定义
		actionBar.setCustomView(R.layout.my_actionbar);       //设置自定义布局
		actionBar.setDisplayShowCustomEnabled(true);
			
		Tx_Title = (TextView)actionBar.getCustomView().findViewById(R.id.Tx_actionBar_Title);
	//	imageView1 = (ImageView)actionBar.getCustomView().findViewById(R.id.img_TitleLogo);
	//	imageView2 = (ImageView)actionBar.getCustomView().findViewById(R.id.img_findLogo);
		imageView3 = (ImageView)actionBar.getCustomView().findViewById(R.id.img_shareLogo);
		
	//	imageView1.setOnClickListener(l);
		Tx_Title.setOnClickListener(l);
	//	imageView2.setOnClickListener(l);
		imageView3.setOnClickListener(l);
	}
	
	private OnClickListener l = new OnClickListener() {
		 
		 
		@Override
		public void onClick(View arg0) {     
			// TODO Auto-generated method stub 
			if(arg0==Tx_Title){
		//		MyBroadcastReceiver.sendBroad_MSG_HAL("点击标题栏>> Title logo"); 
			}else if(arg0==imageView2){
		//		MyBroadcastReceiver.sendBroad_MSG_HAL("点击标题栏>> xxxx logo");
			}else if(arg0==imageView3){
		//		MyBroadcastReceiver.sendBroad_MSG_HAL("点击标题栏>> 搜索 logo"); 
			}
		}
	};
	
	//获取 标题栏 高度 （也能获取状态栏高度）    
	public static int getTitleBar_Height(Activity activity){ 
		//获取 状态栏 高度 
		Rect frame = new Rect();  
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top; 
		
		int contentTop = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();  
		//statusBarHeight是上面状态栏的高度  
		int titleBarHeight = contentTop - statusBarHeight; 
		return titleBarHeight;
	}

}
