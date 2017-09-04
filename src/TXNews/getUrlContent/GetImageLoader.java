package TXNews.getUrlContent;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import android.widget.ImageView;

/**
 * ͨ��Volley ImageLoader ��������ͼƬ  ---��Ҫ�����Զ��� ͼƬ������
 * author:fjw0312@163.com
 * date:2017.7.29
 * 
 * notice: 2017.8.18  
 *  ���ڶ��߳�OOM 
 *   1.�߳����й����ڴ�����(eg:���ع���ͼƬ����ȡ���չ����ļ�����������ͨ�����ݶ�ȡ�г�����)
 *   2.�߳���Ƶ�������Ƶش������ڴ��̣߳����ڽ���Ҳ��һ���ڴ��С���ơ�����BaseAdapter.getView�о�����Ҫnew�ڴ档
 * �޷��ѿصĶ��߳� ���ϴ����߳�  ���̶߳���ڴ� �ᵼ��OOM (outOfMemoryError�ڴ����)
 * 
 * ����Volley ����RequestQueue = Volley.newRequestQueue�����Ķ����ڴ�ᱣ��activity��context���û�һֱռ���ڴ�
 * ���ԣ��߶Ƚ���һ��app���� ֻ����һ��ȫ�ֵ� RequestQueue ����ʹ�õ���instance����.
 * ����ͼƬʱ ���鲻Ҫÿ��ͼƬ��new ImageLoader��ֻ��ʵ����һ�ξͿ��ԡ�
 * */
public class GetImageLoader {

	public GetImageLoader() {
		// TODO Auto-generated constructor stub
	}

	
	//������ urlͼƬ�����ַ  ImageViewͼƬ�ؼ�   defaultImageResIdͼƬռλͼ  errorImageResIdͼƬ���ش���ͼ    maxWidth����ͼ�����     maxHeight����ͼ���߶�
	public static void getImageLoader(String url,ImageLoader myImageLoader, ImageView imageView,int defaultImageResId, int errorImageResId,int maxWidth,int maxHeight){
		
		//1.���� RequestQueue
		//RequestQueue mQueue = Volley.newRequestQueue(MyApplication.getContextObject());		
		//2.���� ImageLoader
		//ImageLoader imageLoader = new ImageLoader(mQueue, imageCache);
		//ImageLoader imageLoader = myImageLoader;     //ʹ����ǰ ����� ͼƬ������
		//3.���� ImageListener
		ImageListener listener = ImageLoader.getImageListener(imageView, defaultImageResId, errorImageResId);
		//4.���� ͼƬ���ؼ�
		myImageLoader.get(url, listener, maxWidth, maxHeight);  //Ҳ���� ���������ߴ�
	}

}
