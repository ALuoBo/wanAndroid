package com.luobo.wanandroid.ui.official;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.luobo.wanandroid.R;
import com.luobo.wanandroid.base.BaseFragment;

/**
 * A fragment representing a list of Items.
 */
public class OfficialFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_official, container, false);

        return view;
    }
}