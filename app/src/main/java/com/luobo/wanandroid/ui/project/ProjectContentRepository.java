package com.luobo.wanandroid.ui.project;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.luobo.wanandroid.api.ApiService;
import com.luobo.wanandroid.api.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class ProjectContentRepository {
    private String TAG = this.getClass().getName();
    private boolean isFirst = true;
    private boolean isLoading = false;

    private ProjectContentRepository() {
    }

    private ApiService service = RetrofitFactory.getInstance();
    private static ProjectContentRepository instance;

    public static ProjectContentRepository getInstance() {
        if (instance == null) {
            instance = new ProjectContentRepository();
        }
        return instance;
    }

    private int page = 1;
    private ProjectContentBean beans;
    private MutableLiveData<ProjectContentBean> data;
    private int _cid;

    public MutableLiveData<ProjectContentBean> getProjectContent(int cid) {
        if (_cid != cid) {
            _cid = cid;
            page = 1;
            data = new MutableLiveData<>();
            beans = new ProjectContentBean();
        }

        service.getProjectContent(page, _cid).enqueue(new Callback<ProjectContentBean>() {
            @Override
            public void onResponse(Call<ProjectContentBean> call, Response<ProjectContentBean> response) {
                if (page == 1) {
                    beans.setData(response.body().getData());
                } else {
                    Log.d(TAG, "onResponse: " + page);
                    beans.getData().getDatas().addAll(response.body().getData().getDatas());
                }
                data.setValue(beans);
                page++;
            }

            @Override
            public void onFailure(Call<ProjectContentBean> call, Throwable t) {

            }
        });
        return data;
    }
}
