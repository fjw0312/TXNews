package TXNews.Main;

import mybroadcast.MyBroadcastReceiver;
import android.app.ActionBar;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import TXNews.bean.VideoItemModel;
import TXNews.customView.MyFragmentLazy;
import TXNews.customView.MyMediaController;
import TXNews.customView.MyNavigationBar;
import TXNews.customView.MyVideoListView;
import TXNews.getUrlContent.GetWYvideo;
import TXNews.url.WYUrl;

public class SpFragment extends MyFragmentLazy{

	public SpFragment() {
		// TODO Auto-generated constructor stub
	}
	
//	MyNavigationBar navigationBar;
	TextView Tx_spTop;
	TextView Tx_spYule;
	TextView Tx_spGaoxiao;
	TextView Tx_spJx; 
	MyVideoListView videoListView;  
	
	String url =  WYUrl.url1; 
	String type = "V9LG4B3A0";  
	 
	VideoView videoView;  
	MyMediaController myMediaController;
	//MediaController mediaController;
	FrameLayout frameLayout;
	ImageView imageView;
	String strTitle = "";
	
	int clickItem = 0;

	@Override
	protected void lazyLaod() {
		// TODO Auto-generated method stub
		super.lazyLaod();
				
		Tx_spTop.setTextColor(Color.RED); 
		MyThread thread = new MyThread();  
		thread.start();
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
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_sp, container, false);
		
//		navigationBar = (MyNavigationBar)view.findViewById(R.id.MyNavigationBar_id);
		Tx_spTop = (TextView)view.findViewById(R.id.Tx_spTop);
		Tx_spYule = (TextView)view.findViewById(R.id.Tx_spYule);
		Tx_spGaoxiao = (TextView)view.findViewById(R.id.Tx_spGaoxiao);
		Tx_spJx = (TextView)view.findViewById(R.id.Tx_spJx);
		videoListView = (MyVideoListView)view.findViewById(R.id.MyVideoListView);
		
		Tx_spTop.setOnClickListener(l);
		Tx_spYule.setOnClickListener(l);
		Tx_spGaoxiao.setOnClickListener(l);
		Tx_spJx.setOnClickListener(l);
		videoListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub 
				clickItem = arg2;
				destoryMyMediaController();
				Adapter adapter = arg0.getAdapter();  
				VideoItemModel videoItemModel = (VideoItemModel)adapter.getItem(arg2);
				MyBroadcastReceiver.sendBroad_MSG_HAL("》》"+videoItemModel.title+":"+videoItemModel.mp4_url);
				videoView = (VideoView)arg1.findViewById(R.id.Vd_videoId);
				myMediaController = (MyMediaController)arg1.findViewById(R.id.MyMediaController);
				frameLayout = (FrameLayout)arg1.findViewById(R.id.frame_id);
				strTitle = ((TextView)arg1.findViewById(R.id.Tx_title)).getText().toString();
				//imageView = (ImageView)arg1.findViewById(R.id.img_id);
				frameLayout.setVisibility(View.GONE);
				myMediaController.setVisibility(View.VISIBLE);
				init_videoView(videoItemModel.mp4_url, strTitle);
				myMediaController.start();
				
			}
		});
		videoListView.setOnScrollListener(new OnScrollListener() {	
			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override     //
			public void onScroll(AbsListView arg0, int firstVisibibleItem, 
					int VisibibleCount, int totalItemCount) {
				// TODO Auto-generated method stub
				if( (clickItem < firstVisibibleItem) || (clickItem>firstVisibibleItem+VisibibleCount)){
					destoryMyMediaController();
				}
			}
		});
		
		return view;
	}
	//释放 视频资源
	private void destoryMyMediaController(){
		if(frameLayout!=null)  frameLayout.setVisibility(View.VISIBLE);	
		if(myMediaController != null)   myMediaController.setVisibility(View.GONE);
		if(videoView != null)   videoView.stopPlayback();
		videoView = null;
		myMediaController = null;
	}
	
	//视频播放线程
	private void init_videoView(String str_url, String text){	
		videoView.setVideoURI( Uri.parse(str_url) );
		myMediaController.setMediaPlayer(videoView, text);
		videoView.requestFocus();

		videoView.setOnCompletionListener(new OnCompletionListener() { //监听播放结束		
			@Override
			public void onCompletion(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				destoryMyMediaController();
			}
		});
		videoView.setOnErrorListener(new OnErrorListener() { //监听播放错误			
			@Override
			public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}
	private OnClickListener l = new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				destoryMyMediaController();
				//处理 之前的控件变色
				if("V9LG4B3A0".equals(type)){
					Tx_spTop.setTextColor(Color.BLACK);
				}else if("V9LG4CHOR".equals(type)){
					Tx_spYule.setTextColor(Color.BLACK);
				}else if("V9LG4E6VR".equals(type)){
					Tx_spGaoxiao.setTextColor(Color.BLACK);
				}else if("00850FRB".equals(type)){
					Tx_spJx.setTextColor(Color.BLACK);
				}
				
				if(arg0==Tx_spTop){
					url =  WYUrl.url1;
					type = "V9LG4B3A0";
					Tx_spTop.setTextColor(Color.RED);
				}else if(arg0==Tx_spYule){
					url =  WYUrl.url2;
					type = "V9LG4CHOR";
					Tx_spYule.setTextColor(Color.RED);
				}else if(arg0==Tx_spGaoxiao){
					url =  WYUrl.url3;
					type = "V9LG4E6VR";
					Tx_spGaoxiao.setTextColor(Color.RED);
				}else if(arg0==Tx_spJx){
					url =  WYUrl.url4;
					type = "00850FRB";
					Tx_spJx.setTextColor(Color.RED);
				}
				//启动网络线程
				MyThread thread = new MyThread();
				thread.start();
			}
	};
	//网络 请求线程
	private class MyThread extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			GetWYvideo.getWYvideo(url, type, videoListView);		
		}	
	}

}
