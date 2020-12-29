package com.luobo.wanandroid.ui.official;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.luobo.wanandroid.R;
import com.luobo.wanandroid.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class OfficialContentFragment extends BaseFragment {
    private OfficialContentViewModel viewModel;
    private String TAG = this.getClass().getName();
    private int authorId;
    OfficialAdapter adapter;
    public OfficialContentFragment(int authorId) {
        this.authorId = authorId;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_official_content, container, false);
        viewModel = new ViewModelProvider(this).get(OfficialContentViewModel.class);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.OfficialRecycler);
         adapter = new OfficialAdapter(getContext(), new OfficialDiffUtil());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        Log.e(TAG, "onViewCreated: "+ authorId );
        viewModel.getOfficialArticle(authorId).observe(getViewLifecycleOwner(), officialArticleBean -> {
            List<OfficialArticleBean.DataBean.DatasBean> data = new ArrayList<>(officialArticleBean.getData().getDatas());
            adapter.submitList(data);
        });
    }

    @Override
    public void onResume() {

        Log.e(TAG, "onResume: " );
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        Log.e(TAG, "onDestroyView: " );
        super.onDestroyView();
    }
}
