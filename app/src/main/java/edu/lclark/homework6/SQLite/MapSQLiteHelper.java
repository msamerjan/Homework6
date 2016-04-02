package edu.lclark.homework6.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by maiaphoebedylansamerjan on 3/31/16.
 */
public class MapSQLiteHelper extends SQLiteOpenHelper{
    public static final String DB_NAME = "user.db";
    private static MapSQLiteHelper sInstance;

    private MapSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static MapSQLiteHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MapSQLiteHelper(context.getApplicationContext(), DB_NAME, null, 1);
        }

        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + User.TABLE_NAME + " ( " +
                        User._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        User. + " TEXT, " +
                        Student.COL_YEAR + " TEXT, " +
                        Student.COL_NET_WORTH + " BIGINT )"
        );

        db.execSQL(CSClass.CREATE_TABLE);

        initialize(db);

    }

}
