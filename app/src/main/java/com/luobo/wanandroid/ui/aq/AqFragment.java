package com.luobo.wanandroid.ui.aq;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.luobo.wanandroid.R;
import com.luobo.wanandroid.WebActivity;

import java.util.ArrayList;
import java.util.List;

public class AqFragment extends Fragment {
    RecyclerView recyclerView;
    AqViewModel viewModel;
    AqAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.aqRecyclerView);
        recyclerView.setAdapter(adapter = new AqAdapter(new AqDiffUtil()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getAq().observe(getViewLifecycleOwner(), aqResponse -> {
            Log.e("will", "onResponse: 6");
            List<AqResponse.DataBean.DatasBean> data = new ArrayList<>();
            data.addAll(aqResponse.getData().getDatas());
            adapter.submitList(data);

        });
        NestedScrollView scrollView = getActivity().findViewById(R.id.parentScroll);
        scrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                //底部加载
                viewModel.getAq();
                Log.e("will", "onResponse: 5");

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(AqViewModel.class);
        return inflater.inflate(R.layout.fragment_aq, container, false);
    }


    class AqAdapter extends ListAdapter<AqResponse.DataBean.DatasBean, AqAdapter.MyViewHolder> {
        //private static final int HEADER_VIEW_TYPE = -1;
        private static final int NORMAL_VIEW_TYPE = 0;
        private static final int FOOTER_VIEW_TYPE = 1;

        protected AqAdapter(@NonNull DiffUtil.ItemCallback<AqResponse.DataBean.DatasBean> diffCallback) {
            super(diffCallback);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView;
            switch (viewType) {
                case FOOTER_VIEW_TYPE:
                    itemView = LayoutInflater.from(getContext()).inflate(R.layout.footer_layout, parent, false);
                    break;
                default:
                    itemView = LayoutInflater.from(getContext()).inflate(R.layout.aq_item_layout, parent, false);
                    break;
            }
            return new MyViewHolder(itemView);

        }

        @Override
        public int getItemCount() {
            return super.getItemCount() + 1;
        }

        @Override
        public int getItemViewType(int position) {
           /* if (position == 0) {
               return HEADER_VIEW_TYPE;
            } else if (position == getItemCount() - 1) {
                return FOOTER_VIEW_TYPE;
            } else return NORMAL_VIEW_TYPE;*/

            if (position == getItemCount() - 1) return FOOTER_VIEW_TYPE;
            else return NORMAL_VIEW_TYPE;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            if (position == getItemCount() - 1) {
                return;
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    holder.tv.setText(Html.fromHtml(getItem(position).getTitle(), Html.FROM_HTML_MODE_COMPACT));

                } else {
                    holder.tv.setText(Html.fromHtml(getItem(position).getTitle()));

                }
                holder.tv.setOnClickListener(v -> {
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra("URL", getItem(position).getLink());
                    startActivity(intent);
                });
            }
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = view.findViewById(R.id.aqTitle);
            }
        }
    }

}