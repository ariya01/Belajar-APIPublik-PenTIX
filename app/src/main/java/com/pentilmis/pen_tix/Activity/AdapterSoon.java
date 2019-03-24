package com.pentilmis.pen_tix.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pentilmis.pen_tix.DatabseMovie.Film;
import com.pentilmis.pen_tix.DatabseMovie.MovieHelper;
import com.pentilmis.pen_tix.DatabaseGenre.GenreHepler;
import com.pentilmis.pen_tix.R;
import com.pentilmis.pen_tix.Return.Soon.Content;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class AdapterSoon extends RecyclerView.Adapter<AdapterSoon.Holder> {
    GenreHepler genreHepler;
    MovieHelper movieHelper;
    List<Content> contents;
    Context context;
    public AdapterSoon (Context context, List<Content> contents)
    {
        this.contents= contents;
        this.context = context;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.rc_comingsoon, viewGroup, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
        genreHepler = new GenreHepler(context);
        genreHepler.open();
        movieHelper = new MovieHelper(context);
        movieHelper.open();
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500"+contents.get(i).getPoster())
                .into(holder.poster);
        String pattern = "dd MMM yyyy";
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern, new Locale("in", "ID"));
        final String date = simpleDateFormat.format(contents.get(i).getRelease());
        holder.release.setText(date);
        holder.title.setText(contents.get(i).getTitle());
        final String genre;
        if(movieHelper.find(contents.get(i).getId_movie())!=null)
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
        if(contents.get(i).getGenre().size()>3)
        {
            genre = genreHepler.find(contents.get(i).getGenre().get(0)).getGenre()+" | "+genreHepler.find(contents.get(i).getGenre().get(1)).getGenre()+" | "+genreHepler.find(contents.get(i).getGenre().get(2)).getGenre();
        }
        else if (contents.get(i).getGenre().size()>2)
        {
            genre = genreHepler.find( contents.get(i).getGenre().get(0)).getGenre()+" | "+genreHepler.find(contents.get(i).getGenre().get(1)).getGenre();
        }
        else if (contents.get(i).getGenre().size()>1)
        {
            genre = genreHepler.find( contents.get(i).getGenre().get(0)).getGenre()+"";
        }
        else
        {
            genre = "undifined";
        }
        if (contents.get(i).getAdult().equals("false"))
        {
            holder.boder.setBackground(context.getResources().getDrawable(R.drawable.child_background));
            holder.adult.setTextColor(context.getResources().getColor(R.color.pudar));
        }
        holder.genre.setText(genre);
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
                if(movieHelper.find(contents.get(i).getId_movie())==null)
                {
                    Film  film = new Film();
                    film.setMovie(contents.get(i).getTitle());
                    film.setId_movie(contents.get(i).getId_movie());
                    film.setGenre(genre);
                    film.setAdult(contents.get(i).getAdult());
                    film.setRelease(date);
                    film.setPoseter("https://image.tmdb.org/t/p/w500"+contents.get(i).getPoster());
                    film.setTitle(contents.get(i).getTitle());
                    movieHelper.insert(film);
                    Toast.makeText(context, "Berhasil Di Tambahkan Sebagai Film Favorit", Toast.LENGTH_SHORT).show();
                    Glide.with(holder.itemView.getContext())
                            .load(R.drawable.fill)
                            .into(holder.love);
                }
                else
                {
                    movieHelper.delete(contents.get(i).getId_movie());
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
