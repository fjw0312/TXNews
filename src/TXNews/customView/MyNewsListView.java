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
 * �Զ���ؼ�    �̳����пؼ� ��չ
 * ����  �Զ���ؼ�  �� �����б�ؼ�
 * 
 * ListView �ؼ�ʹ��ʱ���Զ�����������
 * getView ���ڻᱻ���ϻ������᲻�Ͻ��룬���� ������Ҫ�ڸ÷��� new�ڴ棨��Ҫ��view!=nullʱ��
 * ��Ҫ���������ͼƬ��ʾʱ����������ؽ���volley,��ע�ⲻҪ�������RequestQueue.
 * ListView��ʾͼƬ�Ż���
 * ����ʹ��Scoll������onScroll  onScrollStateChanged������ֻ������ʾ���ü���ͼƬ��
 * 
 * �����Ż��� ���Կ���Ԥ��������
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
	RequestQueue volleyRequestQueue;   //���� volley�������  
	ImageLoader imageLoader;           //volley ͼƬ������
	MyImageCache imageCache;           //����ͼƬ����
	
	//�ؼ���ʼ��
	private void init_view(){
		volleyRequestQueue = MyVolleyRequestQueue.instance();  //volley�������   ʵ����ȫ�� ����
		imageCache = MyImageCache.instance();                  //ͼƬ����          ʵ����ȫ�� ����
		imageLoader = new ImageLoader(volleyRequestQueue, imageCache);  //ʵ���� ͼƬ������
		
		adapter = new MyNewsListViewAdapter();
		this.setAdapter(adapter);
	}
	
	//���¿ؼ�  ����   �ṩ�ⲿ���ø��� ����
	/**
	 * ����listView adapter ������3�ط�ʽ  ��
	 *  1.����setAdapter 2.����adapter.notifydatasetchanged() 3.��Adapter������ø��½ӿ�
	 * */
	public void Update(List<NewsModel> lst_news){	
	    //adapter = new MyNewsListViewAdapter(lst_news);
		//this.setAdapter(adapter);
		adapter.UpdateAdapter(lst_news);
		adapter.notifyDataSetChanged();
	}
		
	//�Զ���һ��������
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
    	
    	//���� ������ ��������
    	public void UpdateAdapter(List<NewsModel> lst){
    		if(NewsM_lst != null){  
    			NewsM_lst.clear();
    		//	System.gc();
    		}	
    		NewsM_lst = lst;
    		
    	}

		List<NewsModel> NewsM_lst = null;
    	class ViewHolder1{    //����һ������ �ؼ�Ԫ�� �ĸ�����
    		ImageView imageView;
    		TextView  title;
    		TextView  author;
    		TextView  date;
    	}
    	class ViewHolder2{    //����һ������ �ؼ�Ԫ�� �ĸ�����		
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
					//���ViewHolder
					viewHolder1 = new ViewHolder1();
					viewHolder1.imageView = (ImageView)view.findViewById(R.id.imageView);
					viewHolder1.title = (TextView)view.findViewById(R.id.title);
					viewHolder1.author = (TextView)view.findViewById(R.id.author);
					viewHolder1.date = (TextView)view.findViewById(R.id.date);
					view.setTag(R.id.tag_type_a,viewHolder1); //ListView ��������Item ����Ҫ���� ��ҪsetTag ��KeyId
				}else{
					viewHolder1 = (ViewHolder1)view.getTag(R.id.tag_type_a);  //���»�ȡ ViewHolder
				}
				//�� ��������
				if(newsModel != null){ 
					viewHolder1.title.setText(newsModel.title);
					viewHolder1.author.setText(newsModel.author_name);
					viewHolder1.date.setText(newsModel.date);
					// ���� ͼƬ ������ ����ͼƬ     ͼƬ �����Լ80dp:65dp��240:200  
					GetImageLoader.getImageLoader(newsModel.thumbnail_pic_s,imageLoader, 
							viewHolder1.imageView, R.drawable.img_b_default,R.drawable.img_b_error,
							240, 200);
				}
				break;
			case NewsModel.TYPE_B:
				if(view==null){
					view = View.inflate(getContext(), R.layout.i_news_item2, null);
					//���ViewHolder 
					viewHolder2 = new ViewHolder2();		
					viewHolder2.title = (TextView)view.findViewById(R.id.title);
					viewHolder2.author = (TextView)view.findViewById(R.id.author);
					viewHolder2.date = (TextView)view.findViewById(R.id.date);
					viewHolder2.imageView1 = (ImageView)view.findViewById(R.id.img_1);
					viewHolder2.imageView2 = (ImageView)view.findViewById(R.id.img_2);
					viewHolder2.imageView3 = (ImageView)view.findViewById(R.id.img_3);
					view.setTag(R.id.tag_type_b,viewHolder2);  //ListView ��������Item ����Ҫ���� ��ҪsetTag ��KeyId
				}else{
					viewHolder2 = (ViewHolder2)view.getTag(R.id.tag_type_b);  //���»�ȡ ViewHolder 
				}
				//�� �������� 
				if(newsModel != null){ 
					viewHolder2.title.setText(newsModel.title);
					viewHolder2.author.setText(newsModel.author_name);
					viewHolder2.date.setText(newsModel.date);
					// ���� ͼƬ ������ ����ͼƬ      ͼƬ�����117dp:80dp  352:240
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
