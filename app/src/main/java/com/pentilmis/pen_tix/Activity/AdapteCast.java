package com.pentilmis.pen_tix.Activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.pentilmis.pen_tix.R;
import com.pentilmis.pen_tix.Return.Detail.Cast;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapteCast extends RecyclerView.Adapter<AdapteCast.Holder> {
    List<Cast> casts;
    Context context;
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.item_pic, viewGroup, false);
        return new AdapteCast.Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Log.d("cek123", "onBindViewHolder: "+casts.get(i).getPath());
        if (casts.get(i).getPath()!=null)
        {
            Glide.with(holder.itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500"+casts.get(i).getPath())
                    .into(holder.imageView);
        }
        else
        {
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.place_holder)
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return casts.size();
    }

    public AdapteCast(Context context, List<Cast> casts) {
        this.casts = casts;
        this.context = context;
    }

    public class Holder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.gambar);
        }
    }
}
