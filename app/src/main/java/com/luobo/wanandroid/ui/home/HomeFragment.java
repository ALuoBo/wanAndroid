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

public class HomeFragment extends Fragment {
    static String TAG = "HomeFragment";
    HomeViewModel viewModel;
    HomePageAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        adapter = new HomePageAdapter(getContext(), getLifecycle(), new HomeDiffUtil());

        viewModel.getHomeData().observe(getActivity(), homeBeans -> {
            adapter.submitList(homeBeans);
        });
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
                    viewModel.getData();
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