package com.pentil.pen_tix.Activity.MainActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pentil.pen_tix.Object.Movie;
import com.pentil.pen_tix.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    List<Movie> movies;
    Context context;
    public Adapter(List<Movie> movies, Context context)
    {
        this.context = context;
        this.movies = movies;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.item_card, viewGroup, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Glide.with(holder.itemView.getContext())
                .load(movies.get(i).getPoster())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView image;
        public Holder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
