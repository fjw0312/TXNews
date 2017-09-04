package TXNews.customView;

import java.util.List;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import TXNews.Main.R;
import TXNews.bean.VideoItemModel;
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
import android.widget.VideoView;


/***
 * 自定义控件    继承现有控件 拓展
 * 定制  自定义控件  ： 视频列表控件
 * 
 * */
public class MyVideoListView extends ListView{

	public MyVideoListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init_view(); 
	}

	public MyVideoListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init_view();
	}

	public MyVideoListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init_view();
	}
	MyVideoListViewAdapter adapter;
	RequestQueue volleyRequestQueue;   //定义 volley请求队列  
	ImageLoader imageLoader;           //volley 图片请求器
	MyImageCache imageCache;           //定义图片缓存
	
	//控件初始化
	private void init_view(){
		volleyRequestQueue = MyVolleyRequestQueue.instance();  //volley请求队列   实例化全局 单例
		imageCache = MyImageCache.instance();                  //图片缓存          实例化全局 单例
		imageLoader = new ImageLoader(volleyRequestQueue, imageCache);  //实例化 图片请求器
		
		adapter = new MyVideoListViewAdapter();
		this.setAdapter(adapter);
	}
	
	//更新控件  数据   提供外部调用更新 数据
	public void Update(List<VideoItemModel> lst_news){
		//adapter = new MyVideoListViewAdapter(lst_news);
		//this.setAdapter(adapter);
		adapter.UpdateAdapter(lst_news);
		adapter.notifyDataSetChanged();
	}
		
	//自定义一个适配器
    public class MyVideoListViewAdapter extends BaseAdapter{
    	
    	public MyVideoListViewAdapter() {
			super();
			// TODO Auto-generated constructor stub
		}
    	public MyVideoListViewAdapter(List<VideoItemModel> lst_news) {
			super();
			// TODO Auto-generated constructor stub
			NewsM_lst = lst_news;
		}

    	//更新 适配器 所有数据
    	public void UpdateAdapter(List<VideoItemModel> lst){
    		if(NewsM_lst != null){  
    			NewsM_lst.clear();
    		}	
    		NewsM_lst = lst; 		
    	}
    	
		List<VideoItemModel> NewsM_lst = null;
    	class ViewHolder{    //定义一个保存 控件元素 的辅助类
    		ImageView imageView;
    		TextView  title;
    		TextView  time;
    		VideoView videoView;
    		MyMediaController myMediaController;
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
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			VideoItemModel videoItemModel = (VideoItemModel)getItem(arg0);
			View view;
			ViewHolder viewHolder;
			
			if(arg1==null){
			//	view = LayoutInflater.from(getContext()).inflate(item_layout_id, null); 
				view = View.inflate(getContext(), R.layout.i_videolist_item, null);//两种写法一样
				
				//添加ViewHolder
				viewHolder = new ViewHolder();
				viewHolder.imageView = (ImageView)view.findViewById(R.id.img_id);
				viewHolder.title = (TextView)view.findViewById(R.id.Tx_title);
				viewHolder.time = (TextView)view.findViewById(R.id.Tx_time);
				viewHolder.videoView = (VideoView)view.findViewById(R.id.Vd_videoId);
				viewHolder.myMediaController = (MyMediaController)view.findViewById(R.id.MyMediaController);
				view.setTag(viewHolder);
			}else{
				view = arg1;
				viewHolder = (ViewHolder)view.getTag();  //重新获取 ViewHolder
			}
			
			//将 数据适配
			if(videoItemModel != null){ 
				String str_min = "";
				String str_sec = "";
				String str_time = videoItemModel.sizeHD;
				 if("".equals(str_time)==false){
					 int time_i = ( Integer.parseInt( str_time ) )/10;
					 str_min = String.valueOf(time_i/60);
					 str_sec = String.valueOf(time_i%60);
				 }
				viewHolder.title.setText(videoItemModel.title);
				if("0".equals(str_time)==false){
					if("0".equals(str_sec)) str_sec = "00";
					 
					viewHolder.time.setText(str_min+":"+str_sec); 
				}
				

				// 调用 图片 加载类 加载图片       图片布局长宽比例 1080:618(620)  320:206 
				GetImageLoader.getImageLoader(videoItemModel.cover, imageLoader,
						viewHolder.imageView, R.drawable.img_a_default,R.drawable.img_a_error,
						800, 600);
				//准备 播放video
			}
			
			return view;
		}  	
    }

}
