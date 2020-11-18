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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.luobo.wanandroid.R;
import com.luobo.wanandroid.WebActivity;

public class AqFragment extends Fragment {
    RecyclerView recyclerView;
    AqViewModel viewModel;
    AqResponse data;
    AqAdapter adapter;

    public AqFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.aqRecyclerView);
        recyclerView.setAdapter(adapter = new AqAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new ViewModelProvider(requireActivity()).get(AqViewModel.class);

        viewModel.getAq(1).observe(getViewLifecycleOwner(), new Observer<AqResponse>() {
            @Override
            public void onChanged(AqResponse aqResponse) {
                data = aqResponse;
                Log.e("will", "onChanged: " + data.getData().getSize());
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_aq, container, false);
    }


    class AqAdapter extends RecyclerView.Adapter<AqAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getContext()).inflate(R.layout.aq_item_layout, parent,
                    false));
            return holder;
        }


        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            if (data != null) {
                Log.e("will", "onChanged: " + data.getData().getDatas().get(position).getTitle());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    holder.tv.setText(Html.fromHtml(data.getData().getDatas().get(position).getTitle(), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    holder.tv.setText(Html.fromHtml(data.getData().getDatas().get(position).getTitle()));

                }
                holder.tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), WebActivity.class);
                        intent.putExtra("URL", data.getData().getDatas().get(position).getLink());
                        startActivity(intent);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            if (data != null)
                return data.getData().getSize();
            else return 0;
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