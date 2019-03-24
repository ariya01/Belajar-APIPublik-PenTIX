package com.pentil.pen_tix.DatabseMovie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.pentil.pen_tix.DatabseMovie.DatabaseContract1.TABLE_MOVIE;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbmovie";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            TABLE_MOVIE,
            DatabaseContract1.MovieColums._ID,
            DatabaseContract1.MovieColums.MOVIE,
            DatabaseContract1.MovieColums.ID_MOVIE,
            DatabaseContract1.MovieColums.GENRE,
            DatabaseContract1.MovieColums.ADULT,
            DatabaseContract1.MovieColums.RELEASE,
            DatabaseContract1.MovieColums.POSTER,
            DatabaseContract1.MovieColums.TITLE
    );
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract1.TABLE_MOVIE);
        onCreate(db);
    }
}
