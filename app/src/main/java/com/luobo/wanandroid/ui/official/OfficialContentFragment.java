package com.luobo.wanandroid.ui.official;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.luobo.wanandroid.R;
import com.luobo.wanandroid.base.BaseFragment;

public class OfficialContentFragment extends BaseFragment {
    public static OfficialContentFragment newInstance() {
        return new OfficialContentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_official_content, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.OfficialRecycler);
        OfficialAdapter adapter = new OfficialAdapter(getContext(), new OfficialDiffUtil());
        recyclerView.setAdapter(adapter);

    }
}
