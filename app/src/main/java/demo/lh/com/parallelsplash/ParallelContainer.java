package demo.lh.com.parallelsplash;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;

/**
 * 容器类 继承FrameLayout 实现viewpager的滚动方法
 */
public class ParallelContainer extends FrameLayout implements ViewPager.OnPageChangeListener {
    private ArrayList<ParallelFragment> fragments;

    public ParallelContainer(Context context) {
        super(context);
    }

    public ParallelContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParallelContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    public void setUp(int... childsId){
        //fragment数组
        fragments = new ArrayList<>();
        for (int i = 0; i < childsId.length; i++) {
            ParallelFragment fragment = new ParallelFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("layoutId", childsId[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);

        }

        //动态创建一个viewpager
        ViewPager viewPager = new ViewPager(getContext());
        //设置viewpager的id
        viewPager.setId(R.id.parallax_pager);
        //给viewpager设置布局
        viewPager.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //为viewpager设置adapter
        MainActivity activity = (MainActivity) getContext();
        ParallelPagerAdapter parallelPagerAdapter = new ParallelPagerAdapter(activity.getSupportFragmentManager(), fragments);
        //添加到布局
        viewPager.setAdapter(parallelPagerAdapter);
        addView(viewPager);
    }
}
