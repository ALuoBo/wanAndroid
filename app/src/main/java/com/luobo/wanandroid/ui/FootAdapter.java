package com.luobo.wanandroid.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.luobo.wanandroid.R;

/**
 * 通用底部刷新栏视图
 */
public class FootAdapter extends RecyclerView.Adapter<FootAdapter.FootViewHolder> {
    private Context context;

    public FootAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FootViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.footer, parent, false);
        return new FootViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FootViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
