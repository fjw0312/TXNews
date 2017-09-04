package TXNews.Main;

import mybroadcast.MyBroadcastReceiver;
import TXNews.bean.WXArticleModel;
import TXNews.customView.MyActionBar;
import TXNews.customView.MyHorizontalListView;
import TXNews.customView.MyNavigationBar;
import TXNews.customView.MyWXarticleListView;
import TXNews.getUrlContent.GetWXArticleModel;
import TXNews.getUrlContent.GetWXCategory;
import TXNews.url.WXUrl;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/***
 * 需要 进一步 优化  线程冲突问题
 * 图片 释放问题  
 * 跳转 问题
 * */
public class JxActivity extends Activity {

	MyWXarticleListView newsListView;
	MyHorizontalListView horizontalListView;
	MyNavigationBar navigationBar;
   
	String nowStrName = "";  //精选分类  name 
	String nowCid = "";
	      
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jx); 
		//MyActionBar actionBar = new MyActionBar(this); 
		new MyActionBar(this);
		
		horizontalListView = (MyHorizontalListView)findViewById(R.id.MyHorizontalListView);
		newsListView = (MyWXarticleListView)findViewById(R.id.MyNewsListView);
		navigationBar = (MyNavigationBar)findViewById(R.id.MyNavigationBar_id);
		
		
		horizontalListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				horizontalListView.Select(arg2);  //更新选中 图标       
				Adapter adapter = arg0.getAdapter();
				nowStrName = (String)adapter.getItem(arg2);
				nowCid = WXUrl.cidMap.get(nowStrName); 
				//重新  请求  分类具体数据     
				new MyThread2().start();
			}
		});
		
		newsListView.setOnItemClickListener(new OnItemClickListener() { //处理列表点击事件

			@Override
			public void onItemClick(AdapterView<?> newsListViewAdapter, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub  
				Adapter adapter = newsListViewAdapter.getAdapter();
				WXArticleModel newsModel = (WXArticleModel)adapter.getItem(arg2);
				// 进入 网页内容  
				Intent intent = new Intent(JxActivity.this, NewsWebActivity.class);
				intent.putExtra("str_html", newsModel.sourceUrl);
				intent.putExtra("str_title", newsModel.title);
				startActivity(intent);
			}
		});
		
		nowCid = "1";  //初始化 请求分类1 数据  
		myThread1.start();  //启动 获取 精选分类列表 网络线程 
		new MyThread2().start();  //启动 获取 精选分类1  文章列表  网络线程 
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		navigationBar.onStart(2);
	}


	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		navigationBar.onStop(2);
	}
	
	//网络请求线程  请求精选分类
	Thread myThread1 = new Thread(new Runnable() {	
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String url  =  WXUrl.getCategory();
			GetWXCategory.getWXCategory(url, "result", horizontalListView);
		}
	});
		
	

	//网络请求线程
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
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	//	MyBroadcastReceiver.sendBroad_MSG_HAL("JxAcitivity 死掉！");
	}
}
