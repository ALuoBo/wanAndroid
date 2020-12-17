package com.luobo.wanandroid.ui.other;

import android.os.Bundle;
import android.text.Html;
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
import com.luobo.wanandroid.ui.project.ProjectFragment;
import com.luobo.wanandroid.ui.user.PersonCenterFragment;

import static android.text.Html.FROM_HTML_MODE_LEGACY;


public class OtherFragment extends Fragment {
    OtherViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(OtherViewModel.class);
        TabLayout tabLayout = view.findViewById(R.id.otherTabLayout);
        //Tab
        ViewPager2 viewPager = view.findViewById(R.id.otherViewPager);
        viewPager.setAdapter(new OtherFragmentAdapter(this));
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(Html.fromHtml(viewModel.getTabText().getValue()[position]));
        }).attach();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_other, container, false);
    }

    class OtherFragmentAdapter extends FragmentStateAdapter {


        public OtherFragmentAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 3:
                    return ProjectFragment.newInstance();
            }
            return new PersonCenterFragment();
        }

        @Override
        public int getItemCount() {
            return viewModel.getTabText().getValue().length;
        }
    }
}