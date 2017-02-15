package com.lizhenquan.honestdictionary.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lizhenquan on 2017/1/8.
 */

public class RetrofitUtil {
    private static Retrofit retrofit;

    /**
     * @return retrofit 实例
     */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Urls.BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getRetrofitInstance2() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Urls.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    /**
     * @return 接口实例
     *
     */
    public static APIService getAPIRetrofitInstance() {
        return getRetrofitInstance().create(APIService.class);
    }
    public static APIService getAPIRetrofitInstance2() {
        return getRetrofitInstance2().create(APIService.class);
    }

}
