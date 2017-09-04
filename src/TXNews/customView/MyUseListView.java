package TXNews.customView;

import java.util.List;
import TXNews.Main.R;
import TXNews.bean.UseItemModel;
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
 * 定制  自定义控件  ： 用户使用  列表控件 
 * 
 * */
public class MyUseListView  extends ListView{

	public MyUseListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub 
		init_view(); 
	}

	public MyUseListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init_view();
	}

	public MyUseListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init_view();
	}
	MyUseListViewAdapter adapter;
	
	//控件初始化
	private void init_view(){
		adapter = new MyUseListViewAdapter();
		this.setAdapter(adapter);
	}
	
	//更新控件  数据   提供外部调用更新 数据
	public void Update(List<UseItemModel> lst_news){
		adapter = new MyUseListViewAdapter(lst_news);
		this.setAdapter(adapter);
	}
		
	//自定义一个适配器
    public class MyUseListViewAdapter extends BaseAdapter{
    	
    	public MyUseListViewAdapter() {
			super();
			// TODO Auto-generated constructor stub
		}
    	public MyUseListViewAdapter(List<UseItemModel> lst_item) {
			super();
			// TODO Auto-generated constructor stub
			useItemModel_lst = lst_item;
		}

    	List<UseItemModel> useItemModel_lst = null;
    	class ViewHolder{    //定义一个保存 控件元素 的辅助类
    		ImageView img_itemLogo;
    		TextView  itemTitle;
    		TextView  itemEtra;
    		ImageView img_itemSms;
    	}
    	 
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if(useItemModel_lst==null) return 0;
			return useItemModel_lst.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			if(useItemModel_lst==null) return null;
			return useItemModel_lst.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			UseItemModel useItemModel = (UseItemModel)getItem(arg0);
			View view;
			ViewHolder viewHolder;
			
			if(arg1==null){
			//	view = LayoutInflater.from(getContext()).inflate(item_layout_id, null); 
				view = View.inflate(getContext(), R.layout.i_uselist_item, null);//两种写法一样
				
				//添加ViewHolder
				viewHolder = new ViewHolder();
				viewHolder.img_itemLogo = (ImageView)view.findViewById(R.id.Img_Itemlogo);
				viewHolder.itemTitle = (TextView)view.findViewById(R.id.Tx_ItemTitle);
				viewHolder.itemEtra = (TextView)view.findViewById(R.id.Tx_ItemExtra);
				viewHolder.img_itemSms = (ImageView)view.findViewById(R.id.Img_smsLogo);
				view.setTag(viewHolder);
			}else{
				view = arg1;
				viewHolder = (ViewHolder)view.getTag();  //重新获取 ViewHolder 
			}
			
			//将 数据适配
			if(useItemModel != null){ 
				if(useItemModel.itemLogo != 0){
					viewHolder.img_itemLogo.setImageResource(useItemModel.itemLogo);
				}else{ //不显示
					viewHolder.img_itemLogo.setVisibility(GONE);
				}			
				viewHolder.itemTitle.setText(useItemModel.itemTitle);
				viewHolder.itemEtra.setText(useItemModel.itemExtra);
				viewHolder.img_itemSms.setImageResource(useItemModel.smsLogo);
			}
			
			return view;
		}  	
    }

}
