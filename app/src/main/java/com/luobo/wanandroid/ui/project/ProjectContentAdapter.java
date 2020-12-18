package com.luobo.wanandroid.ui.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.luobo.wanandroid.R;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

class ProjectContentAdapter extends ListAdapter<ProjectContentBean.DataBean.DatasBean, ProjectContentAdapter.MViewHolder> {
    private Context context;

    protected ProjectContentAdapter(Context context, @NonNull DiffUtil.ItemCallback<ProjectContentBean.DataBean.DatasBean> diffCallback) {
        super(diffCallback);
        this.context = context;
    }


    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.project_item_layout, parent, false);
        return new MViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MViewHolder holder, int position) {

        Glide.with(context)
                .load(getItem(position).getEnvelopePic())
                .centerCrop()
                .transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.previewImageView);

        holder.title.setText(getItem(position).getTitle());
    }

    class MViewHolder extends RecyclerView.ViewHolder {
        ImageView previewImageView;
        TextView title;

        public MViewHolder(@NonNull View itemView) {
            super(itemView);
            previewImageView = itemView.findViewById(R.id.previewImageView);
            title = itemView.findViewById(R.id.titleTextView);
        }
    }
}
