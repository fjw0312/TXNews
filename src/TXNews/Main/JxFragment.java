package TXNews.Main;

import java.util.zip.Inflater;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import TXNews.bean.WXArticleModel;
import TXNews.customView.MyFragmentLazy;
import TXNews.customView.MyHorizontalListView;
import TXNews.customView.MyNavigationBar;
import TXNews.customView.MyWXarticleListView;
import TXNews.getUrlContent.GetWXArticleModel;
import TXNews.getUrlContent.GetWXCategory;
import TXNews.url.WXUrl;

public class JxFragment extends MyFragmentLazy{

	public JxFragment() {
		// TODO Auto-generated constructor stub
	}
	
	MyWXarticleListView newsListView;
	MyHorizontalListView horizontalListView;
//	MyNavigationBar navigationBar;
   
	String nowStrName = "";  //��ѡ����  name 
	String nowCid = "";

	@Override
	protected void lazyLaod() {
		// TODO Auto-generated method stub
		super.lazyLaod();
		
		nowCid = "1";  //��ʼ�� �������1 ����  
		new myThread1().start();  //���� ��ȡ ��ѡ�����б� �����߳� 
		new MyThread2().start();  //���� ��ȡ ��ѡ����1  �����б�  �����߳� 
	}

	@Override
	protected void stopLoad() {
		// TODO Auto-generated method stub
		super.stopLoad();
	}

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_jx, container, false);
		//��� �ؼ�
		horizontalListView = (MyHorizontalListView)view.findViewById(R.id.MyHorizontalListView);
		newsListView = (MyWXarticleListView)view.findViewById(R.id.MyNewsListView);
//		navigationBar = (MyNavigationBar)view.findViewById(R.id.MyNavigationBar_id);
			
		horizontalListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				horizontalListView.Select(arg2);  //����ѡ�� ͼ��       
				Adapter adapter = arg0.getAdapter();
				nowStrName = (String)adapter.getItem(arg2);
				nowCid = WXUrl.cidMap.get(nowStrName); 
				//����  ����  �����������     
				new MyThread2().start();
			}
		});	
		newsListView.setOnItemClickListener(new OnItemClickListener() { //�����б����¼�

			@Override
			public void onItemClick(AdapterView<?> newsListViewAdapter, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub  
				Adapter adapter = newsListViewAdapter.getAdapter();
				WXArticleModel newsModel = (WXArticleModel)adapter.getItem(arg2);
				// ���� ��ҳ����  
				Intent intent = new Intent(JxFragment.this.getActivity(), NewsWebActivity.class);
				intent.putExtra("str_html", newsModel.sourceUrl);
				intent.putExtra("str_title", newsModel.title);
				startActivity(intent);
			}
		});
		
		return view;
	}

	//���������߳�  ����ѡ����
	private class  myThread1  extends Thread{	
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String url  =  WXUrl.getCategory();
			GetWXCategory.getWXCategory(url, "result", horizontalListView);
		}
	}
			
	//���������߳�
	private class MyThread2 extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			String url  =  WXUrl.getSearch(nowCid);
		//	String url  =  WXUrl.getSearchName(nowStrName);
			GetWXArticleModel.getNewsModel(url, "result", "list", newsListView);
		}	
	}
	
	
}
