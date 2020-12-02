package com.luobo.wanandroid.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
import com.luobo.wanandroid.base.BaseActivity;
import com.luobo.wanandroid.ui.home.ArticleDataBean;
import com.luobo.wanandroid.ui.home.ArticleDiffUtil;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity {
    SearchView searchView;
    RecyclerView recyclerView;
    SearchViewModel viewModel;
    MyAdapter adapter;
    String keywords = "";
    LoadMoreObserver observer = new LoadMoreObserver();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach);
        searchView = findViewById(R.id.searchView);
        searchView.findViewById(R.id.search_plate).setBackground(null);
        searchView.findViewById(R.id.submit_area).setBackground(null);
        searchView.setIconifiedByDefault(false);
        recyclerView = findViewById(R.id.searchResultRecycler);
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        adapter = new MyAdapter(new ArticleDiffUtil());
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy < 0) return;
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager.findLastCompletelyVisibleItemPosition() == adapter.getItemCount() - 1 && !keywords.isEmpty()) {
                    viewModel.LoadMoreResult(keywords).observe(SearchActivity.this, observer);
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                keywords = query;
                viewModel.getSearchResult(keywords).observe(SearchActivity.this, new Observer<ArticleDataBean>() {
                    @Override
                    public void onChanged(ArticleDataBean articleDataBean) {

                        adapter.submitList(articleDataBean.getData().getDatas());

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

    class MyAdapter extends ListAdapter<ArticleDataBean.DataBean.DatasBean, MyAdapter.SearchResultViewHolder> {
        //private static final int HEADER_VIEW_TYPE = -1;
        private static final int NORMAL_VIEW_TYPE = 0;
        private static final int FOOTER_VIEW_TYPE = 1;

        protected MyAdapter(@NonNull DiffUtil.ItemCallback<ArticleDataBean.DataBean.DatasBean> diffCallback) {
            super(diffCallback);
        }

        @NonNull
        @Override
        public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView;
            switch (viewType) {
                case FOOTER_VIEW_TYPE:
                    itemView = LayoutInflater.from(SearchActivity.this).inflate(R.layout.footer_layout, parent, false);
                    break;
                default:
                    itemView = LayoutInflater.from(SearchActivity.this).inflate(R.layout.article_item_layout, parent, false);
                    break;
            }

            return new SearchResultViewHolder(itemView);
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
        public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position) {
            if (position == getItemCount() - 1) {
                return;
            } else {
                TextView textView = holder.itemView.findViewById(R.id.title);
                TextView classify = holder.itemView.findViewById(R.id.classify);
                CardView cardView = holder.itemView.findViewById(R.id.articleItem);
                textView.setText(Html.fromHtml(getItem(position).getTitle()));
                classify.setText(getItem(position).getChapterName());
                cardView.setOnClickListener(v -> {
                    Intent intent = new Intent(SearchActivity.this, WebActivity.class);
                    intent.putExtra("URL", getItem(position).getLink());
                    startActivity(intent);
                });
            }
        }

        class SearchResultViewHolder extends RecyclerView.ViewHolder {

            public SearchResultViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }

    class LoadMoreObserver implements Observer<ArticleDataBean> {
        @Override
        public void onChanged(ArticleDataBean articleDataBean) {
            Log.e("myTag", "onChange");
            List<ArticleDataBean.DataBean.DatasBean> data = new ArrayList<>();
            data.addAll(articleDataBean.getData().getDatas());
            adapter.submitList(data);
        }
    }
}