package TXNews.customView;

import TXNews.Main.JxActivity;
import TXNews.Main.MainActivity;
import TXNews.Main.R;
import TXNews.Main.SpActivity;
import TXNews.Main.UserActivity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;



/**
 * 自定义 组合布局(获取布局文件自定义组件)  组件
 * author:fjw0312
 * Email:fjw0312@163.com 
 * date:2017.8.1
 * 功能：导航栏       利用4个ImageView
 * 
 * */
public class MyNavigationBar extends LinearLayout{

	public MyNavigationBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init_view(context);
	}

	public MyNavigationBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub 
		init_view(context);
	}

	public MyNavigationBar(Context context) { 
		super(context);
		// TODO Auto-generated constructor stub
		init_view(context);
	}
	
	private LinearLayout view1;
	private LinearLayout view2;
	private LinearLayout view3;
	private LinearLayout view4;
	private LinearLayout g_view1;
	private LinearLayout g_view2;
	private LinearLayout g_view3;
	private LinearLayout g_view4;
	private LinearLayout c_view1;
	private LinearLayout c_view2;
	private LinearLayout c_view3;
	private LinearLayout c_view4;	
	
	public int selectId = 0;
	Context mContext;
	
	
	//当 控件 不可见 时 被 外部调用
	public void onStop(int id){  //参数id 1-4
		switch(id){
		case 1:
			g_view1.setVisibility(VISIBLE);
			c_view1.setVisibility(GONE);
			break;
		case 2:
			g_view2.setVisibility(VISIBLE);
			c_view2.setVisibility(GONE);
			break;
		case 3:
			g_view3.setVisibility(VISIBLE);
			c_view3.setVisibility(GONE);
			break;
		case 4:
			g_view4.setVisibility(VISIBLE);
			c_view4.setVisibility(GONE);
			break;
		default:break;
		}
	}
	
	//当 控件 可见 时 被 外部调用
	public void onStart(int id){//参数id 1-4
		switch(id){
		case 1:
			g_view1.setVisibility(GONE);
			c_view1.setVisibility(VISIBLE);
			break;
		case 2:
			g_view2.setVisibility(GONE);
			c_view2.setVisibility(VISIBLE);
			break;
		case 3:
			g_view3.setVisibility(GONE);
			c_view3.setVisibility(VISIBLE);
			break;
		case 4:
			g_view4.setVisibility(GONE);
			c_view4.setVisibility(VISIBLE);
			break;			
		default:break;
		}
	}
	
	
	
	//初始化 ，将布局添加到控件上
	private void init_view(Context context){
		mContext = context;
		LayoutInflater.from(context).inflate(R.layout.my_navigation_bar, this);
		//获取控件
		view1 = (LinearLayout)findViewById(R.id.category1);
		view2 = (LinearLayout)findViewById(R.id.category2);
		view3 = (LinearLayout)findViewById(R.id.category3);
		view4 = (LinearLayout)findViewById(R.id.category4);
		
		g_view1 = (LinearLayout)findViewById(R.id.l_category1);
		g_view2 = (LinearLayout)findViewById(R.id.l_category2);
		g_view3 = (LinearLayout)findViewById(R.id.l_category3);
		g_view4 = (LinearLayout)findViewById(R.id.l_category4);
		c_view1 = (LinearLayout)findViewById(R.id.l_category10);
		c_view2 = (LinearLayout)findViewById(R.id.l_category20);
		c_view3 = (LinearLayout)findViewById(R.id.l_category30);
		c_view4 = (LinearLayout)findViewById(R.id.l_category40);			
			
		view1.setOnClickListener(l);
		view2.setOnClickListener(l);
		view3.setOnClickListener(l);
		view4.setOnClickListener(l);
	}
	
	private OnClickListener l = new OnClickListener() {		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent;			
			if(arg0==view1){
				intent = new Intent(mContext, MainActivity.class);
				mContext.startActivity(intent);
			}else if(arg0==view2){
				intent = new Intent(mContext, JxActivity.class);
				mContext.startActivity(intent);
			}else if(arg0==view3){
				intent = new Intent(mContext, SpActivity.class);
				mContext.startActivity(intent);
			}else if(arg0==view4){
				intent = new Intent(mContext, UserActivity.class);
				mContext.startActivity(intent);
			}
		
		}
	};


}
