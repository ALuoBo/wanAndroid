package com.luobo.wanandroid.ui.other;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.luobo.wanandroid.R;
import com.luobo.wanandroid.ui.home.HomeViewModel;


public class OtherFragment extends Fragment {
    OtherViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = view.findViewById(R.id.otherTabLayout);
        //Tab
        for (String tab : viewModel.getTabText().getValue()
        ) {
            tabLayout.addTab(tabLayout.newTab().setText(tab));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(OtherViewModel.class);
        return inflater.inflate(R.layout.fragment_other, container, false);
    }
}