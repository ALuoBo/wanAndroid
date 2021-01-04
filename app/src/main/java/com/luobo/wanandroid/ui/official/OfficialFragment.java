package com.luobo.wanandroid.ui.official;

import android.os.Bundle;
import android.util.Log;
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
    private String TAG = this.getClass().getName();
    OfficialViewModel viewModel;
    int officialContentFragmentSize;
    int[] authorIds = new int[30];

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
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

            // Issue : invalid view holder adapter positionFragmentViewHolder
            //Fix:https://stackoverflow.com/questions/31759171/recyclerview-and-java-lang-indexoutofboundsexception-inconsistency-detected-in
            //This is an example for refreshing data with completely new content. You can easily modify it to fit your needs. I solved this in my case by calling:
            //notifyItemRangeRemoved(0, previousContentSize);
            //before:
            //notifyItemRangeInserted(0, newContentSize);
            //This is the correct solution and is also mentioned in this post by an AOSP project member.
            adapter.notifyItemRangeRemoved(0, officialContentFragmentSize);

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
            Log.d(TAG, "getItemCount: " + officialContentFragmentSize);
            return officialContentFragmentSize;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}