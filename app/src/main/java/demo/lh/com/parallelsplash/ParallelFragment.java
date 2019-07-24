package demo.lh.com.parallelsplash;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 因为viewpager中加载的就是fragment
 *
 * 页面管理类 管理当前页面所有的view
 */
public class ParallelFragment extends Fragment {
    /**
     * fragment中所有需要实现视差动画的view
     */
    private List<View> parallelView = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Bundle args = getArguments();
        int layoutId = args.getInt("layoutId");

        /** 自定义layoutinflator
         *  监布局视加载
         *  管理自定义属性并实现
         *
         * 因为需要监听的是fragment不是布局填充器 因此需要自定义layoutinflator来实现
         */
        ParallelLayoutInflater layoutInflater = new ParallelLayoutInflater(inflater, getActivity(), this);

        //将xml文件转换为view对象返回
        return layoutInflater.inflate(layoutId, null);


    }

    public List<View> getParallelView() {
        return parallelView;
    }
}
