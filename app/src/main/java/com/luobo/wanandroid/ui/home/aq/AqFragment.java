package com.luobo.wanandroid.ui.home.aq;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.luobo.wanandroid.R;
import com.luobo.wanandroid.WebActivity;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class AqFragment extends Fragment {
    RecyclerView recyclerView;
    AqViewModel viewModel;
    AqAdapter aqAdapter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.aqRecyclerView);

        aqAdapter = new AqAdapter(new AqDiffUtil());
        ConcatAdapter concatAdapter = new ConcatAdapter(aqAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(concatAdapter);

        viewModel.getAq().observe(getViewLifecycleOwner(), aqResponse -> {
            List<AqResponse.DataBean.DatasBean> data = new ArrayList<>(aqResponse.getData().getDatas());
            aqAdapter.submitList(data);
        });


        RefreshLayout refreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                viewModel.refresh();
                viewModel.getAq();
                Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
                refreshlayout.finishRefresh(500/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                viewModel.getAq();
                refreshlayout.finishLoadMore(500/*,false*/);//传入false表示加载失败
            }
        });

       /* SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
       swipeRefreshLayout.setOnRefreshListener(() ->
        {
            viewModel.refresh();
            viewModel.getAq();
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
        });*/


       /* recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy <= 0) return;
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager.findLastCompletelyVisibleItemPosition() == aqAdapter.getItemCount() - 1) {
                    viewModel.getAq();
                }
            }
        });*/
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(AqViewModel.class);

        return inflater.inflate(R.layout.fragment_aq, container, false);
    }


    class AqAdapter extends ListAdapter<AqResponse.DataBean.DatasBean, AqAdapter.MyViewHolder> {

        protected AqAdapter(@NonNull DiffUtil.ItemCallback<AqResponse.DataBean.DatasBean> diffCallback) {
            super(diffCallback);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView;
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_aq, parent, false);
            return new MyViewHolder(itemView);

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