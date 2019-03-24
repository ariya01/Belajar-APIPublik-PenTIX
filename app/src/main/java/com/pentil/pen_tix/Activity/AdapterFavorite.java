package com.pentil.pen_tix.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pentil.pen_tix.DatabaseGenre.GenreHepler;
import com.pentil.pen_tix.DatabseMovie.Film;
import com.pentil.pen_tix.DatabseMovie.MovieHelper;
import com.pentil.pen_tix.R;
import com.pentil.pen_tix.Return.Soon.Content;

import java.util.List;

public class AdapterFavorite extends RecyclerView.Adapter<AdapterFavorite.Holder> {
    MovieHelper movieHelper;
    List<Film> contents;
    Context context;
    public AdapterFavorite (Context context, List<Film> contents)
    {
        this.contents= contents;
        this.context = context;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.rc_comingsoon, viewGroup, false);
        return new AdapterFavorite.Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
        holder.title.setText(contents.get(i).getTitle());
        Glide.with(holder.itemView.getContext())
                .load(contents.get(i).getPoseter())
                .into(holder.poster);
//        Log.d("cek poster", "onBindViewHolder: "+"https://image.tmdb.org/t/p/w500"+contents.get(i).getPoseter());
        holder.genre.setText(contents.get(i).getGenre());
        if (contents.get(i).getAdult().equals("false"))
        {
            holder.boder.setBackground(context.getResources().getDrawable(R.drawable.child_background));
            holder.adult.setTextColor(context.getResources().getColor(R.color.pudar));
        }
        holder.release.setText(contents.get(i).getRelease());
        movieHelper = new MovieHelper(context);
        movieHelper.open();
        if(movieHelper.find(contents.get(i).getMovie())!=null)
        {
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.fill)
                    .into(holder.love);
        }
        else
        {
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.unfill)
                    .into(holder.love);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(context, DetailMovieActivity.class);
                a.putExtra("id_movie",contents.get(i).getId_movie());
                context.startActivity(a);
            }
        });
        holder.love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d(TAG, "onClick: "+movieHelper.find(contents.get(i).getId_movie()));
                if(movieHelper.find(contents.get(i).getMovie())==null)
                {
                    Film  film = new Film();
                    film.setMovie(contents.get(i).getTitle());
                    film.setId_movie(contents.get(i).getId_movie());
                    film.setGenre(contents.get(i).getGenre());
                    film.setAdult(contents.get(i).getAdult());
                    film.setRelease(contents.get(i).getRelease());
                    film.setTitle(contents.get(i).getTitle());
                    film.setPoseter("https://image.tmdb.org/t/p/w500"+contents.get(i).getPoseter());
                    movieHelper.insert(film);
                    Toast.makeText(context, "Berhasil Di Tambahkan Sebagai Film Favorit", Toast.LENGTH_SHORT).show();
                    Glide.with(holder.itemView.getContext())
                            .load(R.drawable.fill)
                            .into(holder.love);
                }
                else
                {
                    movieHelper.delete(contents.get(i).getMovie());
                    Toast.makeText(context, "Berhasil Di Dihapus Sebagai Film Favorit", Toast.LENGTH_SHORT).show();
                    Glide.with(holder.itemView.getContext())
                            .load(R.drawable.unfill)
                            .into(holder.love);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView poster,love;
        TextView release,title,adult,genre;
        LinearLayout boder;
        public Holder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.gambar);
            love = itemView.findViewById(R.id.fill);
            release = itemView.findViewById(R.id.release);
            title = itemView.findViewById(R.id.title);
            adult = itemView.findViewById(R.id.adult);
            genre = itemView.findViewById(R.id.genre);
            boder = itemView.findViewById(R.id.boder);
        }
    }
}
