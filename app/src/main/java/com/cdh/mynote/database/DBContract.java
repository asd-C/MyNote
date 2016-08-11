package com.cdh.mynote.database;

import android.provider.BaseColumns;

/**
 * Created by cc on 16-8-10.
 */
public final class DBContract {

    public static abstract class Entry implements BaseColumns {
        public static final String TABLE_NAME = "stuff";
        public static final String COLUMN_INFO= "stuffinfo";
        public static final String COLUMN_DATE= "stuffdate";
    }
}
