package com.luobo.wanandroid.ui.official;

import androidx.lifecycle.MutableLiveData;

import com.luobo.wanandroid.api.ApiService;
import com.luobo.wanandroid.api.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class OfficialTreeRepository {
    private ApiService service = RetrofitFactory.getInstance();

    MutableLiveData<OfficialTreeBean> getTabs() {
        MutableLiveData<OfficialTreeBean> datas = new MutableLiveData<>();
        service.getOfficialTree().enqueue(new Callback<OfficialTreeBean>() {
            @Override
            public void onResponse(Call<OfficialTreeBean> call, Response<OfficialTreeBean> response) {
                datas.setValue(response.body());
            }

            @Override
            public void onFailure(Call<OfficialTreeBean> call, Throwable t) {

            }
        });
        return datas;
    }
}
