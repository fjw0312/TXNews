package TXNews.Main;

import java.util.ArrayList;
import java.util.List;
import mybroadcast.MyBroadcastReceiver;
import TXNews.bean.UseItemModel;
import TXNews.customView.CircleImageView;
import TXNews.customView.MyNavigationBar;
import TXNews.customView.MyUseListView;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserActivity extends Activity{

	MyNavigationBar navigationBar;
	MyUseListView useListView;
	CircleImageView circleImageView;
	TextView userName;
	
	LinearLayout ly_Title1;
	LinearLayout ly_Title2;
	LinearLayout ly_Title3;

	String[] itemTitle = new String[]{"我要发帖","消息通知","活动公告","附近广播","全民话题","用户反馈","软件信息"};
	String[] itemExtra = new String[]{" ","每日签到赚积分","《王者荣光》手游即将上线","juju:网红修炼记","战狼2为什么火"," "," "};
	
	public List<UseItemModel> init_data(){   
		int count = itemTitle.length; 
		List<UseItemModel> useItemModel_lst = new ArrayList<UseItemModel>();
		for(int i=0;i<count;i++){
			UseItemModel useItemModel = new UseItemModel();
			useItemModel.itemLogo = 0;
			//useItemModel.itemLogo = R.drawable.item_gray_db;
			useItemModel.itemTitle = itemTitle[i];
			useItemModel.itemExtra = itemExtra[i]; 
			useItemModel.smsLogo = R.drawable.right_gray_db;
			useItemModel_lst.add(useItemModel);
		}
		return useItemModel_lst;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {  
		// TODO Auto-generated method stub  
		super.onCreate(savedInstanceState);   
//		new MyActionBar(this); 
		ActionBar actionBar = getActionBar();   
		actionBar.hide();
		setContentView(R.layout.activity_user);
		
		navigationBar = (MyNavigationBar)findViewById(R.id.MyNavigationBar_id);
		circleImageView = (CircleImageView)findViewById(R.id.Img_usrLogo);
		userName = (TextView)findViewById(R.id.Tx_usrName); 
		ly_Title1 = (LinearLayout)findViewById(R.id.usr_collect);  
		ly_Title2 = (LinearLayout)findViewById(R.id.usr_history);
		ly_Title3 = (LinearLayout)findViewById(R.id.usr_sigin);
		useListView = (MyUseListView)findViewById(R.id.myUseListView_id);
		useListView.Update( init_data() );
		useListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub 
			//	Adapter adapter = arg0.getAdapter();
			//	UseItemModel useItemModel = (UseItemModel)adapter.getItem(arg2);
				MyBroadcastReceiver.sendBroad_MSG_HAL("访问链接异常！"); //暂 未实现 子项访问功能页面
			}
		});
		
		//设置监听
		circleImageView.setOnClickListener(l);
		userName.setOnClickListener(l);
		ly_Title1.setOnClickListener(ll);
		ly_Title2.setOnClickListener(ll);
		ly_Title3.setOnClickListener(ll);
	}
	//点击  收藏/历史/签到 标题  监听器
	private OnClickListener ll = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==ly_Title1){			
				Intent intent = new Intent(UserActivity.this, UserColleyActivity.class);
				startActivity(intent);
			}else if(arg0==ly_Title2){
				
				Intent intent = new Intent(UserActivity.this, UserHistoryActivity.class);
				startActivity(intent);
			}else if(arg0==ly_Title3){
				makeDialog();
			}
		}
	};
	//点击 头像 登录 监听器
	private OnClickListener l = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//开始登录 界面  or 进入个人资料
		//	MyBroadcastReceiver.sendBroad_MSG_HAL("开始用户登录");
			//Intent intent = new Intent(UserActivity.this, LoginActivity.class);
			Intent intent = new Intent(UserActivity.this, UserLoginActivity.class);
			startActivity(intent);
		}
	};
	//签到 Dialog
	private void makeDialog(){
		new AlertDialog.Builder(this)
		.setTitle("每日签到")
		.setMessage("签到可以提升等级哦")
		.setPositiveButton("签到", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				//Toast.makeText(this, "确定", Toast.LENGTH_LONG).show();
			}
		})
		.create()
		.show();
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub  
		super.onStart();
		navigationBar.onStart(4); 
	}


	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		navigationBar.onStop(4);
	}

}
