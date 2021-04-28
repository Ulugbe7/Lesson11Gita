package com.example.lesson11gita;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "list.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "LIST";
    public static final String ID = "_ID";
    public static final String NAME = "NAME";
    public static final String PHONE = "PHONE";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String execuse = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT, " + PHONE + " TEXT)";
        db.execSQL(execuse);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public long addData(String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(PHONE, phone);

        long res = db.insert(TABLE_NAME, null, cv);
        db.close();
        return res;
    }

    public List<Data> getData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        List<Data> list = new LinkedList<>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                list.add(new Data(cursor.getInt(cursor.getColumnIndex(ID)),
                        cursor.getString(cursor.getColumnIndex(NAME)),
                        cursor.getString(cursor.getColumnIndex(PHONE))));
            } while (cursor.moveToNext());
        }
        db.close();
        return list;
    }

    public boolean deleteOne(Data data) {
        SQLiteDatabase db = this.getWritableDatabase();
        // "DELETE FROM " + TABLE_NAME + " WHERE _id = '" + mId + "'"
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + ID + " = " + data.getId();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            return true;
        } else return false;
    }

    public boolean update(Data data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ID, data.getId());
        cv.put(NAME, data.getName());
        cv.put(PHONE, data.getPhone());

        boolean isUpdate = db.update(TABLE_NAME, cv, ID + " = " + data.getId(), null) > 0;
        db.close();

        return isUpdate;
    }


}
