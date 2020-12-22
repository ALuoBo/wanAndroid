package com.luobo.wanandroid.ui.project;

import androidx.lifecycle.MutableLiveData;

import com.luobo.wanandroid.api.ApiService;
import com.luobo.wanandroid.api.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class ProjectContentRepository {
    private int page = 1;
    private boolean isFirst = true;
    private boolean isLoading = false;
    private ApiService service = RetrofitFactory.getInstance();

    public MutableLiveData<ProjectContentBean> getProjectContent(int cid) {

        MutableLiveData<ProjectContentBean> data = new MutableLiveData<>();

        service.getProjectContent(page, cid).enqueue(new Callback<ProjectContentBean>() {
            @Override
            public void onResponse(Call<ProjectContentBean> call, Response<ProjectContentBean> response) {
                data.setValue(response.body());
                page++;
            }

            @Override
            public void onFailure(Call<ProjectContentBean> call, Throwable t) {

            }
        });
        return data;
    }
}
