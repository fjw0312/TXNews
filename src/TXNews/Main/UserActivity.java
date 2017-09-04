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

	String[] itemTitle = new String[]{"��Ҫ����","��Ϣ֪ͨ","�����","�����㲥","ȫ����","�û�����","�����Ϣ"};
	String[] itemExtra = new String[]{" ","ÿ��ǩ��׬����","�������ٹ⡷���μ�������","juju:����������","ս��2Ϊʲô��"," "," "};
	
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
				MyBroadcastReceiver.sendBroad_MSG_HAL("���������쳣��"); //�� δʵ�� ������ʹ���ҳ��
			}
		});
		
		//���ü���
		circleImageView.setOnClickListener(l);
		userName.setOnClickListener(l);
		ly_Title1.setOnClickListener(ll);
		ly_Title2.setOnClickListener(ll);
		ly_Title3.setOnClickListener(ll);
	}
	//���  �ղ�/��ʷ/ǩ�� ����  ������
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
	//��� ͷ�� ��¼ ������
	private OnClickListener l = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//��ʼ��¼ ����  or �����������
		//	MyBroadcastReceiver.sendBroad_MSG_HAL("��ʼ�û���¼");
			//Intent intent = new Intent(UserActivity.this, LoginActivity.class);
			Intent intent = new Intent(UserActivity.this, UserLoginActivity.class);
			startActivity(intent);
		}
	};
	//ǩ�� Dialog
	private void makeDialog(){
		new AlertDialog.Builder(this)
		.setTitle("ÿ��ǩ��")
		.setMessage("ǩ�����������ȼ�Ŷ")
		.setPositiveButton("ǩ��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				//Toast.makeText(this, "ȷ��", Toast.LENGTH_LONG).show();
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
