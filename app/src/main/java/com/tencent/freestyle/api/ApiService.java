package com.tencent.freestyle.api;

import com.tencent.freestyle.model.response.NewsResponse;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Greyzhou on 2017/9/20.
 */

public interface ApiService {
    String GET_ARTICLE_LIST = "api/news/feed/v54/?refer=1&count=20&loc_mode=4&device_id=34960436458";

    @GET(GET_ARTICLE_LIST)
    Observable<NewsResponse> getNewsList(@Query("category") String category, @Query("min_behot_time") long lastTime, @Query("last_refresh_sub_entrance_interval") long currentTime);
}
