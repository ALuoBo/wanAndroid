package com.luobo.wanandroid.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.luobo.wanandroid.R;
import com.luobo.wanandroid.ui.aq.AqFragment;

public class HomeContainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_contain, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager2 viewPager = view.findViewById(R.id.homeContainViewpager);
        viewPager.setAdapter(new HomeFragmentAdapter(this));
        TabLayout tabLayout = view.findViewById(R.id.homeTabLayout);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 1) {
                tab.setText("问答");
            } else {
                tab.setText("首页");
            }
        }).attach();

    }

    class HomeFragmentAdapter extends FragmentStateAdapter {
        public HomeFragmentAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            //添加项目内容fragment
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new AqFragment();
                default:
                    return new HomeFragment();
            }

        }

        @Override
        public int getItemCount() {

            return 2;
        }
    }
}