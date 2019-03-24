package com.pentilmis.pen_tix.Retrofit;



import com.pentilmis.pen_tix.Return.Detail.RetuenDetail;
import com.pentilmis.pen_tix.Return.Genre.ReturnGenre;
import com.pentilmis.pen_tix.Return.Movie.ReturnPlay;
import com.pentilmis.pen_tix.Return.Soon.ReturnSoon;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface  Client {
    @GET("genre/list")
    Observable<ReturnGenre> genre(
            @Query("api_key") String api_key
    );

    @GET("movie/now_playing")
    Call<ReturnPlay> now(
            @Query("api_key") String api_key
    );

    @GET("movie/{path}")
    Observable<Response<RetuenDetail>> movie(
            @Path("path") String id_move,
            @Query("api_key") String api_key,
            @Query("append_to_response") String append

    );

    @GET("discover/movie")
    Observable<ReturnSoon> soon(
            @Query("api_key") String api_key,
            @Query("primary_release_date.gte") String date
    );
}
