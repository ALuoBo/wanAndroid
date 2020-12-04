package com.luobo.wanandroid.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.luobo.wanandroid.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    static String TAG = "HomeFragment";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        HomeViewModel viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        HomePageAdapter adapter = new HomePageAdapter(getContext(), new ArticleDiffUtil());
        RecyclerView recyclerView = view.findViewById(R.id.homeRecycler);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel.getData().observe(getViewLifecycleOwner(), articleDataBean -> {
            List<ArticleDataBean.DataBean.DatasBean> data = new ArrayList<>();
            data.addAll(articleDataBean.getData().getDatas());
            adapter.submitList(data);
        });

        RecyclerView toppingRecycler = view.findViewById(R.id.homeTopping);
        LinearLayoutManager toppingLinear = new LinearLayoutManager(getContext());
        toppingLinear.setOrientation(RecyclerView.HORIZONTAL);
        toppingRecycler.setLayoutManager(toppingLinear);
        ToppingAdapter toppingAdapter = new ToppingAdapter(new ToppingDiffUtil(), getContext());
        toppingRecycler.setAdapter(toppingAdapter);

        viewModel.getTopping().observe(getViewLifecycleOwner(), toppingBean -> {
            toppingAdapter.submitList(toppingBean.getData());
        });


        NestedScrollView scrollView = getActivity().findViewById(R.id.parentScroll);
        scrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                //底部加载
                viewModel.getData();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}