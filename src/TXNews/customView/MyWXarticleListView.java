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
 * �Զ���ؼ�    �̳����пؼ� ��չ
 * ����  �Զ���ؼ�  ��΢�ž�ѡ�б�ؼ�
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
	RequestQueue volleyRequestQueue;   //���� volley�������  
	ImageLoader imageLoader;           //volley ͼƬ������
	MyImageCache imageCache;           //����ͼƬ����
	
	//�ؼ���ʼ��
	private void init_view(){	
		volleyRequestQueue = MyVolleyRequestQueue.instance();  //volley�������   ʵ����ȫ�� ����
		imageCache = MyImageCache.instance();                  //ͼƬ����          ʵ����ȫ�� ����
		imageLoader = new ImageLoader(volleyRequestQueue, imageCache);  //ʵ���� ͼƬ������
		
		adapter = new MyWXarticleListViewAdapter();
		this.setAdapter(adapter);
	}
	
	//���¿ؼ�  ����   �ṩ�ⲿ���ø��� ����
	public void Update(List<WXArticleModel> lst_news){
	//	adapter = new MyWXarticleListViewAdapter(lst_news);
	//	this.setAdapter(adapter);
		adapter.UpdateAdapter(lst_news);
		adapter.notifyDataSetChanged();
	}
		
	//�Զ���һ��������
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
    	//���� ������ ��������
    	public void UpdateAdapter(List<WXArticleModel> lst){
    		if(NewsM_lst != null){  
    			NewsM_lst.clear();
    		}	
    		NewsM_lst = lst; 		
    	}
    	
		List<WXArticleModel> NewsM_lst = null;
    	class ViewHolder1{    //����һ������ �ؼ�Ԫ�� �ĸ�����
    		ImageView imageView;
    		TextView  title;
    		TextView  subTitle;
    		TextView  hitcount;
    		TextView  date;
    	}
    	class ViewHolder2{    //����һ������ �ؼ�Ԫ�� �ĸ�����
    		
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
					viewHolder1.imageView = (ImageView)view.findViewById(R.id.imageView);//���ViewHolder
					viewHolder1.title = (TextView)view.findViewById(R.id.title);
					viewHolder1.subTitle = (TextView)view.findViewById(R.id.subTitle);
					viewHolder1.hitcount = (TextView)view.findViewById(R.id.hitcount);
					viewHolder1.date = (TextView)view.findViewById(R.id.date);
					view.setTag(R.id.tag_type_a,viewHolder1); //ListView ��������Item ����Ҫ���� ��ҪsetTag ��KeyId				
				}else{
					viewHolder1 = (ViewHolder1)view.getTag(R.id.tag_type_a);  //���»�ȡ ViewHolder
				}
				//�� ��������
				if(wxArticleModel != null){
					viewHolder1.title.setText(wxArticleModel.title);
					viewHolder1.subTitle.setText(wxArticleModel.subTitle);
					viewHolder1.hitcount.setText("�Ķ���"+wxArticleModel.hitCount);
					viewHolder1.date.setText(wxArticleModel.pubTime);
					
					//���� ImageListener
					ImageListener listener = ImageLoader.getImageListener(viewHolder1.imageView, R.drawable.img_b_default,R.drawable.img_b_error);
					//���� ͼƬ���ؼ�         ͼƬ�����Լ80dp:65dp ��240:200	
					imageLoader.get(wxArticleModel.thumbnails, listener, 240, 200);  //Ҳ���� ���������ߴ�
					
					//                                     ͼƬ�����Լ80dp:65dp ��240:200				
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
					view.setTag(R.id.tag_type_b,viewHolder2);  //ListView ��������Item ����Ҫ���� ��ҪsetTag ��KeyId
				}else{
					viewHolder2 = (ViewHolder2)view.getTag(R.id.tag_type_b);  //���»�ȡ ViewHolder
				}
				//�� ��������
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
