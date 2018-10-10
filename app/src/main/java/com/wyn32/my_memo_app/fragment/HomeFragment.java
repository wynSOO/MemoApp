package com.wyn32.my_memo_app.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wyn32.my_memo_app.R;
import com.wyn32.my_memo_app.adapter.MemoViewPagerAdapter;
import com.wyn32.my_memo_app.database.MemoVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class HomeFragment extends Fragment {

    List<String> pageTitles;
    TabLayout homeTabLayout;
    ViewPager homeViewPager;


    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.home_fragment,container,false);

        pageTitles = new ArrayList<>();
        homeTabLayout = view.findViewById(R.id.home_tab_layout);
        homeViewPager = view.findViewById(R.id.home_view_pager);
        homeTabLayout.setupWithViewPager(homeViewPager);

        FirebaseDatabase.getInstance().getReference("my_memo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                pageTitles.clear();


                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    MemoVO vo = ds.getValue(MemoVO.class);
                    pageTitles.add(vo.getMemoCat());
                    Log.d("VO",":"+vo.toString());

                }

                // 중복코드 제거
                pageTitles = new ArrayList<>(new TreeSet<>(pageTitles));
//                Log.d("pageTitles",":"+pageTitles.size());

                homeViewPager.setAdapter(new MemoViewPagerAdapter(getFragmentManager(),pageTitles));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("F-ERROR",databaseError.toString());
            }
        });

        Log.d("pageTitles",":"+pageTitles.size());



//        homeViewPager.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {
//
//            @Nullable
//            @Override
//            public CharSequence getPageTitle(int position) {
////                return super.getPageTitle(position);
//                return pageTitles.get(position);
//            }
//
//            @Override
//            public Fragment getItem(int position) {
//                return new MemoViewFragment();
//            }
//
//            @Override
//            public int getCount() {
//                return pageTitles.size();
//            }
//        });

        return view;
    }
}
