package com.pentil.pen_tix.DatabaseGenre;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_GENRE = "genre";
    static final class MovieColumns implements BaseColumns {
        static String GENRE = "genre";
        static String ID_GENRE = "id_genre";
    }
}
