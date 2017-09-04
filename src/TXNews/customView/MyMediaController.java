package TXNews.customView;

import TXNews.Main.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.SeekBar.OnSeekBarChangeListener;


/**
 * �Զ��� ���ſ�����
 * author:fjw0312@163.com
 * date:2017.8.22
 * ���� �Զ���ؼ�     �Զ��� ViewGroup����(������ϲ���) �ؼ�
 * �Զ��岼������ ��Ҫ��д  onMeasure  onLayout 
 * �Զ��� ��Ͽؼ� ��Ҫ��ȡ����
 * �Զ���  �̳���չ���пؼ�
 * 
 * onSizeChanged() ���ڵ�һ�� onMeasure�����
 * 
 * 
 * ע�� Ŀǰ ������bug ���Ż��� 2017.8.28
 * */

public class MyMediaController extends FrameLayout{
	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		// TODO Auto-generated method stub
		super.onVisibilityChanged(changedView, visibility);
		if(visibility == VISIBLE){
			Log.i("onVisibilityChanged>>",  "VISIBLE");
		}else if(visibility == INVISIBLE){
			Log.i("onVisibilityChanged>>",  "INVISIBLE");			
			pause_position = OldCurrentPosition;
		}else if(visibility == GONE){
			Log.i("onVisibilityChanged>>",  "GONE");
		}
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		Log.i("MyMediaController>>onSizeChanged","into!");
		super.onSizeChanged(w, h, oldw, oldh);
		// �� ������ӵ� �ؼ�	
		init_view(myContext);	 
	}

	public MyMediaController(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		myContext = context;
		
	}
	public MyMediaController(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		myContext = context;
		
	}
	public MyMediaController(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		myContext = context;
		
	}

	
	private View ly_view;   //�Զ������ ����
	private LinearLayout ly_contrlBar;//�Զ������ ����  ������ 
	private LinearLayout ly_content;  //��Ƶ�ڲ�����  ���ڵ����ͣ��
	private TextView Tx_title;        //�Զ������ ���� ����
	private ImageView img_pgUp;
	private TextView Tx_TimeStart;
	private TextView Tx_TimeEnd;
	private ImageView img_c_play;
	private ImageView img_c_pause;
	private SeekBar seekBar;
	private Context myContext;
	
	int widthSize = 0;   //this���ֿؼ�  ���
	int heightSize = 0;
	
	String strTx_TimeStart = "";
	String strTx_Title = "";
	
	
	public static int URI_LOADCATION = 1;  //���ű�����Դ  ��ʶ
	public static int URI_NET = 2;         //����������Դ  ��ʶ
	int Localtion_OR_Net = 0; //����/����
	
	public static int PLAY_STATE_WILL = 1;      //���ž��� ״̬
	public static int PLAY_STATE_PLAYING = 2;   //���Ž��� ״̬
	public static int PLAY_STATE_PAUSE = 3;     //������ͣ ״̬
	public static int PLAY_STATE_NETWAIT = 4;   //���绺��  ״̬
	public static int PLAY_STATE_ReView = 5;    //���Ų��ɼ�  ״̬
	
	int show_Control = 0;   //��ʾ ������ͼ
	
	MediaPlayer MyMediaPlayer;  
	VideoView videoView;
	int DurationTime = 0;      //��Ƶʱ��  ms
	int CurrentPosition = 0;   //���Ž���  ms
	int OldCurrentPosition = 0;   //ǰ1s���Ž���  ms
	int BufferPercentage = 0;  //����ٷֱ�
	int pause_position = 0 ;  //��ͣʱ���Ž���
	int seek_To = 0;    //������ �϶�λ��
	
	//�ṩ---*************************-  �ⲿ�������˿�    ��*************************
		public void start(){  //��ʼ����
			if( videoView != null ){ //����  ����׼��
				videoView.start();			
			}
		}
		public void pause(){
			if( videoView != null ){ //����  ����׼�� 
				videoView.pause();
			}
		}
		public void resume(){
			if( videoView != null ){
				OnResume();
			}
		}
		/**
		 * ����  ֧�ֽ������½���
		 * */
		private void OnStart(){ //��ʼ����	������˸����  ------seekBar ��������  ��λ����
			if(videoView != null){
				videoView.resume();
				videoView.seekTo(CurrentPosition);
				videoView.start();
			}
		}
		/**
		 * ����  ֧�ֽ������½���
		 * */
		private void OnPause(){ //��ͣ����                          -----seekBar ����ʱ ���� ����
			if(videoView != null){
				videoView.pause(); 
				updateSeekBar();
			}
		}
		/**
		 * ��λ ���²���
		 * */
		private void OnResume(){ //���²���                         ------resume ����
			if(videoView != null){
				videoView.resume();
				videoView.start();
			}
		} 
		public void OnDestory(){ //���ٲ�����
			if(videoView != null){
				videoView.stopPlayback();
				videoView = null;
				reparedListener = null;
				bufferingUpdateListener = null;
				seekBar_l = null;
				myOnTouchListenner = null;
				l = null;
			}
		}

	//==========================���� �ӿ�
	/**
	 * �Զ��� ������ ���ýӿ�
	 * ����Object�� VideoView or MediaPlayer  mode ���ز���ģʽ ���� ���粥��ģʽ 
	 * */
	public boolean setMediaPlayer(Object object, String strTitle){
		if(object == null) return false;
		//Localtion_OR_Net = mode;
		if(object instanceof VideoView){   //ֻ֧�� VideoView 
			videoView = (VideoView)object;
			videoView.setOnPreparedListener(reparedListener);  //���ü���
			strTx_Title = strTitle;
		}else{
			return false;
		} 
		return true;
	}
	// ���� ����׼��������  ��ʼ�� ����׼��  ֻ��Ҫ���嵽 ���Բ��žͻᴥ��  Ҳֻ�ᴥ��һ�� 
	private OnPreparedListener reparedListener = new OnPreparedListener(){
		@Override
		public void onPrepared(MediaPlayer mediaPlayer) {
			// TODO Auto-generated method stub
			MyMediaPlayer = mediaPlayer;
			DurationTime = mediaPlayer.getDuration();  //��ȡ ��Ƶ��ʱ��
			seekBar.setMax(DurationTime);
			String str = Num_sec_TurnTo_Date(DurationTime);
			Tx_TimeEnd.setText(str);		
			Tx_title.setText(strTx_Title);
			
			Log.i("MyMediaController>>reparedListener", "into!");		
		
			mediaPlayer.setOnBufferingUpdateListener(bufferingUpdateListener);//���û������ ������
			
		}
	};


	
	// ����   ���� ������¼�����      //Լ1/s �����һ��ֱ�����Ž���   �ʣ�ʹ��������Դ �����øü��� ��� ��ʱ��
	private OnBufferingUpdateListener bufferingUpdateListener = new OnBufferingUpdateListener(){
		@Override
		public void onBufferingUpdate(MediaPlayer mediaPlayer, int arg1) {  //arg1 Ϊ���绺��ٷֱ�
			// TODO Auto-generated method stub
			
			BufferPercentage = arg1; //��ȡ ��Ƶ������Ȱٷֱ�  videoView.getBufferPercentage();
			seekBar.setSecondaryProgress( (BufferPercentage*100)/DurationTime );
			updateSeekBar();
			Log.i("MyMediaController>>bufferingUpdateListener","BufferPercentage��"+String.valueOf(BufferPercentage));
			
			//���� ������ͼ  �ȴ���ʱ  ������ͼ
			if( show_Control != 0 ){
				show_Control--;
				if(show_Control<=0){
					show_Control = 0;
					ly_contrlBar.setVisibility(GONE);
					Tx_title.setVisibility(GONE);
					img_c_pause.setVisibility(GONE);
					img_c_play.setVisibility(GONE);
					Log.e("onTouch>>ACTION_DOWN","����  ������----");
				}
			}
		}	
	};
		
	//--------------------- ----------�ڲ����ò���-------------------------
	//���� ���ſ�����
	private void updateSeekBar(){  
		if(videoView==null) return;
		try{
			OldCurrentPosition = CurrentPosition;
			CurrentPosition = videoView.getCurrentPosition(); //��ȡ���Ž��� 
			seekBar.setProgress(CurrentPosition);
			seekBar.setSecondaryProgress( (BufferPercentage*100)/DurationTime );
			//seekBar.setSecondaryProgress( videoView.getBufferPercentage() ); //�������绺��s 
			strTx_TimeStart = Num_sec_TurnTo_Date(CurrentPosition);
			Tx_TimeStart.setText(strTx_TimeStart);

		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	//�� ms ʱ�� ת��Ϊʱ��00:00:00   ������� ms
	private String Num_sec_TurnTo_Date(int secNum){
		int num  = secNum/1000;
		int h = num/3600;
		int m = (num%3600)/60;
		int s = (num%3600)%60;
		String str_h = String.valueOf(h)+":";
		String str_m = String.valueOf(m)+":";
		String str_s = String.valueOf(s);
		if(h == 0){ 
			str_h = "";
		}else if(h<10){
			str_h = "0"+str_h;
		}
		if(m == 0){ 
			str_m = "00:";
		}else if(m<10){
			str_m = "0"+str_m;
		}
		if(s == 0){
			str_s = "00";
		}else if(s<10){
			str_s = "0"+str_s;
		}		
		String str = str_h+str_m+str_s;
		return str;
	}	

	//======================================�ؼ� ��������===================================
	private OnClickListener l = new OnClickListener() {		 
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0 == img_pgUp){ 
				Log.i("OnClickListener>>img_pgUp","���");
				//ȫ�� ����  ----δʵ��
				
			}else if(arg0==img_c_play){
				Log.i("OnClickListener>>img_c_play","��� �м��ʶ");
				 //����
				start();
				img_c_pause.setVisibility(GONE);
				img_c_play.setVisibility(GONE);
				ly_contrlBar.setVisibility(GONE);
				Tx_title.setVisibility(GONE);
				//show_Control = 3; //��ʾ3s
			}else if(arg0==img_c_pause){
				Log.i("OnClickListener>>img_c_pause","��� �м��ʶ");
				//��ͣ 
				pause();
				img_c_pause.setVisibility(GONE);
				img_c_play.setVisibility(VISIBLE);
				ly_contrlBar.setVisibility(VISIBLE);
				Tx_title.setVisibility(VISIBLE);
				show_Control = 3; //��ʾ3s  
			}
		}
	};
	@SuppressLint("ClickableViewAccessibility")
	private OnTouchListener myOnTouchListenner = new OnTouchListener(){
		@Override
		public boolean onTouch(View arg0, MotionEvent event) {
			// TODO Auto-generated method stub
			//event.getAction() Ϊ��8λ�������ͣ���8λ+1λ��������� 
			//�ú���&0xff ��&MotionEvent.ACTION_MASK = event.getActionMasked()
			switch(event.getActionMasked()){      //�жϴ���������getActionMasked() 
				case MotionEvent.ACTION_DOWN: //����ָ����
					Log.i("onTouch>>ACTION_DOWN","����ָ����");
					if(show_Control == 0){
						Log.i("onTouch>>ACTION_DOWN","��ʾ  ������----");
						try{
						if(videoView.isPlaying()){
							img_c_pause.setVisibility(VISIBLE);
							img_c_play.setVisibility(GONE);
						}else{
							img_c_pause.setVisibility(GONE);
							img_c_play.setVisibility(VISIBLE);
						}
						ly_contrlBar.setVisibility(VISIBLE);
						Tx_title.setVisibility(VISIBLE);
						show_Control = 3; //��ʾ3s
						}catch(Exception e){
							e.printStackTrace();
						}
					}
					break;
				case MotionEvent.ACTION_UP : //����ָ����
					Log.i("onTouch>>ACTION_DOWN","����ָ����");
					break;
				case MotionEvent.ACTION_MOVE : //����ָ�ƶ�  
					Log.i("onTouch>>ACTION_DOWN","����ָ�ƶ�");
					break;
				default: break; 
			}
			return true;  //ֻ�з���true ���ܼ�����ACTION_MOVE  ACTION_UP
		}
		
	};
	// ���� seekBar �϶� �ı����   notice�������϶����ò��Ž��� ��bug �޷���ʱ��Ӧ
	private OnSeekBarChangeListener seekBar_l = new OnSeekBarChangeListener() {	 
		@Override
		public void onStopTrackingTouch(SeekBar arg0) { //ֹͣ����
			// TODO Auto-generated method stub
			//Log.i("OnSeekBarChangeListener>>SeekBar","SeekBarֹͣ����");
			CurrentPosition = seek_To;
			OnStart();
		} 
		
		@Override
		public void onStartTrackingTouch(SeekBar arg0) {//��ʼ����
			// TODO Auto-generated method stub 
			//Log.i("OnSeekBarChangeListener>>SeekBar","SeekBar��ʼ����");
			OnPause();		
		}
		
		@Override
		public void onProgressChanged(SeekBar arg0, int progresss, boolean fromUser) {  //����
			// TODO Auto-generated method stub 
			if(fromUser){
				seek_To = progresss;
			}else{
				//������  fromUser = false
			//	Log.i("OnSeekBarChangeListener>>SeekBar�����϶�-false",String.valueOf(progresss)+"  "+String.valueOf(fromUser));
			}		 
		}
	};

	//---------------------------------------�Զ��� �ؼ� ����----------------------------------
    //��ʼ�� ����������ӵ��ؼ���    ���ֿ����� ��wrap_content�����»���� ��View(VideoView)�Ĵ�С 
	private void init_view(Context context){ 
		ly_view = LayoutInflater.from(context).inflate(R.layout.my_mediacontroller, this); //��ҳ����ص� ��View
		android.view.ViewGroup.LayoutParams layoutParams = ly_view.getLayoutParams();
		layoutParams.height = heightSize;
		layoutParams.width = widthSize;
		ly_view.setLayoutParams(layoutParams);
		
		//��ȡ�ؼ�   
		ly_contrlBar = (LinearLayout)ly_view.findViewById(R.id.ly_controlBar);
		Tx_title = (TextView)ly_view.findViewById(R.id.Tx_title);
		img_pgUp = (ImageView)ly_view.findViewById(R.id.img_upDown);
		Tx_TimeStart = (TextView)ly_view.findViewById(R.id.Tx_timeStart); 
		Tx_TimeEnd = (TextView)ly_view.findViewById(R.id.Tx_timeEnd);
		seekBar = (SeekBar)ly_view.findViewById(R.id.seekbar_id);
		
		ly_content = (LinearLayout)ly_view.findViewById(R.id.ly_content);
		img_c_play = (ImageView)ly_view.findViewById(R.id.img_c_play);
		img_c_pause = (ImageView)ly_view.findViewById(R.id.img_c_pause);
		//progressBar = (ProgressBar)ly_view.findViewById(R.id.progreaaBar_id);
		
		//���ü���
		ly_content.setOnTouchListener(myOnTouchListenner);
		img_pgUp.setOnClickListener(l);
		seekBar.setOnSeekBarChangeListener(seekBar_l);
		img_c_play.setOnClickListener(l);
		img_c_pause.setOnClickListener(l);
	}	
	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate(); 
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
		Log.i("MyMediaController>>onLayout","into!");
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//���� �ӿؼ� mesaure() 
		int childCount = getChildCount();
		Log.i("MyMediaController>>onMeasure","childCount="+String.valueOf(childCount));
		for(int i=0;i<childCount;i++){   
			View children = getChildAt(i); 
			measureChild(children, widthMeasureSpec, heightMeasureSpec);
		}
		
		//�ж� ����ģʽ   ֧��wrap_content
		int mMaxWidth = 0;
		int mMaxHeight = 0;
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);	 //this���ֿؼ�  ���ģʽ 
		int heightMode = MeasureSpec.getMode(heightMeasureSpec); //this���ֿؼ�  �߶�ģʽ 
		widthSize = MeasureSpec.getSize(widthMeasureSpec);   //this���ֿؼ�  ���
		heightSize = MeasureSpec.getSize(heightMeasureSpec); //this���ֿؼ�  �߶�
		if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
			for(int i=0;i<childCount;i++){
				View children = getChildAt(i);
				int h = children.getMeasuredHeight();
				int w = children.getMeasuredWidth();
				if(h > mMaxHeight)  mMaxHeight = h;
				if(w > mMaxWidth)  mMaxWidth = w;
			}
			widthSize = mMaxWidth;
			heightSize = mMaxHeight;			 
		}else if(widthMode == MeasureSpec.AT_MOST){ 
			for(int i=0;i<childCount;i++){
				View children = getChildAt(i);
				int w = children.getMeasuredWidth();
				if(w > mMaxWidth)  mMaxWidth = w;
			}
			widthSize = mMaxWidth;
		}else if(heightMode == MeasureSpec.AT_MOST){
			for(int i=0;i<childCount;i++){
				View children = getChildAt(i);
				int h = children.getMeasuredHeight();
				if(h > mMaxHeight)  mMaxHeight = h;
			}
			heightSize = mMaxHeight;		
		}
		setMeasuredDimension(widthSize, heightSize);  //���� Ϊ��һ �ӿؼ������ �� ��	
	}
}
