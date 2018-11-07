package com.test.ahsan.app.model.retrofit;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ahsan on 11/09/2018.
 */

public interface NetworkCalls {
    @GET("discover/movie")
    Observable<String> getMovies(@Query("api_key") String api_key,
                                 @Query("page") int page);

}
