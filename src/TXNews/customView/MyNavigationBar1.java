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
import android.widget.ImageView;
import android.widget.LinearLayout;


/**
 * 自定义 组合布局(获取布局文件自定义组件)  组件
 * author:fjw0312
 * Email:fjw0312@163.com 
 * date:2017.8.1
 * 功能：导航栏       利用4个ImageView
 * 
 * */
public class MyNavigationBar1 extends LinearLayout{

	public MyNavigationBar1(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init_view(context);
	}

	public MyNavigationBar1(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init_view(context);
	}

	public MyNavigationBar1(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init_view(context);
	}
	
	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView3;
	private ImageView imageView4;
	private ImageView defaultImageView;
	public int selectId = 0;
	Context mContext;
	
	public void changeImageView(int changeMode,int id){
		ImageView image;
		switch(id){
		case 1:
			image = imageView1;
			break;
		case 2:
			image = imageView2;
			break;
		case 3:
			image = imageView3;
			break;
		case 4:
			image = imageView4;
			break;
		default: image = defaultImageView; break;
		}
		if(image == null) return;
		// 处理  Image
		if(changeMode == 0){  //恢复 之前 的处理
			image.setScaleX(1.0f);
			image.setScaleY(1.0f);
		}else if(changeMode == 1){  //处理当前新处理
			image.setScaleX(1.3f);
			image.setScaleY(1.3f);
		}

	}
	
	//初始化 ，将布局添加到控件上
	private void init_view(Context context){
		mContext = context;
		LayoutInflater.from(context).inflate(R.layout.my_navigation_bar1, this);
		//获取控件	
		imageView1 = (ImageView)findViewById(R.id.img_category1);
		imageView2 = (ImageView)findViewById(R.id.img_category2);
		imageView3 = (ImageView)findViewById(R.id.img_category3);
		imageView4 = (ImageView)findViewById(R.id.img_category4);
		
		imageView1.setOnClickListener(l);
		imageView2.setOnClickListener(l);
		imageView3.setOnClickListener(l);
		imageView4.setOnClickListener(l);
	}
	
	private OnClickListener l = new OnClickListener() {		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent;			
			if(arg0==imageView1){
				intent = new Intent(mContext, MainActivity.class);
				mContext.startActivity(intent);
			}else if(arg0==imageView2){
				intent = new Intent(mContext, JxActivity.class);
				mContext.startActivity(intent);
			}else if(arg0==imageView3){
				intent = new Intent(mContext, SpActivity.class);
				mContext.startActivity(intent);
			}else if(arg0==imageView4){
				intent = new Intent(mContext, UserActivity.class);
				mContext.startActivity(intent);
			}
		
		}
	};


}
