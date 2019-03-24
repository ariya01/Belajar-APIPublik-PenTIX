package com.pentil.pen_tix.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pentil.pen_tix.Activity.MainActivity.MainActivity;
import com.pentil.pen_tix.Utility.AppPreference;
import com.pentil.pen_tix.BuildConfig;
import com.pentil.pen_tix.DatabaseGenre.GenreHepler;
import com.pentil.pen_tix.R;
import com.pentil.pen_tix.Retrofit.Client;
import com.pentil.pen_tix.Retrofit.Server;
import com.pentil.pen_tix.Return.Genre.Genre;
import com.pentil.pen_tix.Return.Genre.ReturnGenre;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Splash extends AppCompatActivity {
    AppPreference appPreference;
    Boolean aBoolean;
    ProgressBar progressBar;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    double progress;
    double maxprogress = 100;
    GenreHepler genreHepler;
    TextView judul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = findViewById(R.id.loading);
        genreHepler = new GenreHepler(this);
        genreHepler.open();
        judul = findViewById(R.id.judul);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/segoe_print_bold.ttf");
        judul.setTypeface(custom_font);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                progressBar.setProgress(30);
//                new Load().execute();
                loaddata();
            }
        }, 2000);

    }

    public void loaddata()
    {
        Client api = Server.builder().create(Client.class);
        appPreference = new AppPreference(Splash.this);
        if (appPreference.getFirstRun())
        {
            compositeDisposable.add(api.genre(BuildConfig.TMDB_API_KEY).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ReturnGenre>() {
                @Override
                public void accept(ReturnGenre returnGenre) throws Exception {
                        List<Genre> genres = returnGenre.getGenres();
                        com.pentil.pen_tix.DatabaseGenre.Genre genre;
                        for (int i =0 ; i<genres.size();i++)
                        {
                            genre = new com.pentil.pen_tix.DatabaseGenre.Genre();
                            genre.setGenre(genres.get(i).getName());
                            genre.setId_genre(genres.get(i).getId());
                            genreHepler.insert(genre);
                        }
                }
            }));
        }
        Intent i = new Intent(Splash.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private class Load extends AsyncTask<Void, Integer, Void>{

        @Override
        protected void onPreExecute() {
            appPreference = new AppPreference(Splash.this);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressBar.setProgress(100);

            Intent i = new Intent(Splash.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            aBoolean = appPreference.getFirstRun();
            if (aBoolean)
            {
                Client api = Server.builder().create(Client.class);
//                Call<ReturnGenre> cari = api.genre(BuildConfig.TMDB_API_KEY);
//                cari.enqueue(new Callback<ReturnGenre>() {
//                    @Override
//                    public void onResponse(Call<ReturnGenre> call, Response<ReturnGenre> response) {
//                        List<Genre> genres = response.body().getGenres();
//                        com.pentil.pen_tix.DatabaseGenre.Genre genre;
//                        for (int i =0 ; i<genres.size();i++)
//                        {
//                            genre = new com.pentil.pen_tix.DatabaseGenre.Genre();
//                            genre.setGenre(genres.get(i).getName());
//                            genre.setId_genre(genres.get(i).getId());
//                            genreHepler.insert(genre);
//                        }
//                        Log.d("test", "doInBackground: "+ genreHepler.query().size());
//                    }
//
//                    @Override
//                    public void onFailure(Call<ReturnGenre> call, Throwable t) {
//
//                    }
//                });
            }
            progressBar.setProgress(70);
            appPreference.setFirstRun(false);
            return null;
        }
    }
}
