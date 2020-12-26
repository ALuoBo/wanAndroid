package com.luobo.wanandroid.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.luobo.wanandroid.R;
import com.luobo.wanandroid.ui.home.article.ArticleAdapter;
import com.luobo.wanandroid.ui.home.article.ArticleBean;
import com.luobo.wanandroid.ui.home.article.ArticleDiffUtil;
import com.luobo.wanandroid.ui.home.top.ToppingAdapter;
import com.luobo.wanandroid.ui.home.top.ToppingBean;
import com.luobo.wanandroid.ui.home.top.ToppingDiffUtil;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    static String TAG = "HomeFragment";
    HomeViewModel viewModel;
    ToppingAdapter toppingAdapter;
    ArticleAdapter articleAdapter;
    HomeAdapter homeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: " + this);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onViewCreated: " + this);
        RecyclerView recyclerView = view.findViewById(R.id.homeRecycler);

        toppingAdapter = new ToppingAdapter(getContext(), new ToppingDiffUtil());
        articleAdapter = new ArticleAdapter(getContext(), new ArticleDiffUtil());

        homeAdapter = new HomeAdapter(getContext(), getLifecycle());
        RefreshLayout refreshLayout = (RefreshLayout) view.findViewById(R.id.homeSwipeRefresh);
        refreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
                refreshlayout.finishRefresh(500/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                viewModel.getData();
                refreshlayout.finishLoadMore(500/*,false*/);//传入false表示加载失败
            }
        });

        ConcatAdapter concatAdapter = new ConcatAdapter(homeAdapter, toppingAdapter, articleAdapter);
        recyclerView.setAdapter(concatAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

      /*  //Banner
        viewModel.getBanner().observe(getViewLifecycleOwner(), homeBannerBean -> {
            HomeAdapter.BannerViewHolder viewHolder =
                    (HomeAdapter.BannerViewHolder) recyclerView.getChildViewHolder(recyclerView.getChildAt(0));
            viewHolder.mBannerViewPager.refreshData(homeBannerBean.getData());
        });
*/
        //Top
        viewModel.getTopping().observe(getViewLifecycleOwner(), toppingBean -> {
            List<ToppingBean.DataBean> data = new ArrayList(toppingBean.getData());
            toppingAdapter.submitList(data);
        });
        //Article
        viewModel.getData().observe(getViewLifecycleOwner(), articleDataBean -> {
            ArrayList<ArticleBean.DataBean.DatasBean> data = new ArrayList(articleDataBean.getData().getDatas());
            articleAdapter.submitList(data);
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy < 0) return;
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager.findLastCompletelyVisibleItemPosition() == articleAdapter.getItemCount() - 1) {
                    Log.e(TAG, "onScrolled: ");
                    viewModel.getData();
                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " + this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView: " + this);
    }

    @Override
    public void onDestroy() {

        Log.e(TAG, "onDestroy: " + this);
        super.onDestroy();
    }
}