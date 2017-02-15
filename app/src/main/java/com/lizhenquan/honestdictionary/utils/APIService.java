package com.lizhenquan.honestdictionary.utils;

import com.lizhenquan.honestdictionary.bean.BaiduBean;
import com.lizhenquan.honestdictionary.bean.JsonWordBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by lizhenquan on 2017/2/12.
 */

public interface APIService {
    //http://xtk.azurewebsites.net/BingDictService.aspx?Word="apple"
    @GET("BingDictService.aspx")
    Call<JsonWordBean> getJsonString(@Query("Word") String params);

//http://api.fanyi.baidu.com/api/trans/vip/translate?
// q=apple&from=en&to=zh&appid=2015063000000001&salt=1435660288&sign=f89f9594663708c1605f3d736d01d2d4
    @GET("translate")
    Call<BaiduBean> getBaiduJsonString(@QueryMap Map<String,String> params);

}
