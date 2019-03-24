package com.pentilmis.pen_tix.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pentilmis.pen_tix.Activity.AdapterSoon;
import com.pentilmis.pen_tix.BuildConfig;
import com.pentilmis.pen_tix.R;
import com.pentilmis.pen_tix.Retrofit.Client;
import com.pentilmis.pen_tix.Retrofit.Server;
import com.pentilmis.pen_tix.Return.Soon.ReturnSoon;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class ComingSoonFragment extends Fragment {

    RecyclerView recyclerView;
    String modifiedDate;
    AdapterSoon adapterSoon;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    public ComingSoonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coming_soon, container, false);
        recyclerView = view.findViewById(R.id.rc_soon);
        Date date = new Date();
        modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
//        Log.d("cek tanggal", "onCreateView: "+modifiedDate);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
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
//                new Load().execute();
                loadadata();
            }
        }, 500);

    }

    private void loadadata() {
        Client api = Server.builder().create(Client.class);
        compositeDisposable.add(api.soon(BuildConfig.TMDB_API_KEY,modifiedDate).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ReturnSoon>() {
            @Override
            public void accept(ReturnSoon returnSoon) throws Exception {
                adapterSoon = new AdapterSoon(getContext(),returnSoon.getContents());
                recyclerView.setAdapter(adapterSoon);
            }
        }));
    }

//    public class Load extends AsyncTask<Void, Integer, Void> {
//        @Override
//        protected void onPreExecute() {
//
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//
//
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            Client api = Server.builder().create(Client.class);
//            Call<ReturnSoon> cari = api.soon(BuildConfig.TMDB_API_KEY,modifiedDate);
//            cari.enqueue(new Callback<ReturnSoon>() {
//                @Override
//                public void onResponse(Call<ReturnSoon> call, Response<ReturnSoon> response) {
////                    Log.d("cek satu", "onResponse: "+response.body().getContents().get(1).getTitle());
//                    adapterSoon = new AdapterSoon(getContext(),response.body().getContents());
//                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//                    recyclerView.setLayoutManager(mLayoutManager);
//                    recyclerView.setItemAnimator(new DefaultItemAnimator());
//                    recyclerView.setAdapter(adapterSoon);
//                }
//
//                @Override
//                public void onFailure(Call<ReturnSoon> call, Throwable t) {
//
//                }
//            });
//
//            return null;
//        }
//    }

}
