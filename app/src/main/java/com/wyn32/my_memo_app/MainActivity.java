package com.wyn32.my_memo_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.wyn32.my_memo_app.fragment.HomeFragment;
import com.wyn32.my_memo_app.fragment.MemoFragment;
import com.wyn32.my_memo_app.fragment.MyPageFragment;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.main_navigation_home:
                    viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                        @Override
                        public Fragment getItem(int position) {
                            return new HomeFragment();
                        }

                        @Override
                        public int getCount() {
                            return 1;
                        }
                    });
                    return true;
                case R.id.main_navigation_memo:
                    viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                        @Override
                        public Fragment getItem(int position) {
                            return new MemoFragment();
                        }

                        @Override
                        public int getCount() {
                            return 1;
                        }
                    });
                    return true;
                case R.id.main_navigation_mypage:
                    viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                        @Override
                        public Fragment getItem(int position) {
                            return new MyPageFragment();
                        }

                        @Override
                        public int getCount() {
                            return 1;
                        }
                    });
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.main_view_pager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new HomeFragment();
            }

            @Override
            public int getCount() {
                return 1;
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.main_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
