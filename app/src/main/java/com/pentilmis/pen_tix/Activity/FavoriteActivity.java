package com.pentilmis.pen_tix.Activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pentilmis.pen_tix.DatabseMovie.Film;
import com.pentilmis.pen_tix.DatabseMovie.MovieHelper;
import com.pentilmis.pen_tix.R;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AdapterFavorite adapterFavorite;
    MovieHelper movieHelper;
    TextView kosong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        recyclerView = findViewById(R.id.rc_favorit);
        kosong = findViewById(R.id.kosong);
        new Load().execute();
    }

    public class Load extends AsyncTask<Void, Integer, Void> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(Void aVoid) {


        }

        @Override
        protected Void doInBackground(Void... voids) {
            movieHelper = new MovieHelper(FavoriteActivity.this);
            movieHelper.open();
            List<Film>  films = movieHelper.query();
            adapterFavorite = new AdapterFavorite(FavoriteActivity.this,films);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapterFavorite);
            if(films.size()==0)
            {
                kosong.setVisibility(View.VISIBLE);
            }
            return null;
        }
    }
}
