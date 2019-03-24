package com.pentil.pen_tix.DatabaseGenre;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.pentil.pen_tix.DatabaseGenre.DatabaseContract.MovieColumns.GENRE;
import static com.pentil.pen_tix.DatabaseGenre.DatabaseContract.MovieColumns.ID_GENRE;
import static com.pentil.pen_tix.DatabaseGenre.DatabaseContract.TABLE_GENRE;

public class GenreHepler {
    private static String DATABASE_TABLE = TABLE_GENRE;
    private Context context;
    private DatabaHelper dataBaseHelper;

    private SQLiteDatabase database;

    public GenreHepler(Context context){
        this.context = context;
    }

    public GenreHepler open() throws SQLException {
        dataBaseHelper = new DatabaHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dataBaseHelper.close();
    }

    public ArrayList<Genre> query(){
        ArrayList<Genre> arrayList = new ArrayList<Genre>();
        Cursor cursor = database.query(DATABASE_TABLE,null,null,null,null,null,_ID +" DESC",null);
        cursor.moveToFirst();
        Genre genre;
        if (cursor.getCount()>0) {
            do {

                genre = new Genre();
                genre.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                genre.setGenre(cursor.getString(cursor.getColumnIndexOrThrow(GENRE)));
                genre.setId_genre(cursor.getString(cursor.getColumnIndexOrThrow(ID_GENRE)));
                arrayList.add(genre);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public Genre find(String id)
    {
        Genre genre;
        Cursor cursor = database.query(DATABASE_TABLE,null,ID_GENRE +"="+id,null,null,null,_ID +" DESC",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            do {
                genre = new Genre();
                genre.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                genre.setGenre(cursor.getString(cursor.getColumnIndexOrThrow(GENRE)));
                genre.setId_genre(cursor.getString(cursor.getColumnIndexOrThrow(ID_GENRE)));
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        else
        {
            genre=null;
        }
        return genre;
    }


    public long insert(Genre genre){
        ContentValues initialValues =  new ContentValues();
        initialValues.put(GENRE, genre.getGenre());
        initialValues.put(ID_GENRE, genre.getId_genre());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public int delete(String id){
        return database.delete(TABLE_GENRE, _ID + " = '"+id+"'", null);
    }
}
