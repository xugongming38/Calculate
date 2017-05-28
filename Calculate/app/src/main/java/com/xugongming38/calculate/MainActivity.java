package com.xugongming38.calculate;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTablayout;
    private ViewPager mViewPager;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initEvents();
    }

    private void initEvents() {

        mTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == mTablayout.getTabAt(0)) {
                    one.setIcon(getResources().getDrawable(R.drawable.online2));
                    mViewPager.setCurrentItem(0);
                } else if (tab == mTablayout.getTabAt(1)) {
                    two.setIcon(getResources().getDrawable(R.drawable.my2));
                    mViewPager.setCurrentItem(1);
                } else if (tab == mTablayout.getTabAt(2)) {
                    three.setIcon(getResources().getDrawable(R.drawable.about2));
                    mViewPager.setCurrentItem(2);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab == mTablayout.getTabAt(0)) {
                    one.setIcon(getResources().getDrawable(R.drawable.online1));
                } else if (tab == mTablayout.getTabAt(1)) {
                    two.setIcon(getResources().getDrawable(R.drawable.my1));
                } else if (tab == mTablayout.getTabAt(2)) {
                    three.setIcon(getResources().getDrawable(R.drawable.about1));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initViews() {

        mTablayout= (TabLayout) findViewById(R.id.tabLayout);
        mViewPager= (ViewPager) findViewById(R.id.viewPager);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String[] mTitles = new String[]{"在线", "我的", "关于"};

            @Override
            public Fragment getItem(int position) {
                if (position == 1) {
                    return new MyFragment();
                } else if (position == 2) {
                    return new AboutFragment();
                }

                return new WebFragment();
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }

        });
        mViewPager.setCurrentItem(1);
        mTablayout.setupWithViewPager(mViewPager);

        one = mTablayout.getTabAt(0);
        two = mTablayout.getTabAt(1);
        three = mTablayout.getTabAt(2);

        one.setIcon(getResources().getDrawable(R.drawable.online1));
        two.setIcon(getResources().getDrawable(R.drawable.my1));
        three.setIcon(getResources().getDrawable(R.drawable.about1));

    }
}
