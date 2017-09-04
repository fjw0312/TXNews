package TXNews.Main;


import java.util.List;
import TXNews.bean.HistoryModel;
import TXNews.bean.RecordModel;
import TXNews.utils.MyRecordAdapter;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class UserColleyActivity extends Activity{
	ImageView imageView;
	TextView Tx_manage;
	ListView listView;
	LinearLayout ly_id;
	CheckBox AllcheckBox;
	TextView Tx_del;
	TextView Tx_allDel;
	
	int ShowCheck_flag = 0;
		
	SimpleAdapter simpleAdap;  //简易适配器     
	List<RecordModel> myList = null;;
	
	MyRecordAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState); 
		ActionBar actionBar = getActionBar();   
		actionBar.hide(); 
		setContentView(R.layout.activity_user_history); 
		 
		imageView = (ImageView)findViewById(R.id.img_back); 
		Tx_manage = (TextView)findViewById(R.id.Tx_manage);
		listView = (ListView)findViewById(R.id.lst_id);
		ly_id =  (LinearLayout)findViewById(R.id.ly_id);
		AllcheckBox = (CheckBox)findViewById(R.id.CheckBox_id);
		Tx_del = (TextView)findViewById(R.id.Tx_del);
		Tx_allDel = (TextView)findViewById(R.id.Tx_allDel);
		
		
		imageView.setOnClickListener(l);
		Tx_manage.setOnClickListener(l);
		AllcheckBox.setOnCheckedChangeListener(checkedChangeListener);
		Tx_del.setOnClickListener(l);
		Tx_allDel.setOnClickListener(l);
		
	/*	myList = new ArrayList<RecordModel>();
		for(int i=1;i<31;i++){
			RecordModel recordModel = new RecordModel("时光忡忡，岁月荏苒！ -"+String.valueOf(i),"xxxxxxxxxxxxxxxxxxx");
			myList.add(recordModel);
		}
	*/	
		myList = HistoryModel.collectRecord_Map_lst;
		adapter = new MyRecordAdapter(this, myList);
		listView.setAdapter(adapter);	
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(ShowCheck_flag==1){ //进入管理
					Log.i("UserHistoryActivity>onItemClick","进入管理"); 
					
				}else{  //点击访问
					Log.i("UserHistoryActivity>onItemClick","点击访问");
					// 进入 网页内容  
					Adapter adapter = arg0.getAdapter();  
					RecordModel recordModel = (RecordModel)adapter.getItem(arg2);
					Intent intent = new Intent(UserColleyActivity.this, NewsWebActivity.class);
					intent.putExtra("str_html", recordModel.url);
					intent.putExtra("str_title", recordModel.title);
					startActivity(intent);
				}
			}
		});
	}
	private OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener(){ //对全选 按钮监听
		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
			// TODO Auto-generated method stub
			if(adapter!=null){
				if(arg1){
					adapter.showChoiceAll();  //全选
				}else{
					adapter.showRemoveChoiceAll();  //不全选
				}			
			}
		}	
	};
	private OnClickListener l = new OnClickListener() {		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==imageView){       //放回 退出页面
				Intent intent = new Intent(UserColleyActivity.this, UserActivity.class);
				startActivity(intent);	
			}else if(arg0==Tx_manage){ //进入管理模式
				if(ShowCheck_flag==0){  //显示 进入管理 选择 模式
					ShowCheck_flag = 1;
					adapter.showCheckBox();  //显示所有 可选择框
					ly_id.setVisibility(View.VISIBLE);
				}else if(ShowCheck_flag==1){ //隐藏 管理 选择 模式
					ShowCheck_flag = 0;
					adapter.showCheckBoxEnd();
					ly_id.setVisibility(View.GONE);
				}			
			}else if(arg0==Tx_del){     //删除 选择的item
				ShowCheck_flag = 0;
				adapter.deleteChoice();
				ly_id.setVisibility(View.GONE);
				AllcheckBox.setChecked(false);
			}else if(arg0==Tx_allDel){ //全部 删除Item
				ShowCheck_flag = 0;
				adapter.deleteAll();
				ly_id.setVisibility(View.GONE);
				AllcheckBox.setChecked(false);
			}
		}
	};
}
