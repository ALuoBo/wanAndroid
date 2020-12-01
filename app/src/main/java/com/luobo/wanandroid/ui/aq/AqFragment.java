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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.luobo.wanandroid.R;
import com.luobo.wanandroid.WebActivity;

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

        viewModel.getAq(1).observe(getViewLifecycleOwner(), aqResponse -> {
            adapter.submitList(aqResponse.getData().getDatas());

        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                Log.e("will", "onScrolled: xxxxxxxx");

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

        protected AqAdapter(@NonNull DiffUtil.ItemCallback<AqResponse.DataBean.DatasBean> diffCallback) {
            super(diffCallback);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getContext()).inflate(R.layout.aq_item_layout, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

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

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = view.findViewById(R.id.aqTitle);
            }
        }
    }

}