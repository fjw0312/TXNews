package TXNews.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/10/18.
 * 鍥剧墖鍔犺浇  澶勭悊绫�   璇ョ被Api 涓嶅缓璁繍琛屽湪 UI Thread
 * 濡傛灉 缃戠粶鍥剧墖 鍔犺浇寤鸿 浣跨敤Glide Picasso妗嗘灦
 */

public class GetFitBitmap {
    public GetFitBitmap() {
    }

    //澶勭悊鍥剧墖缂╂斁姣斾緥
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if ( ((float)height / (float)reqHeight) > ((float)width / (float)reqWidth)) {
                inSampleSize = Math.round((float)height / (float)reqHeight);
            } else {
                inSampleSize = Math.round((float)width / (float)reqWidth);
            }
        }
        return inSampleSize;
    }


    //鍔犺浇 璧勬簮鏂囦欢鍥剧墖
    public static Bitmap getResourcesImage(Resources res, int rsc_id,int reqWidth, int reqHeight){

        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        BitmapFactory.decodeResource(res, rsc_id, opt);
        opt.inSampleSize = calculateInSampleSize(opt,reqWidth,reqHeight);
        return BitmapFactory.decodeResource(res, rsc_id, opt);
    }
    //鍔犺浇 鏈湴鏂囦欢鍥剧墖
    public static Bitmap getFileImage(String strFile,int reqWidth, int reqHeight){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        BitmapFactory.decodeFile(strFile,opt);
        opt.inSampleSize = calculateInSampleSize(opt,reqWidth,reqHeight);
        return  BitmapFactory.decodeFile(strFile,opt);
    }
    //鍔犺浇 缃戠粶鍥剧墖
    public static Bitmap getNetImage(InputStream in, int reqWidth, int reqHeight){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inSampleSize = calculateInSampleSize(opt,reqWidth,reqHeight);
        BitmapFactory.decodeStream(in, null, opt);
        return null;
    }

    //鍔犺浇 缃戠粶鍥剧墖
    public static Bitmap getNetImage(String strUrl, int reqWidth, int reqHeight){
        InputStream in = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(strUrl);
            connection = (HttpURLConnection) url.openConnection();   //涓庢湇鍔＄杩炴帴   鎺ヤ笅鍙互鐩存帴鏀跺彂鏁版嵁浜�
            connection.setRequestMethod("GET"); //璁剧疆涓鸿姹傝幏鍙栨暟鎹�     鍙戦�佲�淕ET鈥�
            //connection.setRequestProperty("encoding", "uft-8");  鎸囧畾缂栫爜
            connection.setConnectTimeout(5000); //璁剧疆缃戠粶杩炴帴瓒呮椂   鍙互涓嶈缃�
            connection.setReadTimeout(5000);    //璁剧疆缃戠粶璇诲彇瓒呮椂   鍙互涓嶈缃�
            if (connection.getResponseCode() == 200) {  //鏁版嵁鎺ユ敹鎴愬姛
                in = connection.getInputStream(); //鑾峰彇缃戠粶瀛楄妭娴�
            }
        }catch (Exception e){
            Log.e("Error:","GetBitmapForImageView->getNetImage>>缃戠粶璇锋眰寮傚父鎶涘嚭锛�");
            return null;
        }

        // 澶勭悊 鍥剧墖鍘嬬缉
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inSampleSize = calculateInSampleSize(opt,reqWidth,reqHeight);
        return  BitmapFactory.decodeStream(in, null, opt);
    }

}
