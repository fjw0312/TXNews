package TXNews.Main;

import java.util.ArrayList;
import java.util.List;

import TXNews.customView.MyNavigationBarX;
import TXNews.customView.MyViewPager;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;



public class TestActivity extends FragmentActivity{

	    List<Fragment> fragments;
	    MyViewPager myViewPager;
	    MyNavigationBarX navigationBar;

	    private List<Fragment> newfragments(){
	        fragments = new ArrayList<Fragment>();
	        fragments.add(new MainFragment());
	        fragments.add(new JxFragment());
	        fragments.add(new SpFragment());
	        fragments.add(new UserFragment());
	        return fragments;
	    }

	    @Override
	    protected void onCreate(@Nullable Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
			ActionBar actionBar = getActionBar();   
			actionBar.hide();
	        setContentView(R.layout.activity_tab);
	        //FullScreenUI.FullScreenUI(this); 


	        newfragments();
	        myViewPager = (MyViewPager) findViewById(R.id.MyViewPager); //��ȡ�ؼ�
	        myViewPager.init_adapter(getSupportFragmentManager(),fragments);  //��ʼ��������  ��ʼ������
	        navigationBar = (MyNavigationBarX)findViewById(R.id.MyNavigationBar_id);
	        navigationBar.setViewPager(myViewPager); //���� myViewPager ʵ���л�ҳ�� 
	        navigationBar.onChange(0,false); //��ʼ��ͼ��

	        myViewPager.setOffscreenPageLimit(fragments.size()-1); //���� �������  ��֤��ת������
	     //   myViewPager.setOffscreenPageLimit(1); //���� �������>0	        
	        //myViewPager.addOnPageChangeListener(listener)
	        myViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
	            @Override
	            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	            }

	            @Override //ҳ�� �л�����  positionҳ��id
	            public void onPageSelected(int position) {
	                navigationBar.onChange(position,false);

	            }

	            @Override //state 0->1->2->0  1���ڻ��� 2�������  0������
	            public void onPageScrollStateChanged(int state) { 

	            }
	        });
	    }

	    @Override
	    protected void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	    }

}
