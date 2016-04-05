package edu.lclark.homework6.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static edu.lclark.homework6.SQLite.Pins.COL_DESCRIPTION;
import static edu.lclark.homework6.SQLite.Pins.COL_LAT;
import static edu.lclark.homework6.SQLite.Pins.COL_LONG;
import static edu.lclark.homework6.SQLite.Pins.COL_TITLE;
import static edu.lclark.homework6.SQLite.Pins.CREATE_TABLE;
import static edu.lclark.homework6.SQLite.Pins.TABLE_NAME;
import static edu.lclark.homework6.SQLite.Pins._ID;

/**
 * Created by maiaphoebedylansamerjan on 3/31/16.
 */
public class MapSQLiteHelper extends SQLiteOpenHelper{
    public static final String DB_NAME = "user.db";
    private static final String TAG="UpdateUser";
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
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(User.CREATE_TABLE);

        db.execSQL(CREATE_TABLE);

        //initialize(db);

    }

    /*public void initialize(SQLiteDatabase database) {

        database.beginTransaction();

        ArrayList<Pins> pins = Pins.getAllPins();
        for (Pins pins1 : pins) {
            database.insert(Pins.TABLE_NAME, null, pins1.getContentValues());
        }
        database.setTransactionSuccessful();
        database.endTransaction();
    }*/


    public void addPin(Pins pin) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try {

            long userId = addOrUpdateUser(pin.getUser());

           ContentValues values= pin.getPinValues();


            db.insertOrThrow(TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add post to database");
        } finally {
            db.endTransaction();
        }
    }

    public long addOrUpdateUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        long userId = -1;

        db.beginTransaction();
        try {
            ContentValues values = user.getUserValues();

            int rows = db.update(User.TABLE_NAME, values, User.COL_USER + "= ?", new String[]{user.getUser()});

            if (rows == 1) {
                String usersSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
                        User._ID, User.TABLE_NAME, User.COL_USER);
                Cursor cursor = db.rawQuery(usersSelectQuery, new String[]{String.valueOf(user.getUser())});
                try {
                    if (cursor.moveToFirst()) {
                        userId = cursor.getInt(0);
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            } else {
                userId = db.insertOrThrow(User.TABLE_NAME, null, values);
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add or update user");
        } finally {
            db.endTransaction();
        }
        return userId;
    }

    public List<Pins> getAllPins(int ID) {
        List<Pins> pins = new ArrayList<>();

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME + "WHERE " + Pins._ID + " = ?", new String[]{String.valueOf(ID)});

        if (cursor.moveToFirst()) {

            do {
                int id = getCursorInt(cursor, _ID);
                String title = getCursorString(cursor, COL_TITLE);
                String description = getCursorString(cursor, COL_DESCRIPTION);
                int latitude=getCursorInt(cursor, COL_LAT);
                int longitude=getCursorInt(cursor,COL_LONG);
                pins.add(new Pins(latitude, longitude,id,title,description));
            } while (cursor.moveToNext());

        }

        cursor.close();
        return pins;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();


        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + User.TABLE_NAME, null);

        if (cursor.moveToFirst()) {

            do {
                int id = getCursorInt(cursor, User._ID);
                String name = getCursorString(cursor, User.COL_USER);
                int numPins = getCursorInt(cursor, User.COL_PINS);
                users.add(new User(id, name, numPins));
            } while (cursor.moveToNext());

        }

        cursor.close();
        return users;
    }

    public User checkUser(String user) {

        Cursor cursor = getReadableDatabase().rawQuery("SELECT " + User.COL_USER + " FROM " + User.TABLE_NAME + " WHERE " + User.COL_USER + " = '" + user + "'", new String[]{String.valueOf(user)});

        int id = getCursorInt(cursor, User._ID);
        String  foundUser =(getCursorString(cursor, User.COL_USER));
        cursor.close();
        return new User(foundUser,id);
    }

    public void getPinsForUsers(){
        String sql="SELECT"+User.COL_USER+", " + TABLE_NAME + ".* " +
                "FROM " + TABLE_NAME + " INNER JOIN " + User.TABLE_NAME +
                " ON " + TABLE_NAME + "." + COL_LAT + " LIKE " + User.TABLE_NAME + "." + User.COL_PINS;

        Log.d("getPinsForUsers", sql);
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        while (cursor.moveToNext()) {
            String builder = getCursorString(cursor, User.COL_PINS) +
                    " goes to " +
                    getCursorString(cursor, COL_LAT) +
                    " : " +
                    getCursorString(cursor, COL_LONG);
            Log.d("getPinsForUsers", builder);
        }

        cursor.close();

    }


    public void insertUser(User users) throws SQLiteConstraintException {
        getWritableDatabase().insert(User.TABLE_NAME, null, users.getUserValues());
    }

    public void insertPin(Pins pin) {
        getWritableDatabase().insert(Pins.TABLE_NAME, null, pin.getPinValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + User.TABLE_NAME);
        db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
    }

    public String getCursorString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public int getCursorInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }
}
