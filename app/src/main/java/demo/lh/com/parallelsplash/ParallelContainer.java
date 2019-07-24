package demo.lh.com.parallelsplash;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 容器类 继承FrameLayout 实现viewpager的滚动方法
 */
public class ParallelContainer extends FrameLayout implements ViewPager.OnPageChangeListener {
    private ArrayList<ParallelFragment> fragments;
    private static final String TAG = ParallelContainer.class.getSimpleName();
    private ImageView iv_man;
    private ParallelPagerAdapter parallelPagerAdapter;

    public ParallelContainer(Context context) {
        super(context);
    }

    public ParallelContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParallelContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
        parallelPagerAdapter = new ParallelPagerAdapter(activity.getSupportFragmentManager(), fragments);
        //添加到布局
        viewPager.setAdapter(parallelPagerAdapter);
        viewPager.setOnPageChangeListener(this);
        addView(viewPager);
    }


    /**
     * 主要做动画操作
     * @param position 当前位置
     * @param positionOffset 偏移的位置
     * @param positionOffsetPixels 偏移的像素
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.i(TAG, "onPageScrolled: " + "position = " + position
                + "positionOffset = " +positionOffset
                + "positionOffsetPixels = " + positionOffsetPixels);
        int containerWidth = getWidth();

        ParallelFragment inFragment = null;
        try {
            inFragment = fragments.get(position+1);
        }catch (Exception e){
            e.printStackTrace();
        }

        ParallelFragment outFragment = null;
        try {
            outFragment = fragments.get(position);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (inFragment!=null){
            List<View> inViews = inFragment.getParallelView();
            if (inViews!=null){
                for (View view : inViews) {
                    ParallelViewTag tag = (ParallelViewTag) view.getTag(R.id.parallax_view_tag);
                    if (tag == null){
                        continue;
                    }
                    ViewHelper.setTranslationX(view, (containerWidth-positionOffsetPixels)*tag.xIn);
                    ViewHelper.setTranslationY(view, (containerWidth-positionOffsetPixels)*tag.yIn);
                }
            }
        }

        if (outFragment!=null){
            List<View> outViews = outFragment.getParallelView();
            if (outViews!=null){
                for (View view : outViews) {
                    ParallelViewTag tag = (ParallelViewTag) view.getTag(R.id.parallax_view_tag);
                    if (tag == null){
                        continue;
                    }
                    Log.i(TAG, "onPageScrolled: out" + tag.xOut);
                    ViewHelper.setTranslationX(view, 0-positionOffsetPixels*tag.xOut);
                    ViewHelper.setTranslationY(view, 0-positionOffsetPixels*tag.yOut);
                }
            }
        }
    }

    /**
     * 对主角进行显示操作
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        if (position == parallelPagerAdapter.getCount()-1){
            iv_man.setVisibility(INVISIBLE);
        }else {
            iv_man.setVisibility(VISIBLE);
        }

    }

    /**
     * 控制主角运动
     * @param state
     */
    @Override
    public void onPageScrollStateChanged(int state) {
        AnimationDrawable animationDrawable = (AnimationDrawable) iv_man.getBackground();
        switch (state){
            case ViewPager.SCROLL_STATE_IDLE:
                animationDrawable.stop();
                break;
            case ViewPager.SCROLL_STATE_DRAGGING:
                animationDrawable.start();
                break;
        }

    }

    public void setIv_man(ImageView img) {
        this.iv_man = img;
    }
}
