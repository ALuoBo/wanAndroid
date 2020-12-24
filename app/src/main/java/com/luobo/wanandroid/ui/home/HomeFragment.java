package com.luobo.wanandroid.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.luobo.wanandroid.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    static String TAG = "HomeFragment";
    HomeViewModel viewModel;
    HomePageAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: " + this);

        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onViewCreated: " + this);
        RecyclerView recyclerView = view.findViewById(R.id.homeRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new HomePageAdapter(getContext(), getLifecycle(), new HomeDiffUtil());
        viewModel.getHomeData().observe(getViewLifecycleOwner(), homeBeans -> {
            Log.e(TAG, "onCreate: homeBeans change");
            List<HomeBean> data = new ArrayList<>();
            data.addAll(homeBeans);
            //adapter.submitList(List)方法中需要提供一个列表，这个List必须是一个新的
            //列表,如果你使用的是一个已经加载了的列表，那么将不会被加载。
            adapter.submitList(data);
        });
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Banner
        viewModel.getBanner().observe(getViewLifecycleOwner(), homeBannerBean -> {
            HomePageAdapter.BannerViewHolder viewHolder =
                    (HomePageAdapter.BannerViewHolder) recyclerView.getChildViewHolder(recyclerView.getChildAt(0));
            viewHolder.mBannerViewPager.refreshData(homeBannerBean.getData());
            adapter.notifyDataSetChanged();
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy < 0) return;
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager.findLastCompletelyVisibleItemPosition() == adapter.getItemCount() - 1) {
                    //   viewModel.getData();
                }
            }
        });


       /* NestedScrollView scrollView = view.findViewById(R.id.homeScrollView);
        scrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            //判断是否滑到的底部
            if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                Log.e(TAG, "onViewCreated: loadMore" );
                viewModel.getData();
            }
        });*/

    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume " + adapter.getCurrentList().size());
        super.onResume();
        Log.e(TAG, "onResume: " + this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView: " + this);
    }

    @Override
    public void onDestroy() {

        Log.e(TAG, "onDestroy: " + this);
        super.onDestroy();
    }
}