package TXNews.getUrlContent;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import android.widget.ImageView;

/**
 * 通过Volley ImageLoader 下载网络图片  ---需要调用自定义 图片缓存类
 * author:fjw0312@163.com
 * date:2017.7.29
 * 
 * notice: 2017.8.18  
 *  关于多线程OOM 
 *   1.线程中有过大内存数据(eg:加载过大图片、读取接收过大文件、网络数据通信数据读取中常出现)
 *   2.线程中频繁不节制地创建耗内存线程，由于进行也有一定内存大小限制。比如BaseAdapter.getView中尽量不要new内存。
 * 无法把控的多线程 不断创建线程  且线程多耗内存 会导致OOM (outOfMemoryError内存溢出)
 * 
 * 关于Volley 由于RequestQueue = Volley.newRequestQueue创建的队列内存会保存activity的context引用会一直占用内存
 * 所以，高度建议一个app程序 只定义一个全局的 RequestQueue 可以使用单例instance定义.
 * 加载图片时 建议不要每张图片都new ImageLoader，只需实例化一次就可以。
 * */
public class GetImageLoader {

	public GetImageLoader() {
		// TODO Auto-generated constructor stub
	}

	
	//参数： url图片请求地址  ImageView图片控件   defaultImageResId图片占位图  errorImageResId图片加载错误图    maxWidth请求图最大宽度     maxHeight请求图最大高度
	public static void getImageLoader(String url,ImageLoader myImageLoader, ImageView imageView,int defaultImageResId, int errorImageResId,int maxWidth,int maxHeight){
		
		//1.创建 RequestQueue
		//RequestQueue mQueue = Volley.newRequestQueue(MyApplication.getContextObject());		
		//2.创建 ImageLoader
		//ImageLoader imageLoader = new ImageLoader(mQueue, imageCache);
		//ImageLoader imageLoader = myImageLoader;     //使用提前 定义的 图片接收器
		//3.创建 ImageListener
		ImageListener listener = ImageLoader.getImageListener(imageView, defaultImageResId, errorImageResId);
		//4.加载 图片到控件
		myImageLoader.get(url, listener, maxWidth, maxHeight);  //也可以 不限制最大尺寸
	}

}
