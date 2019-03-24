package com.pentil.pen_tix.DatabaseGenre;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.pentil.pen_tix.DatabaseGenre.DatabaseContract.TABLE_GENRE;

public class DatabaHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbgenre";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            TABLE_GENRE,
            DatabaseContract.MovieColumns._ID,
            DatabaseContract.MovieColumns.GENRE,
            DatabaseContract.MovieColumns.ID_GENRE
    );
    public DatabaHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_GENRE);
        onCreate(db);
    }
}
