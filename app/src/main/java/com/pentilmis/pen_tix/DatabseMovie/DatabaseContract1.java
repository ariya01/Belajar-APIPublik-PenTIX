package com.pentilmis.pen_tix.DatabseMovie;

import android.provider.BaseColumns;

public class DatabaseContract1 {
    static String TABLE_MOVIE = "movie";
    static final class MovieColums implements BaseColumns {
        static String MOVIE = "movie";
        static String ID_MOVIE = "id_movie";
        static String GENRE = "genre_movie";
        static String ADULT = "adult";
        static String RELEASE = "relase";
        static String POSTER = "poster";
        static String TITLE = "title";
    }
}
