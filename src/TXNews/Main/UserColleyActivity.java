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
		
	SimpleAdapter simpleAdap;  //����������     
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
			RecordModel recordModel = new RecordModel("ʱ�����磬�������ۣ� -"+String.valueOf(i),"xxxxxxxxxxxxxxxxxxx");
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
				if(ShowCheck_flag==1){ //�������
					Log.i("UserHistoryActivity>onItemClick","�������"); 
					
				}else{  //�������
					Log.i("UserHistoryActivity>onItemClick","�������");
					// ���� ��ҳ����  
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
	private OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener(){ //��ȫѡ ��ť����
		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
			// TODO Auto-generated method stub
			if(adapter!=null){
				if(arg1){
					adapter.showChoiceAll();  //ȫѡ
				}else{
					adapter.showRemoveChoiceAll();  //��ȫѡ
				}			
			}
		}	
	};
	private OnClickListener l = new OnClickListener() {		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==imageView){       //�Ż� �˳�ҳ��
				Intent intent = new Intent(UserColleyActivity.this, UserActivity.class);
				startActivity(intent);	
			}else if(arg0==Tx_manage){ //�������ģʽ
				if(ShowCheck_flag==0){  //��ʾ ������� ѡ�� ģʽ
					ShowCheck_flag = 1;
					adapter.showCheckBox();  //��ʾ���� ��ѡ���
					ly_id.setVisibility(View.VISIBLE);
				}else if(ShowCheck_flag==1){ //���� ���� ѡ�� ģʽ
					ShowCheck_flag = 0;
					adapter.showCheckBoxEnd();
					ly_id.setVisibility(View.GONE);
				}			
			}else if(arg0==Tx_del){     //ɾ�� ѡ���item
				ShowCheck_flag = 0;
				adapter.deleteChoice();
				ly_id.setVisibility(View.GONE);
				AllcheckBox.setChecked(false);
			}else if(arg0==Tx_allDel){ //ȫ�� ɾ��Item
				ShowCheck_flag = 0;
				adapter.deleteAll();
				ly_id.setVisibility(View.GONE);
				AllcheckBox.setChecked(false);
			}
		}
	};
}
