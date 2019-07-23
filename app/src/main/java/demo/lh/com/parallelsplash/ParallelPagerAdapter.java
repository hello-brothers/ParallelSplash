package demo.lh.com.parallelsplash;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

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
