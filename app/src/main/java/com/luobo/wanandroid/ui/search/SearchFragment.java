package com.luobo.wanandroid.ui.search;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.luobo.wanandroid.R;
import com.luobo.wanandroid.WebActivity;
import com.luobo.wanandroid.base.BaseFragment;
import com.luobo.wanandroid.ui.home.article.ArticleBean;
import com.luobo.wanandroid.ui.home.article.ArticleDiffUtil;
import com.luobo.wanandroid.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class SearchFragment extends BaseFragment {
    SearchView searchView;
    RecyclerView recyclerView;
    SearchViewModel viewModel;
    MyAdapter adapter;
    String keywords = "";
    LoadMoreObserver observer = new LoadMoreObserver();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().findViewById(R.id.bottomNavigationView).setVisibility(View.GONE);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                searchViewAnim(false);

                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        getNavController(requireActivity()).navigateUp();
                    }
                };

                timer.schedule(task,250);

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_serach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView = view.findViewById(R.id.searchView);
        searchView.findViewById(R.id.search_plate).setBackground(null);
        searchView.findViewById(R.id.submit_area).setBackground(null);

        TextView textSearch = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        textSearch.setTextSize(14);
        searchViewAnim(true);
        recyclerView = view.findViewById(R.id.searchResultRecycler);
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        adapter = new MyAdapter(new ArticleDiffUtil());
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy < 0) return;
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager.findLastCompletelyVisibleItemPosition() == adapter.getItemCount() - 1 && !keywords.isEmpty()) {
                    viewModel.LoadMoreResult(keywords).observe(getViewLifecycleOwner(), observer);
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                keywords = query;
                viewModel.getSearchResult(keywords).observe(getViewLifecycleOwner(), new Observer<ArticleBean>() {
                    @Override
                    public void onChanged(ArticleBean articleBean) {
                        adapter.submitList(articleBean.getData().getDatas());

                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }


    /**
     * 搜索框展开动画
     */
    private void searchViewAnim(boolean isIn) {
        int searchWidth = ScreenUtil.getScreenWidth(getActivity()) - ScreenUtil.dp2px(getActivity(), 134);
        int targetWidth = ScreenUtil.getScreenWidth(getActivity()) - ScreenUtil.dp2px(getActivity(), 32);
        ValueAnimator animator;
        if (isIn) {
            animator = ValueAnimator.ofInt(searchWidth, targetWidth);
        } else {
            animator = ValueAnimator.ofInt(targetWidth, searchWidth);
        }
        animator.setDuration(300);
        animator.addUpdateListener(animation -> {
            int value = (int) animation.getAnimatedValue();
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) searchView.getLayoutParams();
            params.width = value;
            searchView.setLayoutParams(params);
        });
        animator.start();
    }

    class MyAdapter extends ListAdapter<ArticleBean.DataBean.DatasBean, MyAdapter.SearchResultViewHolder> {


        protected MyAdapter(@NonNull DiffUtil.ItemCallback<ArticleBean.DataBean.DatasBean> diffCallback) {
            super(diffCallback);
        }

        @NonNull
        @Override
        public MyAdapter.SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView;

            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_article, parent, false);
            return new MyAdapter.SearchResultViewHolder(itemView);
        }


        @Override
        public void onBindViewHolder(@NonNull MyAdapter.SearchResultViewHolder holder, int position) {

            TextView textView = holder.itemView.findViewById(R.id.title);
            TextView classify = holder.itemView.findViewById(R.id.classify);
            CardView cardView = holder.itemView.findViewById(R.id.articleItem);
            textView.setText(Html.fromHtml(getItem(position).getTitle()));
            classify.setText(getItem(position).getChapterName());
            cardView.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("URL", getItem(position).getLink());
                startActivity(intent);
            });

        }

        class SearchResultViewHolder extends RecyclerView.ViewHolder {

            public SearchResultViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }

    class LoadMoreObserver implements Observer<ArticleBean> {
        @Override
        public void onChanged(ArticleBean articleBean) {
            Log.e("myTag", "onChange");
            List<ArticleBean.DataBean.DatasBean> data = new ArrayList<>();
            data.addAll(articleBean.getData().getDatas());
            adapter.submitList(data);
        }

    }
}