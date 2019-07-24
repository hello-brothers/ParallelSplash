package demo.lh.com.parallelsplash;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * FragmentPagerAdapter创建的fragment永远不会被销毁
 * 因此：
 *      当数据量大的时候可以使用FragmentStatePagerAdapter
 *      当用户界面只是少量固定的fragment时候，使用FragmentPagerAdapter
 */
public class ParallelPagerAdapter extends FragmentPagerAdapter {
    //装载fragment
    private List<ParallelFragment> fragments = new ArrayList<>();
    public ParallelPagerAdapter(FragmentManager fm, List<ParallelFragment> fragmentList) {
        super(fm);
        this.fragments = fragmentList;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
