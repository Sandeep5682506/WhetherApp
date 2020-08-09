package com.example.mywhether;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface wheatherapi {
    @GET("weather")
    Call<Example> getweather(@Query("q") String cityname,
                             @Query("appid") String apikey);
}
