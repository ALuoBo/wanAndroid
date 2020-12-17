package com.luobo.wanandroid.ui.project;

import androidx.lifecycle.MutableLiveData;

import com.luobo.wanandroid.api.ApiService;
import com.luobo.wanandroid.api.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectRepository {
    private ApiService service = RetrofitFactory.getInstance();

    public MutableLiveData<ProjectTreeBean> getProjectTree() {
        MutableLiveData<ProjectTreeBean> data = new MutableLiveData<>();
        service.getProjectTree().enqueue(new Callback<ProjectTreeBean>() {

            @Override
            public void onResponse(Call<ProjectTreeBean> call, Response<ProjectTreeBean> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ProjectTreeBean> call, Throwable t) {

            }

        });
        return data;
    }
}
