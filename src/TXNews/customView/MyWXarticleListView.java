package TXNews.customView;

import java.util.List;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import TXNews.Main.R;
import TXNews.bean.WXArticleModel;
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
 * 定制  自定义控件  ：微信精选列表控件
 * 
 * */
public class MyWXarticleListView extends ListView{

	public MyWXarticleListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init_view(); 
	}

	public MyWXarticleListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init_view();
	}

	public MyWXarticleListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init_view();
	}
	MyWXarticleListViewAdapter adapter;
	RequestQueue volleyRequestQueue;   //定义 volley请求队列  
	ImageLoader imageLoader;           //volley 图片请求器
	MyImageCache imageCache;           //定义图片缓存
	
	//控件初始化
	private void init_view(){	
		volleyRequestQueue = MyVolleyRequestQueue.instance();  //volley请求队列   实例化全局 单例
		imageCache = MyImageCache.instance();                  //图片缓存          实例化全局 单例
		imageLoader = new ImageLoader(volleyRequestQueue, imageCache);  //实例化 图片请求器
		
		adapter = new MyWXarticleListViewAdapter();
		this.setAdapter(adapter);
	}
	
	//更新控件  数据   提供外部调用更新 数据
	public void Update(List<WXArticleModel> lst_news){
	//	adapter = new MyWXarticleListViewAdapter(lst_news);
	//	this.setAdapter(adapter);
		adapter.UpdateAdapter(lst_news);
		adapter.notifyDataSetChanged();
	}
		
	//自定义一个适配器
    public class MyWXarticleListViewAdapter extends BaseAdapter{
    	
		public MyWXarticleListViewAdapter() {
			super();
			// TODO Auto-generated constructor stub
		}
    	public MyWXarticleListViewAdapter(List<WXArticleModel> lst_news) {
			super();
			// TODO Auto-generated constructor stub
			NewsM_lst = lst_news;
		}
    	//更新 适配器 所有数据
    	public void UpdateAdapter(List<WXArticleModel> lst){
    		if(NewsM_lst != null){  
    			NewsM_lst.clear();
    		}	
    		NewsM_lst = lst; 		
    	}
    	
		List<WXArticleModel> NewsM_lst = null;
    	class ViewHolder1{    //定义一个保存 控件元素 的辅助类
    		ImageView imageView;
    		TextView  title;
    		TextView  subTitle;
    		TextView  hitcount;
    		TextView  date;
    	}
    	class ViewHolder2{    //定义一个保存 控件元素 的辅助类
    		
    		TextView  title;
    		TextView  subTitle;
    		TextView  hitcount;
    		TextView  date;
    	}
    	
    	@Override
		public int getItemViewType(int position) {
			// TODO Auto-generated method stub
    		WXArticleModel wxArticleModel = (WXArticleModel)getItem(position);
    		if(wxArticleModel==null) return 0;
			return wxArticleModel.type;
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
			WXArticleModel wxArticleModel = (WXArticleModel)getItem(arg0);
//			View view;
			ViewHolder1 viewHolder1;
			ViewHolder2 viewHolder2;
			
			
			switch(wxArticleModel.type){
			case WXArticleModel.TYPE_A:
				if(view==null){
					view = View.inflate(getContext(), R.layout.i_wxarticle_item1, null);
					viewHolder1 = new ViewHolder1();
					viewHolder1.imageView = (ImageView)view.findViewById(R.id.imageView);//添加ViewHolder
					viewHolder1.title = (TextView)view.findViewById(R.id.title);
					viewHolder1.subTitle = (TextView)view.findViewById(R.id.subTitle);
					viewHolder1.hitcount = (TextView)view.findViewById(R.id.hitcount);
					viewHolder1.date = (TextView)view.findViewById(R.id.date);
					view.setTag(R.id.tag_type_a,viewHolder1); //ListView 多种类型Item 由于要复用 需要setTag 带KeyId				
				}else{
					viewHolder1 = (ViewHolder1)view.getTag(R.id.tag_type_a);  //重新获取 ViewHolder
				}
				//将 数据适配
				if(wxArticleModel != null){
					viewHolder1.title.setText(wxArticleModel.title);
					viewHolder1.subTitle.setText(wxArticleModel.subTitle);
					viewHolder1.hitcount.setText("阅读："+wxArticleModel.hitCount);
					viewHolder1.date.setText(wxArticleModel.pubTime);
					
					//创建 ImageListener
					ImageListener listener = ImageLoader.getImageListener(viewHolder1.imageView, R.drawable.img_b_default,R.drawable.img_b_error);
					//加载 图片到控件         图片长宽比约80dp:65dp ：240:200	
					imageLoader.get(wxArticleModel.thumbnails, listener, 240, 200);  //也可以 不限制最大尺寸
					
					//                                     图片长宽比约80dp:65dp ：240:200				
				//	GetImageLoader.getImageLoader(wxArticleModel.thumbnails, imageCache,
				//			viewHolder1.imageView, R.drawable.img_b_default,R.drawable.img_b_error,
				//			800, 650);
				}
				break;
			case WXArticleModel.TYPE_B:
				if(view==null){
					view = View.inflate(getContext(), R.layout.i_wxarticle_item2, null);
					viewHolder2 = new ViewHolder2();
					viewHolder2.title = (TextView)view.findViewById(R.id.title);
					viewHolder2.subTitle = (TextView)view.findViewById(R.id.subTitle);
					viewHolder2.hitcount = (TextView)view.findViewById(R.id.hitcount);
					viewHolder2.date = (TextView)view.findViewById(R.id.date);
					view.setTag(R.id.tag_type_b,viewHolder2);  //ListView 多种类型Item 由于要复用 需要setTag 带KeyId
				}else{
					viewHolder2 = (ViewHolder2)view.getTag(R.id.tag_type_b);  //重新获取 ViewHolder
				}
				//将 数据适配
				if(wxArticleModel != null){
					viewHolder2.title.setText(wxArticleModel.title);
					viewHolder2.subTitle.setText(wxArticleModel.subTitle);
					viewHolder2.hitcount.setText(wxArticleModel.hitCount);
					viewHolder2.date.setText(wxArticleModel.pubTime);
				}
				break;
				 
			default:break;
			}
			
			return view;
		}  	
    }

}
