package TXNews.customView;

import TXNews.Main.R;
import TXNews.url.JHUrl;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by fjw0312 on 2017/10/10.
 * 碎片  自定义   懒加载 基础类
 * 特别： 碎片不怕被提前 初始化预加载   不耗时  不耗内存
 * 只需要  继承后，  重写  OnCreateView()  lazyLoad()  stopLoad();
 * 延时显示后延时操作 加载  放入lazyLoad(). 
 * 预加载就执行  OnCreateView() 若不需要任何控件初始化  可设备layout id后不用重写
 */

public class MyFragmentLazy extends Fragment{

    boolean isInit = false;    //初始化  碎片标志变量
    boolean isVisible = false;  //是否可见
    public int R_ly_id = R.layout.item_img;  //布局文件id   //当不需要初始化控制时，不用设置


  
    public MyFragmentLazy(){
        super();
    }

/*
     //外部 传入参数  ---可直接  调用NewInstance
     //        Bundle bundle = new Bundle();
     //       bundle.putInt("key", img_src_s[i]);
     //        myFragment.setArguments(bundle);
     //实例化  并传入参数    ---方便实例化  与传参数
    public static MyFragmentLazy newInstance(int arg){
        MyFragmentLazy fragment = new MyFragmentLazy();
        Bundle bundle = new Bundle();
        bundle.putInt("key", arg);
        fragment.setArguments(bundle);
        return fragment;
    }
    public static MyFragmentLazy newInstance(String argc){
        MyFragmentLazy fragment = new MyFragmentLazy();
        Bundle bundle = new Bundle();
        bundle.putString("key", argc);
        fragment.setArguments(bundle);
        return fragment;
    }
*/
    
    //判断  是否 加载数据
    protected void isCanLoadData(){
        if(!isInit) return;      //未初始化
        if(getUserVisibleHint()){  //碎片界面 可见
            isVisible = true;
            //处理碎片可见  加载数据
            lazyLaod();
        }else{
            isVisible = false;
            //处理碎片不可见  数据终止
            stopLoad();
        }
    }
    //加载数据
    protected void lazyLaod(){
       
       // int src_id = (int)getArguments().get("key");


    }
    //停止 加载数据
    protected void stopLoad(){

    }


    @Override //碎片可不可见 发生变化
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();  //已预加载 并碎片  可见
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R_ly_id, container, false);
        //初始化 控件     初始化赋值
        //imageView = (ImageView)view.findViewById(R.id.img_id);

        return view;
    }
    /*    
            Bundle bundle = new Bundle();
            bundle.putInt("key", img_src_s[i]);
            myFragment.setArguments(bundle);

     */
    @Override  
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isInit = true;
        //预加载 并可见  加载数据
        isCanLoadData();
    }

    @Override  //销毁 碎片
    public void onDestroyView() {
        super.onDestroyView();
        isInit = false;
        isVisible = false;
    }

}
