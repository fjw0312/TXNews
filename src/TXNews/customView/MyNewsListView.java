package TXNews.customView;

import java.util.List;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import TXNews.Main.R;
import TXNews.bean.NewsModel;
import TXNews.getUrlContent.GetImageLoader;
import TXNews.utils.MyImageCache;
import TXNews.utils.MyVolleyRequestQueue;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


/***
 * 自定义控件    继承现有控件 拓展
 * 定制  自定义控件  ： 新闻列表控件
 * 
 * ListView 控件使用时，自定义适配器中
 * getView 由于会被不断滑动，会不断进入，所以 尽量不要在该方法 new内存（主要是view!=null时）
 * 需要将网络加载图片显示时，把网络加载交给volley,但注意不要创建多个RequestQueue.
 * ListView显示图片优化：
 * 可以使用Scoll监听，onScroll  onScrollStateChanged，处理只请求显示看得见的图片。
 * 
 * 后期优化： 可以考虑预加载问题
 * */
public class MyNewsListView extends ListView{

	public MyNewsListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init_view(); 
	}

	public MyNewsListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init_view();
	}

	public MyNewsListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init_view();
	}
	
	MyNewsListViewAdapter adapter;
	RequestQueue volleyRequestQueue;   //定义 volley请求队列  
	ImageLoader imageLoader;           //volley 图片请求器
	MyImageCache imageCache;           //定义图片缓存
	
	//控件初始化
	private void init_view(){
		volleyRequestQueue = MyVolleyRequestQueue.instance();  //volley请求队列   实例化全局 单例
		imageCache = MyImageCache.instance();                  //图片缓存          实例化全局 单例
		imageLoader = new ImageLoader(volleyRequestQueue, imageCache);  //实例化 图片请求器
		
		adapter = new MyNewsListViewAdapter();
		this.setAdapter(adapter);
	}
	
	//更新控件  数据   提供外部调用更新 数据
	/**
	 * 更新listView adapter 数据有3重方式  ：
	 *  1.重新setAdapter 2.调用adapter.notifydatasetchanged() 3.在Adapter里面调用更新接口
	 * */
	public void Update(List<NewsModel> lst_news){	
	    //adapter = new MyNewsListViewAdapter(lst_news);
		//this.setAdapter(adapter);
		adapter.UpdateAdapter(lst_news);
		adapter.notifyDataSetChanged();
	}
		
	//自定义一个适配器
    public class MyNewsListViewAdapter extends BaseAdapter{
    	
    	public MyNewsListViewAdapter() {
			super();
			// TODO Auto-generated constructor stub
		}
    	public MyNewsListViewAdapter(List<NewsModel> lst_news) {
			super();
			// TODO Auto-generated constructor stub
			NewsM_lst = lst_news;
		}
    	
    	//更新 适配器 所有数据
    	public void UpdateAdapter(List<NewsModel> lst){
    		if(NewsM_lst != null){  
    			NewsM_lst.clear();
    		//	System.gc();
    		}	
    		NewsM_lst = lst;
    		
    	}

		List<NewsModel> NewsM_lst = null;
    	class ViewHolder1{    //定义一个保存 控件元素 的辅助类
    		ImageView imageView;
    		TextView  title;
    		TextView  author;
    		TextView  date;
    	}
    	class ViewHolder2{    //定义一个保存 控件元素 的辅助类		
    		TextView  title;
    		ImageView imageView1;
    		ImageView imageView2;
    		ImageView imageView3;
    		TextView  author;
    		TextView  date;
    	}
    	
    	@Override
		public int getItemViewType(int position) {
			// TODO Auto-generated method stub
    		NewsModel newsModel = (NewsModel)getItem(position);
    		if(newsModel==null) return 0;
			return newsModel.type;
		}
    	
		@Override
		public int getViewTypeCount() {
			// TODO Auto-generated method stub
			return 2;
		} 
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if(NewsM_lst==null) return 0;
			return NewsM_lst.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			if(NewsM_lst==null) return null;
			return NewsM_lst.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View view, ViewGroup arg2) {
			// TODO Auto-generated method stub
			NewsModel newsModel = (NewsModel)getItem(arg0);
		//	View view;
			ViewHolder1 viewHolder1;
			ViewHolder2 viewHolder2;
			
			
			
			switch(newsModel.type){
			case NewsModel.TYPE_A:
				if(view==null){
					view = View.inflate(getContext(), R.layout.i_news_item1, null);
					//添加ViewHolder
					viewHolder1 = new ViewHolder1();
					viewHolder1.imageView = (ImageView)view.findViewById(R.id.imageView);
					viewHolder1.title = (TextView)view.findViewById(R.id.title);
					viewHolder1.author = (TextView)view.findViewById(R.id.author);
					viewHolder1.date = (TextView)view.findViewById(R.id.date);
					view.setTag(R.id.tag_type_a,viewHolder1); //ListView 多种类型Item 由于要复用 需要setTag 带KeyId
				}else{
					viewHolder1 = (ViewHolder1)view.getTag(R.id.tag_type_a);  //重新获取 ViewHolder
				}
				//将 数据适配
				if(newsModel != null){ 
					viewHolder1.title.setText(newsModel.title);
					viewHolder1.author.setText(newsModel.author_name);
					viewHolder1.date.setText(newsModel.date);
					// 调用 图片 加载类 加载图片     图片 长宽比约80dp:65dp：240:200  
					GetImageLoader.getImageLoader(newsModel.thumbnail_pic_s,imageLoader, 
							viewHolder1.imageView, R.drawable.img_b_default,R.drawable.img_b_error,
							240, 200);
				}
				break;
			case NewsModel.TYPE_B:
				if(view==null){
					view = View.inflate(getContext(), R.layout.i_news_item2, null);
					//添加ViewHolder 
					viewHolder2 = new ViewHolder2();		
					viewHolder2.title = (TextView)view.findViewById(R.id.title);
					viewHolder2.author = (TextView)view.findViewById(R.id.author);
					viewHolder2.date = (TextView)view.findViewById(R.id.date);
					viewHolder2.imageView1 = (ImageView)view.findViewById(R.id.img_1);
					viewHolder2.imageView2 = (ImageView)view.findViewById(R.id.img_2);
					viewHolder2.imageView3 = (ImageView)view.findViewById(R.id.img_3);
					view.setTag(R.id.tag_type_b,viewHolder2);  //ListView 多种类型Item 由于要复用 需要setTag 带KeyId
				}else{
					viewHolder2 = (ViewHolder2)view.getTag(R.id.tag_type_b);  //重新获取 ViewHolder 
				}
				//将 数据适配 
				if(newsModel != null){ 
					viewHolder2.title.setText(newsModel.title);
					viewHolder2.author.setText(newsModel.author_name);
					viewHolder2.date.setText(newsModel.date);
					// 调用 图片 加载类 加载图片      图片长宽比117dp:80dp  352:240
					GetImageLoader.getImageLoader(newsModel.thumbnail_pic_s, imageLoader, 
						viewHolder2.imageView1, R.drawable.img_c_default,R.drawable.img_c_error,
						352, 240);
					GetImageLoader.getImageLoader(newsModel.thumbnail_pic_s02, imageLoader, 
							viewHolder2.imageView2, R.drawable.img_c_default,R.drawable.img_c_error,
							352, 240);
					GetImageLoader.getImageLoader(newsModel.thumbnail_pic_s03, imageLoader, 
							viewHolder2.imageView3, R.drawable.img_c_default,R.drawable.img_c_error,
							352, 240);
				}
				break;
			default:break; 
			}
			
			return view;
		}  	
    }

}
