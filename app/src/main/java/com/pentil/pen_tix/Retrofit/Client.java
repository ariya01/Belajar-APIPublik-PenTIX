package com.pentil.pen_tix.Retrofit;

import com.pentil.pen_tix.Return.Detail.RetuenDetail;
import com.pentil.pen_tix.Return.Genre.ReturnGenre;
import com.pentil.pen_tix.Return.Movie.ReturnPlay;
import com.pentil.pen_tix.Return.Soon.ReturnSoon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface  Client {
    @GET("genre/list")
    Call<ReturnGenre> genre(
            @Query("api_key") String api_key
    );

    @GET("movie/now_playing")
    Call<ReturnPlay> now(
            @Query("api_key") String api_key
    );

    @GET("movie/{path}")
    Call<RetuenDetail> movie(
            @Path("path") String id_move,
            @Query("api_key") String api_key,
            @Query("append_to_response") String append

    );

    @GET("discover/movie")
    Call<ReturnSoon> soon(
            @Query("api_key") String api_key,
            @Query("primary_release_date.gte") String date
    );
}
