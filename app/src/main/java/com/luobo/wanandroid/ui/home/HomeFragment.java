package com.luobo.wanandroid.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.luobo.wanandroid.R;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        HomeViewModel viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        HomePageAdapter adapter = new HomePageAdapter(getContext(), new ArticleDiffUtil());

        viewModel.getData(0).observe(getViewLifecycleOwner(), articleDataBean -> adapter.submitList(articleDataBean.getData().getDatas()));
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.homeRecycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ImageView imageView = view.findViewById(R.id.imageView);

        viewModel.getBanner().observe(getViewLifecycleOwner(), homeBannerBean -> {
            Log.e("Will", homeBannerBean.getData().get(1).getImagePath());
            Glide.with(getActivity())
                    .load(homeBannerBean.getData().get(1).getImagePath())
                    .centerCrop()
                    .transition(withCrossFade())
                    .into(imageView);
        });
        return view;
    }
}