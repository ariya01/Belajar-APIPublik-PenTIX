package com.pentil.pen_tix.DatabseMovie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.pentil.pen_tix.DatabseMovie.DatabaseContract1.MovieColums.ADULT;
import static com.pentil.pen_tix.DatabseMovie.DatabaseContract1.MovieColums.GENRE;
import static com.pentil.pen_tix.DatabseMovie.DatabaseContract1.MovieColums.ID_MOVIE;
import static com.pentil.pen_tix.DatabseMovie.DatabaseContract1.MovieColums.MOVIE;
import static com.pentil.pen_tix.DatabseMovie.DatabaseContract1.MovieColums.POSTER;
import static com.pentil.pen_tix.DatabseMovie.DatabaseContract1.MovieColums.RELEASE;
import static com.pentil.pen_tix.DatabseMovie.DatabaseContract1.MovieColums.TITLE;
import static com.pentil.pen_tix.DatabseMovie.DatabaseContract1.TABLE_MOVIE;

public class MovieHelper {
    private static String DATABASE_TABLE = TABLE_MOVIE;
    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    public MovieHelper(Context context){
        this.context = context;
    }

    public MovieHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dataBaseHelper.close();
    }

    public ArrayList<Film> query(){
        ArrayList<Film> arrayList = new ArrayList<Film>();
        Cursor cursor = database.query(DATABASE_TABLE,null,null,null,null,null,_ID +" DESC",null);
        cursor.moveToFirst();
        Film film;
        if (cursor.getCount()>0) {
            do {

                film = new Film();
                film.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                film.setMovie(cursor.getString(cursor.getColumnIndexOrThrow(MOVIE)));
                film.setMovie(cursor.getString(cursor.getColumnIndexOrThrow(ID_MOVIE)));
                film.setGenre(cursor.getString(cursor.getColumnIndexOrThrow(GENRE)));
                film.setAdult(cursor.getString(cursor.getColumnIndexOrThrow(ADULT)));
                film.setRelease(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE)));
                film.setPoseter(cursor.getString(cursor.getColumnIndexOrThrow(POSTER)));
                film.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                arrayList.add(film);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public Film find(String id)
    {
        Film film;
        Cursor cursor = database.query(DATABASE_TABLE,null,ID_MOVIE +"="+id,null,null,null,_ID +" DESC",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            do {
                film = new Film();
                film.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                film.setMovie(cursor.getString(cursor.getColumnIndexOrThrow(MOVIE)));
                film.setId_movie(cursor.getString(cursor.getColumnIndexOrThrow(ID_MOVIE)));
                film.setGenre(cursor.getString(cursor.getColumnIndexOrThrow(GENRE)));
                film.setAdult(cursor.getString(cursor.getColumnIndexOrThrow(ADULT)));
                film.setRelease(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE)));
                film.setPoseter(cursor.getString(cursor.getColumnIndexOrThrow(POSTER)));
                film.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        else
        {
            film=null;
        }
        return film;
    }


    public long insert(Film film){
        ContentValues initialValues =  new ContentValues();
        initialValues.put(MOVIE, film.getMovie());
        initialValues.put(ID_MOVIE, film.getId_movie());
        initialValues.put(GENRE, film.getGenre());
        initialValues.put(ADULT, film.getAdult());
        initialValues.put(RELEASE, film.getRelease());
        initialValues.put(POSTER, film.getPoseter());
        initialValues.put(TITLE, film.getTitle());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public int delete(String id){
        return database.delete(TABLE_MOVIE, ID_MOVIE + " = '"+id+"'", null);
    }
}
