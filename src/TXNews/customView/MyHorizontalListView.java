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
 * �Զ���ؼ�     �̳����пؼ�    �Զ���
 * ����  �Զ���ؼ�  �� �����б�
 *    //���� �����б�    �̳����пؼ�GridView
 *    notice: ʹ��ʱ �����   <HorizontalScrolView  <LinearLayout/>  ����   />  ���ܹ���
 *    
 *    author��fjw0312@163.com
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
		
		//�ؼ�  Ĭ��  ����
		private int itemWith = 120;
		
		
		MyHorizontalListViewAdapter adapter;
		TextView textView;  //ѡ�е�item_id
		int hasSelect = 0;
		//��ʼ�� �ؼ�  ��ò����ļ�
		private void init_view(Context context){
			//��� ������
			adapter = new MyHorizontalListViewAdapter(); //û������
			this.setAdapter(adapter);
		}
		//���¿ؼ�  ����   �ṩ�ⲿ���ø��� ����  
		public void Update(List<String> lst,int ItemWith){	
			itemWith = ItemWith;
			Update(lst);
		}
		public void Update(List<String> lst){		
			android.view.ViewGroup.LayoutParams layoutParams = this.getLayoutParams();
			layoutParams.width = lst.size() *itemWith;
			this.setLayoutParams(layoutParams);  //���� �װ����		
			this.setNumColumns( lst.size() );	 //��������
			this.setVerticalScrollBarEnabled(false);  //���ù�����  ���ɼ�
			adapter = new MyHorizontalListViewAdapter(lst); //����
			this.setAdapter(adapter);
		}
		//���� ѡ������仯    �ṩ�ⲿ����
		public void Select(int itemId){
			View oldView = this.getChildAt(hasSelect);
			TextView oldTextView = (TextView)oldView.getTag();
			oldTextView.setTextColor(Color.BLACK);
			hasSelect = itemId;
			View View = this.getChildAt(hasSelect);
			TextView textView = (TextView)View.getTag();
			textView.setTextColor(Color.RED);
			//TextPaint paint = textView.getPaint();  //����������
			//paint.setFakeBoldText(true);
		}
	


	//����  ����������
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
				//���� ���� ��(Ĭ��)��һ��Item��Ϊ��ɫ
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
