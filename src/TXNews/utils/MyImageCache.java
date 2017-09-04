package TXNews.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;


/***
 * 自定义 个人图片缓存类
 * author:fjw0312@163.com
 * date:2017.7.29
 * 
 * */
public class MyImageCache implements ImageCache{
	//LruCache图片缓存处理类
	private LruCache<String,Bitmap> myCache;
	int maxMemory = (int) (Runtime.getRuntime().maxMemory() ); // 获取到可用内存的最大值，使用内存超出这个值会引起OutOfMemory异常。单位：K
    int cacheSize = maxMemory / 8;// 使用最大可用内存值的1/8作为缓存的大小。 
	
	//构造
	public MyImageCache() {
		// TODO Auto-generated constructor stub
		myCache = new LruCache<String, Bitmap>(cacheSize){
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				// TODO Auto-generated method stub   // 重写此方法来衡量每张图片的大小，默认返回图片数量。
				//return bitmap.getByteCount()/1024;
				return bitmap.getRowBytes()*bitmap.getHeight();
			}			
		};
	}
	
	//创建 单例模式
	public static MyImageCache bitmapCache; //表示该软件程序 内 如果有使用到 该缓存 就使用该静态 缓存图片列表
	public static synchronized MyImageCache instance(){
		if(bitmapCache==null){
			bitmapCache = new MyImageCache();
		}
		return bitmapCache;
	}

	@Override
	public Bitmap getBitmap(String url) {
		// TODO Auto-generated method stub
		return myCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		// TODO Auto-generated method stub
		myCache.put(url, bitmap);
	}

}
