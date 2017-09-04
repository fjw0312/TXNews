package TXNews.utils;

import java.util.ArrayList;
import java.util.List;
import TXNews.Main.R;
import TXNews.bean.RecordModel;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

/**
 * ������
 * ���ڣ���ʷ��¼  �ղؼ�¼ �б�   ������
 * */
public class MyRecordAdapter extends BaseAdapter{

	public MyRecordAdapter(Context context, List<RecordModel> list) {
		// TODO Auto-generated constructor stub
		mContext = context;
		lst = list;
	}
	
	Context mContext;
	List<RecordModel> lst = null;
	public int CheckBox_V = View.GONE; 
	public int AllChoice = 0;  //ȫѡ��־
	
	//����  ���ýӿ�
	public void showCheckBox(){  //��ʾ  ��ѡ���
		CheckBox_V = View.VISIBLE;		
	//	notifyDataSetChanged();
		showRemoveChoiceAll();
	}
	public void showCheckBoxEnd(){ //����  ��ѡ���
		CheckBox_V = View.GONE;
		notifyDataSetChanged();
	}

	public void showChoiceAll(){  //��ʾ  ѡ���   ȫѡ
		CheckBox_V = View.VISIBLE;
		for(int i=0;i<getCount();i++){
			RecordModel recordModel = (RecordModel)getItem(i);
			recordModel.hasChoice = true;
		}
		notifyDataSetChanged();
	}
	public void showRemoveChoiceAll(){//��ʾ  ѡ���   ��ȫѡ
		CheckBox_V = View.VISIBLE;
		for(int i=0;i<getCount();i++){
			RecordModel recordModel = (RecordModel)getItem(i);
			recordModel.hasChoice = false;
		}
		notifyDataSetChanged();
	}
	public void deleteChoice(){  //ɾ�� ѡ��Item
		List<RecordModel> new_lst = new ArrayList<RecordModel>();
		for(int i=0;i<getCount();i++){
			RecordModel recordModel = (RecordModel)getItem(i);
			if(recordModel.hasChoice == false){
				new_lst.add(recordModel);
			}		
		}
		lst = new_lst;
		showCheckBoxEnd();
	}
	public void deleteAll(){  //ɾ�� ����Item
		lst.clear();
		showCheckBoxEnd();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(lst==null) return 0;
		return lst.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		if(lst==null) return null;
		return lst.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		RecordModel recordModel = (RecordModel)getItem(arg0);
		recordModel.id = arg0;
		
		ViewHolder viewHolder;
		if(arg1==null){
				arg1 = View.inflate(mContext, R.layout.i_historyrecord_item, null);//����д��һ��
				
				//���ViewHolder
				viewHolder = new ViewHolder();
				viewHolder.TView_Title = (TextView)arg1.findViewById(R.id.Tx_title);
				viewHolder.TView_url = (TextView)arg1.findViewById(R.id.Tx_url);
				viewHolder.checkBox = (CheckBox)arg1.findViewById(R.id.checkBox_id);
				viewHolder.checkBox.setTag(recordModel);
				arg1.setTag(viewHolder);
		}else{
				viewHolder = (ViewHolder)arg1.getTag();  //���»�ȡ ViewHolder  
				viewHolder.checkBox.setTag(recordModel);  //Ӧ�� ���  ����������recordModel
		}
		
		if(recordModel != null){ 
			viewHolder.TView_Title.setText(recordModel.title);
			viewHolder.TView_url.setText(recordModel.url);  
			viewHolder.checkBox.setVisibility(CheckBox_V);  //���� ѡ��� �Ƿ�ɼ�
			if(recordModel.hasChoice){  //����  ��ʾ  ѡ��״̬
				viewHolder.checkBox.setChecked(true);
			}else{
				viewHolder.checkBox.setChecked(false);	
			}
			//  ���� ����״̬  ��ѡ���  
			viewHolder.checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {			
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean arg1) {
					// TODO Auto-generated method stub
					RecordModel p = (RecordModel) buttonView.getTag();
					if(arg1){
						p.hasChoice = true;
					//	Log.i("MyRecordAdapter>>onCheckedChanged--True>"+String.valueOf(p.id), String.valueOf(p.hasChoice));
					}else{
						p.hasChoice = false;
					//	Log.i("MyRecordAdapter>>onCheckedChanged--false>"+String.valueOf(p.id), String.valueOf(p.hasChoice));
					}
					
				}
			});
		}
		
		
		return arg1; 
	}
	
	private class ViewHolder{
		TextView TView_Title;
		TextView TView_url;
		CheckBox checkBox;
	}

}
