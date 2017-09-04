package TXNews.Main;


import mybroadcast.MyBroadcastReceiver;
import TXNews.bean.NewsModel;
import TXNews.bean.TextBarItemModel;
import TXNews.customView.MyActionBar;
import TXNews.customView.MyHorizontalListView;
import TXNews.customView.MyNavigationBar;
import TXNews.customView.MyNewsListView;
import TXNews.getUrlContent.GetNewsModel;
import TXNews.url.JHUrl;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/***
 * �д� �Ż����⣺
 * 1. ��Ƶ���Ż��� ��bug
 * 2. ����ˢ�� �б�
 * 3. �б�����Ŀ¼����
 * 4. ����������
 * 5. ����״̬����ɫ   
 * */

public class MainActivity extends Activity { 

	MyNewsListView newsListView;
	MyHorizontalListView horizontalListView;
	MyNavigationBar navigationBar; 
	
	String nowCategory = "";  //���ŷ���
	      
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);   
	
		//MyActionBar actionBar = new MyActionBar(this);  
		new MyActionBar(this);  

		horizontalListView = (MyHorizontalListView)findViewById(R.id.MyHorizontalListView);
		newsListView = (MyNewsListView)findViewById(R.id.MyNewsListView);	
		navigationBar = (MyNavigationBar)findViewById(R.id.MyNavigationBar_id);  
		
	    TextBarItemModel.init(); 
		horizontalListView.Update(TextBarItemModel.text_lst);  //�������� �б�  ����
		horizontalListView.setOnItemClickListener(new OnItemClickListener() { 
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub		
				Adapter adapter = arg0.getAdapter();
				String strkey = (String)adapter.getItem(arg2);
				String strValue = TextBarItemModel.text_hash.get(strkey);
				nowCategory = strValue;
				//����  ������� ����
				new MyThread().start();
				
				horizontalListView.Select(arg2);  //����ѡ�� ͼ��
			}
		});
		newsListView.setOnItemClickListener(new OnItemClickListener() { //�����б����¼�

			@Override
			public void onItemClick(AdapterView<?> newsListViewAdapter, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub  
				Adapter adapter = newsListViewAdapter.getAdapter();
				NewsModel newsModel = (NewsModel)adapter.getItem(arg2);
				// ���� ��ҳ����  
				Intent intent = new Intent(MainActivity.this, NewsWebActivity.class);
				intent.putExtra("str_html", newsModel.url);
				intent.putExtra("str_title", newsModel.title);
				startActivity(intent);
			}
		});
	
		//���� Ĭ�ϼ��� topͷ�����������
		nowCategory = JHUrl.top;
		new MyThread().start();

	}
	
	//���������߳�
	private class MyThread  extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			String url  =  JHUrl.getUrl(nowCategory);
			GetNewsModel.getNewsModel(url, "result", "data", newsListView);
		}
		
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		navigationBar.onStart(1);
	}


	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		navigationBar.onStop(1);
	}
	 
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
	//	MyBroadcastReceiver.sendBroad_MSG_HAL("MainAcitivity ������");
		super.onDestroy();		
	}


}
