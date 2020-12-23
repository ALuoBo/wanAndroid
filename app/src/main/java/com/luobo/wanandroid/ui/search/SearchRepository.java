package com.luobo.wanandroid.ui.search;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.luobo.wanandroid.api.ApiService;
import com.luobo.wanandroid.api.RetrofitFactory;
import com.luobo.wanandroid.ui.home.ArticleBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRepository {

    private static String TAG = "SearchRepository";
    private int page = 0;

    private boolean isLoading = false;
    private boolean isFirstRequest = true;
    private String oldKeyword = "";
    ArticleBean datasBean = new ArticleBean();

    private ApiService service = RetrofitFactory.getInstance();
    MutableLiveData<ArticleBean> data = new MutableLiveData<>();

    private SearchRepository() {
    }

    private static SearchRepository instance;

    public static SearchRepository getInstance() {
        if (instance == null) {
            return instance = new SearchRepository();
        } else {
            return instance;
        }
    }

    /**
     * @param keywords 关键字
     * @return liveData 搜索结果
     * @Description 搜索键搜索
     */
    public MutableLiveData<ArticleBean> getSearchResult(String keywords) {
        MutableLiveData<ArticleBean> liveData = new MutableLiveData<>();
        service.search(0, keywords).enqueue(new Callback<ArticleBean>() {
            @Override
            public void onResponse(Call<ArticleBean> call, Response<ArticleBean> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArticleBean> call, Throwable t) {

            }
        });
        return liveData;
    }

    /**
     * @param keyword 搜索关键字
     * @return liveData 搜索结果
     * @Description 加载更多
     */
    public MutableLiveData<ArticleBean> LoadMoreResult(String keyword) {

        if (!isSameKeyword(keyword)) {
            page = 0;
            isFirstRequest = true;
        }
        if (isLoading) return data;
        isLoading = true;
        service.search(page, keyword).enqueue(new Callback<ArticleBean>() {
            @Override
            public void onResponse(Call<ArticleBean> call, Response<ArticleBean> response) {
                if (isFirstRequest) {
                    oldKeyword = keyword;
                    datasBean.setData(response.body().getData());
                    isFirstRequest = false;
                } else {
                    datasBean.getData().getDatas().addAll(response.body().getData().getDatas());
                }
                data.setValue(datasBean);
                page++;
                isLoading = false;
            }

            @Override
            public void onFailure(Call<ArticleBean> call, Throwable t) {
                isLoading = false;
            }
        });
        return data;
    }

    /**
     * @param newKeywords 每次请求传入的关键词
     * @return 对比结果
     */
    private boolean isSameKeyword(String newKeywords) {
        Log.e(TAG, "isSameKeyword? " + oldKeyword.equals(newKeywords));
        return oldKeyword.equals(newKeywords);
    }
}
