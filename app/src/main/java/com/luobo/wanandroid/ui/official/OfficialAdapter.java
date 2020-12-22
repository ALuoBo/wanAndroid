package com.luobo.wanandroid.ui.official;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.luobo.wanandroid.R;
import com.luobo.wanandroid.WebActivity;

class OfficialAdapter extends ListAdapter<OfficialBean.DataBean.DatasBean, OfficialAdapter.MViewHolder> {
    private Context context;

    protected OfficialAdapter(Context context, @NonNull DiffUtil.ItemCallback<OfficialBean.DataBean.DatasBean> diffCallback) {
        super(diffCallback);
        this.context = context;
    }


    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false);
        return new MViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MViewHolder holder, int position) {
        TextView textView = holder.itemView.findViewById(R.id.title);
        TextView classify = holder.itemView.findViewById(R.id.classify);
        CardView cardView = holder.itemView.findViewById(R.id.articleItem);
        textView.setText(getItem(position).getTitle());
        classify.setText(getItem(position).getChapterName());
        cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, WebActivity.class);
            intent.putExtra("URL", getItem(position).getLink());
            context.startActivity(intent);
        });

    }

    class MViewHolder extends RecyclerView.ViewHolder {

        public MViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
