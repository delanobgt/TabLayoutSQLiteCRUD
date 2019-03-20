package com.example.tablayoutsqlitecrud.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.tablayoutsqlitecrud.Store;
import com.example.tablayoutsqlitecrud.fragments.StudentCreateFragment;
import com.example.tablayoutsqlitecrud.fragments.StudentDetailFragment;
import com.example.tablayoutsqlitecrud.fragments.StudentEditFragment;
import com.example.tablayoutsqlitecrud.fragments.StudentListFragment;

/**
 * Fragment to return the clicked tab.
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "TabPagerAdapter";
    private static final int TAB_COUNT = 3;

    private StudentListFragment studentListFragment;
    private StudentDetailFragment studentDetailFragment;
    private StudentCreateFragment studentCreateFragment;
    private StudentEditFragment studentEditFragment;

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
        studentListFragment = new StudentListFragment();
        studentDetailFragment = new StudentDetailFragment();
        studentCreateFragment = new StudentCreateFragment();
        studentEditFragment = new StudentEditFragment();
        Store.addSubscription(TAG, new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return studentListFragment;
        else if (position == 1) return studentDetailFragment;
        else if (position == 2 && Store.editSelectedStudentIndex != null) return studentEditFragment;
        else if (position == 2) return studentCreateFragment;
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position){
        if (position == 0) return "View All";
        else if (position == 1) return "Detail";
        else if (position == 2 && Store.editSelectedStudentIndex != null) return "Edit";
        else if (position == 2) return "Create";
        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }
}
