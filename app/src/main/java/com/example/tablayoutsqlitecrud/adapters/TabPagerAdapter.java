package com.example.tablayoutsqlitecrud.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tablayoutsqlitecrud.Store;
import com.example.tablayoutsqlitecrud.fragments.StudentDetailFragment;
import com.example.tablayoutsqlitecrud.fragments.StudentListFragment;
import com.example.tablayoutsqlitecrud.fragments.StudentModifyFragment;

/**
 * Fragment to return the clicked tab.
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "TabPagerAdapter";
    private static final int TAB_COUNT = 3;

    private StudentListFragment studentListFragment;
    private StudentDetailFragment studentDetailFragment;
    private StudentModifyFragment studentModifyFragment;
    public String tab3Title = "Create";

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
        studentListFragment = new StudentListFragment();
        studentDetailFragment = new StudentDetailFragment();
        studentModifyFragment = new StudentModifyFragment();
        Store.getStore().detailFragment = studentDetailFragment;
        Store.getStore().modifyFragment = studentModifyFragment;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return studentListFragment;
        else if (position == 1) return studentDetailFragment;
        else if (position == 2) return studentModifyFragment;
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position){
        if (position == 0) return "View All";
        else if (position == 1) return "Detail";
        else if (position == 2) return tab3Title;
        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }
}
