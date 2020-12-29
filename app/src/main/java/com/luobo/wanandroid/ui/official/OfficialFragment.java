package com.luobo.wanandroid.ui.official;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.luobo.wanandroid.R;
import com.luobo.wanandroid.base.BaseFragment;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class OfficialFragment extends BaseFragment {
    OfficialViewModel viewModel;
    int officialContentFragmentSize;
    int[] authorIds = new int[30];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_official, container, false);
        viewModel = new ViewModelProvider(this).get(OfficialViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = view.findViewById(R.id.officialTable);
        ViewPager2 viewPager = view.findViewById(R.id.officialViewPager);
        OfficialContentFragmentAdapter adapter = new OfficialContentFragmentAdapter(this);
        viewPager.setAdapter(adapter);
        ArrayList<String> titleList = new ArrayList<>();

        viewModel.getTabs().observe(getViewLifecycleOwner(), projectTreeBean -> {

            for (OfficialTreeBean.DataBean dataBean : projectTreeBean.getData()) {

                titleList.add(dataBean.getName());
                authorIds[officialContentFragmentSize] = dataBean.getId();
                officialContentFragmentSize++;
            }
            adapter.notifyItemRangeInserted(0, officialContentFragmentSize);

        });

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(titleList.get(position));
        }).attach();

    }

    class OfficialContentFragmentAdapter extends FragmentStateAdapter {
        public OfficialContentFragmentAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            //添加内容fragment
            return new OfficialContentFragment(authorIds[position]);
        }

        @Override
        public int getItemCount() {
            return officialContentFragmentSize;
        }
    }
}