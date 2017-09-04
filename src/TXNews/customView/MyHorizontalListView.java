package TXNews.customView;

import java.util.List;
import TXNews.Main.R;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;


/***
 * 自定义控件     继承现有控件    自定义
 * 定制  自定义控件  ： 横向列表
 *    //定义 横向列表    继承现有控件GridView
 *    notice: 使用时 最好用   <HorizontalScrolView  <LinearLayout/>  包裹   />  才能滚动
 *    
 *    author：fjw0312@163.com
 *    date:2017.8.1
 * */
public class MyHorizontalListView extends GridView{

		public MyHorizontalListView(Context context, AttributeSet attrs,
				int defStyle) {
			super(context, attrs, defStyle);
			// TODO Auto-generated constructor stub
			init_view(context);
		}

		public MyHorizontalListView(Context context, AttributeSet attrs) {
			super(context, attrs);
			// TODO Auto-generated constructor stub 
			init_view(context);
		}

		public MyHorizontalListView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			init_view(context);
		}
		
		//控件  默认  参数
		private int itemWith = 120;
		
		
		MyHorizontalListViewAdapter adapter;
		TextView textView;  //选中的item_id
		int hasSelect = 0;
		//初始化 控件  获得布局文件
		private void init_view(Context context){
			//添加 适配器
			adapter = new MyHorizontalListViewAdapter(); //没有数据
			this.setAdapter(adapter);
		}
		//更新控件  数据   提供外部调用更新 数据  
		public void Update(List<String> lst,int ItemWith){	
			itemWith = ItemWith;
			Update(lst);
		}
		public void Update(List<String> lst){		
			android.view.ViewGroup.LayoutParams layoutParams = this.getLayoutParams();
			layoutParams.width = lst.size() *itemWith;
			this.setLayoutParams(layoutParams);  //设置 底板参数		
			this.setNumColumns( lst.size() );	 //设置列数
			this.setVerticalScrollBarEnabled(false);  //设置滚动条  不可见
			adapter = new MyHorizontalListViewAdapter(lst); //数据
			this.setAdapter(adapter);
		}
		//更新 选择子项变化    提供外部调用
		public void Select(int itemId){
			View oldView = this.getChildAt(hasSelect);
			TextView oldTextView = (TextView)oldView.getTag();
			oldTextView.setTextColor(Color.BLACK);
			hasSelect = itemId;
			View View = this.getChildAt(hasSelect);
			TextView textView = (TextView)View.getTag();
			textView.setTextColor(Color.RED);
			//TextPaint paint = textView.getPaint();  //设置字体变粗
			//paint.setFakeBoldText(true);
		}
	


	//定义  定制适配器
	public class MyHorizontalListViewAdapter extends BaseAdapter{
		
		public MyHorizontalListViewAdapter() {
			super();
			// TODO Auto-generated constructor stub
		}
		public MyHorizontalListViewAdapter(List<String> lst) {
			super();
			// TODO Auto-generated constructor stub
			Str_lst = lst;
		}
		
		List<String> Str_lst = null;
		

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if(Str_lst==null) return 0;
			return Str_lst.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			if(Str_lst==null) return null;
			return Str_lst.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			String text = (String)getItem(arg0);
			View view;
			TextView textView;
			
			if(arg1==null){
				view = View.inflate(getContext(), R.layout.i_horlstview_item, null);
				textView = (TextView)view.findViewById(R.id.Tx_barItem);
				view.setTag(textView);
				//初次 适配 将(默认)第一个Item变为红色
				if(arg0 == hasSelect){
					textView.setTextColor(Color.RED);
				}
			}else{ 
				view = arg1;
				textView = (TextView)view.getTag();
			}
			
			if(text != null){
				textView.setText(text);
		//		Log.i("MyHorizontalListView>>getView", text);
			}
			
			
			return view;
		}
		
	}
}
