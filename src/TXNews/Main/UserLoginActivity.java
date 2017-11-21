package TXNews.Main;

import mybroadcast.MyBroadcastReceiver;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class UserLoginActivity extends Activity{
	
	ImageView img_back;
	TextView Tx_sigin;
	EditText Ed_UserNmae;
	EditText Ed_passWork;
	Button Bn_Login;
	ImageView img_WX_login;
	ImageView img_QQ_login;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState); 
		ActionBar actionBar = getActionBar();   
		actionBar.hide(); 
		setContentView(R.layout.activity_user_login); 
		 
		img_back = (ImageView)findViewById(R.id.img_back); 
		Tx_sigin = (TextView)findViewById(R.id.Tx_sigin); 
		Ed_UserNmae = (EditText)findViewById(R.id.Ed_UserNmae); 
		Ed_passWork = (EditText)findViewById(R.id.Ed_passWork); 
		Bn_Login = (Button)findViewById(R.id.Bn_Login); 
		img_WX_login = (ImageView)findViewById(R.id.img_WX_login); 
		img_QQ_login = (ImageView)findViewById(R.id.img_QQ_login); 
		
		img_back.setOnClickListener(l);
		Tx_sigin.setOnClickListener(l);
		Bn_Login.setOnClickListener(l);
		img_WX_login.setOnClickListener(l);
		img_QQ_login.setOnClickListener(l);
	}
	private OnClickListener l = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==img_back){  //返回
				UserLoginActivity.this.finish();
			}else if(arg0==Bn_Login){
				MyBroadcastReceiver.sendBroad_Error_HAL("登录失败！");
			}else if(arg0==Tx_sigin){
				MyBroadcastReceiver.sendBroad_MSG_HAL("目前关闭用户注册！请联系商家。");
			}else if(arg0==img_WX_login){
				MyBroadcastReceiver.sendBroad_MSG_HAL("目前关闭微信登录！请联系商家。");
			}else if(arg0==img_QQ_login){
				MyBroadcastReceiver.sendBroad_MSG_HAL("目前关闭QQ登录！请联系商家。");
			}
		}
	};

}
