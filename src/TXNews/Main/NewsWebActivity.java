package TXNews.Main;

import mybroadcast.MyBroadcastReceiver;
import TXNews.bean.HistoryModel;
import TXNews.bean.RecordModel;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("SetJavaScriptEnabled")
public class NewsWebActivity extends Activity{

	ImageView img_back;
	TextView Tx_title;
	TextView Tx_share;  //未使用
	TextView Tx_collect;
	WebView webView;
	String str_html = "";
	String str_title = "";
	
	private OnClickListener l = new OnClickListener() {		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==img_back){
				NewsWebActivity.this.finish();
			}else if(arg0==Tx_share){    //分享到第三方 工具 目前效果不好
				
				Intent intent = new Intent(Intent.ACTION_SEND);		
				intent.setType("text/plain") ;// 设置类型
				intent.putExtra(Intent.EXTRA_TITLE, "NB XXXX");	        //设置标题
				intent.putExtra(Intent.EXTRA_SUBJECT, "fangjiongwen-TEST");// 设置主题
				intent.putExtra(Intent.EXTRA_TEXT, str_html);	        // 设置内容			
				startActivity(Intent.createChooser(intent,"分享工具"));	//应用选择器
						
			}else if(arg0==Tx_collect){  //收藏
				RecordModel recordModel = new RecordModel(str_html,str_title);
				HistoryModel.collectRecord_Map_lst.add(recordModel);
				MyBroadcastReceiver.sendBroad_MSG_HAL("文章收藏成功");
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//隐藏 标题栏
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		setContentView(R.layout.activity_newshtml); 
		
		Intent intent = getIntent();
		str_html = intent.getStringExtra("str_html");
		str_title = intent.getStringExtra("str_title");
		RecordModel recordModel = new RecordModel(str_html,str_title);
		HistoryModel.historyRecord_Map_lst.add(recordModel);
		
		img_back = (ImageView)findViewById(R.id.img_back);
		Tx_title = (TextView)findViewById(R.id.Tx_title);
		Tx_collect = (TextView)findViewById(R.id.Tx_collect);
		webView = (WebView)findViewById(R.id.web_id);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				// TODO Auto-generated method stub
				//return super.shouldOverrideUrlLoading(view, url);
				return true;
			}			
		});
		
		
		img_back.setOnClickListener(l);
		Tx_collect.setOnClickListener(l);
		//打开web
		webView.loadUrl(str_html);	
	}

}
