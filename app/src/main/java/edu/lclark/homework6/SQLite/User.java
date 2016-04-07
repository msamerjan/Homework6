package edu.lclark.homework6.SQLite;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by maiaphoebedylansamerjan on 3/31/16.
 */
public class User implements BaseColumns,Parcelable {
    private String mUserName;
    private int mPins, mID;

    public static final String TABLE_NAME="users";
    public static final String COL_USER="username";
    public static final String COL_PINS="number of pins";

    public static final String CREATE_TABLE="CREATE TABLE " + User.TABLE_NAME + "(" +
            User._ID + " TEXT PRIMARY KEY, " +
            User.COL_USER + " TEXT)";

    private ArrayList<User> mUsers;


    public User(String user){
        mUserName=user;
    }

    public User(int id,String user){
        mID=id;
        mUserName=user;
    }


    protected User(Parcel in) {
        mUserName = in.readString();
        mID = in.readInt();
        mUsers = in.createTypedArrayList(User.CREATOR);
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mUserName);
        dest.writeInt(mID);
        dest.writeTypedList(mUsers);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUser(){
        return mUserName;
    }

    public int getPins(){
        return mPins;
    }

    public int getmID(){return mID;}


    public ContentValues getUserValues(){
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_USER,mUserName);
        //contentValues.put(COL_PINS,mPins);
        return contentValues;
    }

}
