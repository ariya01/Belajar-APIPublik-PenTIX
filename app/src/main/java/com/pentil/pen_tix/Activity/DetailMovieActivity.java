package com.pentil.pen_tix.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.devs.readmoreoption.ReadMoreOption;
import com.pentil.pen_tix.BuildConfig;
import com.pentil.pen_tix.R;
import com.pentil.pen_tix.Retrofit.Client;
import com.pentil.pen_tix.Retrofit.Server;
import com.pentil.pen_tix.Return.Detail.Cast;
import com.pentil.pen_tix.Return.Detail.DetailGenre;
import com.pentil.pen_tix.Return.Detail.RetuenDetail;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class DetailMovieActivity extends AppCompatActivity {
    private String id_movie,key;
    private Intent intent;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    TextView overview,title,genre,adult,valuerating,valuelanguage;
    private ImageView imageView,poster;
    private RecyclerView rc_cast;
    AdapteCast adapteCast;
    ReadMoreOption readMoreOption;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        intent = getIntent();
        id_movie = intent.getStringExtra("id_movie");
        imageView = findViewById(R.id.gambar);
        poster = findViewById(R.id.poster);
        title = findViewById(R.id.title);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Quicksand-Bold.ttf");
        title.setTypeface(custom_font);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Regular.ttf");
        genre = findViewById(R.id.genreandduration);
        genre.setTypeface(custom_font1);
        adult = findViewById(R.id.valueadult);
        valuelanguage = findViewById(R.id.valuelanguage);
        valuerating = findViewById(R.id.valuerating);
        overview = findViewById(R.id.overview);
        rc_cast = findViewById(R.id.rc_cast);
        readMoreOption = new ReadMoreOption.Builder(this)
                .textLength(3, ReadMoreOption.TYPE_LINE) // OR
                .moreLabel("Readmore")
                .lessLabel("Less")
                .moreLabelColor(R.color.pudar)
                .lessLabelColor(R.color.pudar)
                .labelUnderLine(true)
                .expandAnimation(true)
                .build();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        rc_cast.setLayoutManager(mLayoutManager);
        rc_cast.setItemAnimator(new DefaultItemAnimator());
//        new Load().execute();
        progressBar = findViewById(R.id.progress);
        loadata();
    }

    private void loadata() {
        Client api = Server.builder().create(Client.class);
        api.movie(id_movie,BuildConfig.TMDB_API_KEY,"videos,casts,similar").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<RetuenDetail>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<RetuenDetail> retuenDetailResponse) {
                Log.d("cek", "onNext: "+retuenDetailResponse.body());
                RetuenDetail detail = retuenDetailResponse.body();
                List<Cast> casts = detail.getCastObject().getList();
                Log.d("cek", "onNext: "+casts.get(0).getCharacter());
                    for (int i =0;i<detail.getVideo().getDetailVideo().size();i++)
                    {
                        if(detail.getVideo().getDetailVideo().get(i).getSite().equals("YouTube"))
                        {
                            key=detail.getVideo().getDetailVideo().get(i).getKey();
                            break;
                        }

                    }
                    Log.d("cell", "onResponse: "+"https://image.tmdb.org/t/p/w500"+detail.getPath());
                    Glide.with(DetailMovieActivity.this)
                            .load("https://image.tmdb.org/t/p/w500"+detail.getPath())
                            .into(poster);
                    Glide.with(DetailMovieActivity.this)
                            .load("https://img.youtube.com/vi/"+key+"/0.jpg")
                            .apply(new RequestOptions().override(600, 200).fitCenter())
                            .into(imageView);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+key)));
                        }
                    });
                    title.setText(retuenDetailResponse.body().getTitle());
                    List <DetailGenre> genres = retuenDetailResponse.body().getGenres();
                    String katagen ;
                    katagen = genres.get(0).getNama();
                    if (genres.size()>1)
                    {
                        katagen += " / "+genres.get(1).getNama();
                    }
                    String gabungan;
                    if (detail.getRuntime()!=null)
                    {
                         gabungan = katagen +" - "+ durasi(detail.getRuntime());
                    }
                    else
                    {
                         gabungan = katagen;
                    }
                    genre.setText(gabungan);
                    if(detail.getAdult().equals("false"))
                    {
                        adult.setText("No");
                    }
                    else
                    {
                        adult.setText("Yes");
                    }
                    valuerating.setText(detail.getRating());
                    valuelanguage.setText(detail.getLanguage());
                    readMoreOption.addReadMoreTo(overview, detail.getOverview());
                    adapteCast = new AdapteCast(DetailMovieActivity.this,casts);
                    rc_cast.setAdapter(adapteCast);
                    progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public String durasi(String jam)
    {
        int hours = Integer.parseInt(jam)/60;
        int minut = Integer.parseInt(jam)%60;
        String kata;
        if (hours>1)
        {
            kata = hours+" Hours "+ minut+" minutes";
        }
        else
        {
            kata = hours+" Hour "+ minut+" minutes";
        }
        return kata;
    }
}
