package TXNews.Main;

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
import TXNews.bean.NewsModel;
import TXNews.bean.TextBarItemModel;
import TXNews.customView.MyFragmentLazy;
import TXNews.customView.MyHorizontalListView;
import TXNews.customView.MyNavigationBar;
import TXNews.customView.MyNewsListView;
import TXNews.getUrlContent.GetNewsModel;
import TXNews.url.JHUrl;

public class MainFragment extends MyFragmentLazy{

	public MainFragment() {
		// TODO Auto-generated constructor stub
	}
	
	MyNewsListView newsListView;
	MyHorizontalListView horizontalListView;
//	MyNavigationBar navigationBar; 
	
	String nowCategory = "";  //新闻分类

	@Override
	protected void lazyLaod() {
		// TODO Auto-generated method stub
		super.lazyLaod();
		
		//初次 默认加载 top头条分类的新闻
		nowCategory = JHUrl.top;
		new MyThread().start();
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
        View view=inflater.inflate(R.layout.activity_main, container, false);
      //获得 控件  
        horizontalListView = (MyHorizontalListView)view.findViewById(R.id.MyHorizontalListView);
		newsListView = (MyNewsListView)view.findViewById(R.id.MyNewsListView);	
//		navigationBar = (MyNavigationBar)view.findViewById(R.id.MyNavigationBar_id);  
		
	    TextBarItemModel.init(); 
		horizontalListView.Update(TextBarItemModel.text_lst);  //分类新闻 列表  适配
		horizontalListView.setOnItemClickListener(new OnItemClickListener() { 
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub		
				Adapter adapter = arg0.getAdapter();
				String strkey = (String)adapter.getItem(arg2);
				String strValue = TextBarItemModel.text_hash.get(strkey);
				nowCategory = strValue;
				//重新  请求分类 新闻
				new MyThread().start();
				
				horizontalListView.Select(arg2);  //更新选中 图标
			}
		});
		newsListView.setOnItemClickListener(new OnItemClickListener() { //处理列表点击事件

			@Override
			public void onItemClick(AdapterView<?> newsListViewAdapter, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub  
				Adapter adapter = newsListViewAdapter.getAdapter();
				NewsModel newsModel = (NewsModel)adapter.getItem(arg2);
				// 进入 网页内容  
				Intent intent = new Intent(MainFragment.this.getActivity(), NewsWebActivity.class);
				intent.putExtra("str_html", newsModel.url);
				intent.putExtra("str_title", newsModel.title);
				startActivity(intent);
			}
		});
	
        return view;
		
	}

	//网络请求线程
	private class MyThread  extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			String url  =  JHUrl.getUrl(nowCategory);
			GetNewsModel.getNewsModel(url, "result", "data", newsListView);
		}		
	}
	
	
}
