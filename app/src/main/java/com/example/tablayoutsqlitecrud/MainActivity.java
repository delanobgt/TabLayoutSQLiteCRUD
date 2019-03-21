package com.example.tablayoutsqlitecrud;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.tablayoutsqlitecrud.adapters.TabPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Store.getStore().init(getApplicationContext());

        ViewPager viewPager = findViewById(R.id.view_pager);
        final TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        final TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) { }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    Store.getStore().detailFragment.clearStudent();
                }
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
        tabLayout.getTabAt(0).select();

        Store.getStore().tabLayout = tabLayout;
        Store.getStore().tabPagerAdapter = adapter;
    }
}
