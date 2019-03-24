package com.pentilmis.pen_tix.Fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pentilmis.pen_tix.Activity.DetailMovieActivity;
import com.pentilmis.pen_tix.Activity.MainActivity.Adapter;
import com.pentilmis.pen_tix.BuildConfig;
import com.pentilmis.pen_tix.DatabaseGenre.Genre;
import com.pentilmis.pen_tix.DatabaseGenre.GenreHepler;
import com.pentilmis.pen_tix.Object.Movie;
import com.pentilmis.pen_tix.R;
import com.pentilmis.pen_tix.Retrofit.Client;
import com.pentilmis.pen_tix.Retrofit.Server;
import com.pentilmis.pen_tix.Return.Movie.Film;
import com.pentilmis.pen_tix.Return.Movie.ReturnPlay;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayingNowFragment extends Fragment implements DiscreteScrollView.OnItemChangedListener{
    TextView titile,genre1,numberrating,valuelanguage,languae,rating,view1;
    private DiscreteScrollView itemPicker;
    private InfiniteScrollAdapter infiniteAdapter;
    List<Movie> movies = new ArrayList<>();
    List<Film> films;
    List<Integer> genre;
    GenreHepler genreHepler;
    Genre cek,cek1;
    ProgressBar progressBar;
    LinearLayout linearLayout,detail;
    public PlayingNowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playing_now, container, false);
        titile = view.findViewById(R.id.title);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Quicksand-Bold.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
        titile.setTypeface(custom_font);
        itemPicker = (DiscreteScrollView) view.findViewById(R.id.item_picker);
        itemPicker.setOrientation(DSVOrientation.HORIZONTAL);
        itemPicker.addOnItemChangedListener(this);
        itemPicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
        progressBar = view.findViewById(R.id.loading);
        detail = view.findViewById(R.id.detail);
        genre1 = view.findViewById(R.id.genre);
        genre1.setTypeface(custom_font1);
        numberrating = view.findViewById(R.id.numberrating);
        numberrating.setTypeface(custom_font);
        valuelanguage = view.findViewById(R.id.valuelanguage);
        valuelanguage.setTypeface(custom_font);
        languae = view.findViewById(R.id.language);
        languae.setTypeface(custom_font1);
        rating = view.findViewById(R.id.rating);
        rating.setTypeface(custom_font1);
        view1 = view.findViewById(R.id.view);
        view1.setTypeface(custom_font);
        linearLayout = view.findViewById(R.id.klik);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                new Load().execute();
            }
        }, 500);

    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int position) {
        int positionInDataSet = infiniteAdapter.getRealPosition(position);
        onItemChanged(movies.get(positionInDataSet));
    }

    private void onItemChanged(final Movie movie) {
        if (movie.getTitle().length()>22)
        {
            titile.setText(movie.getTitle().substring(0,22)+"...");
        }
        else
        {
            titile.setText(movie.getTitle());
        }
        genre1.setText(movie.getGenre1());
        numberrating.setText(movie.getRating());
        valuelanguage.setText(movie.getBahasa());
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), DetailMovieActivity.class);
                i.putExtra("id_movie",movie.getId());
                startActivity(i);
            }
        });
    }

    public class Load extends AsyncTask<Void, Integer, Void> {
        @Override
        protected void onPreExecute() {
           progressBar.setVisibility(View.VISIBLE);
           detail.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {


        }

        @Override
        protected Void doInBackground(Void... voids) {
            Client api = Server.builder().create(Client.class);
            Call<ReturnPlay> cari = api.now(BuildConfig.TMDB_API_KEY);
            genreHepler = new GenreHepler(getContext());
            genreHepler.open();
            cari.enqueue(new Callback<ReturnPlay>() {
                @Override
                public void onResponse(Call<ReturnPlay> call, Response<ReturnPlay> response) {
                    films = response.body().getFilms();
                    for(int i =0;i<films.size();i++)
                    {
                        Movie movie = new Movie();
                        movie.setId(films.get(i).getId_movie());
                        movie.setTitle(films.get(i).getTitle());
                        movie.setBahasa(films.get(i).getLanguage());
                        movie.setPoster("https://image.tmdb.org/t/p/w500"+films.get(i).getPoster());
                        movie.setRating(films.get(i).getRating());
                        genre = films.get(i).getGenre();
                        cek =genreHepler.find(genre.get(0).toString());
                        movie.setGenre1(cek.getGenre());
                        if(genre.size()>1)
                        {
                            cek1 =genreHepler.find(genre.get(1).toString());
                            movie.setGenre2(cek1.getGenre());
                        }
                        movies.add(movie);
                        infiniteAdapter = InfiniteScrollAdapter.wrap(new Adapter(movies,getContext()));
                        itemPicker.setAdapter(infiniteAdapter);
                        detail.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ReturnPlay> call, Throwable t) {
                    t.printStackTrace();
                }
            });
            return null;
        }
    }
}
