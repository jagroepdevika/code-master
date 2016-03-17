package com.devika.android.e_order.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.devika.android.e_order.entity.Bestelling;
import com.devika.android.e_order.entity.User;

/**
 * Created by devika on 3/15/2016.
 */
public class OrderDAO extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Order.db";
    public static final int DATABASE_VERSION = 1;

    public static final String ID = "id";
    public static final String USER_TABLE = "user";
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";

    public static final String BESTELLING_TABLE = "bestelling";
    public static final String BESTELLING_USERNAME = "username";
    public static final String BESTELLING_DRANK = "bestelling_drank";
    public static final String BESTELLING_ETEN = "bestelling_eten";

    private static final String SQL_USER_TABLE_QUERY = "create table user(id INTEGER PRIMARY KEY, username TEXT, password TEXT);";
    private static final String SQL_BESTELLING_TABLE_QUERY = "create table bestelling(id INTEGER PRIMARY KEY,username TEXT, bestelling_drank TEXT, bestelling_eten TEXT);";


    public OrderDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        defaultInsertUser();
        defaultInsertBest();
    }

    public void defaultInsertUser() {

        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_USERNAME, "order");
        contentValues.put(USER_PASSWORD, "order");
        insertOneRecord(USER_TABLE, contentValues);

        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(USER_USERNAME, "devika");
        contentValues2.put(USER_PASSWORD, "devika");
        insertOneRecord(USER_TABLE, contentValues2);

        ContentValues contentValues3 = new ContentValues();
        contentValues3.put(USER_USERNAME, "yoon");
        contentValues3.put(USER_PASSWORD, "yoon");
        insertOneRecord(USER_TABLE, contentValues3);
    }

    public void defaultInsertBest() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BESTELLING_USERNAME, "order");
        contentValues.put(BESTELLING_DRANK, "chips");
        contentValues.put(BESTELLING_ETEN, "water");
        insertBestelling(BESTELLING_TABLE, contentValues);
    }

    public long insertOneRecord(String userTable, ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();
        long rowId = db.insert(USER_TABLE, null, contentValues);
        db.close();
        return rowId;
    }

    public long insertBestelling(String table, ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();
        long rowId = db.insert(BESTELLING_TABLE, null, contentValues);
        db.close();
        return rowId;
    }

    public User login(String username, String password) {
        User login = null;
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("select * from %s where %s = '%s' AND %s = '%s';", USER_TABLE, USER_USERNAME, username, USER_PASSWORD, password);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            login = new User(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
        }
        db.close();
        return login;
    }

    public Bestelling findBest(String username) {
        Bestelling bestelling = null;
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("select * from %s where %s = '%s';", BESTELLING_TABLE, BESTELLING_USERNAME, username);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            bestelling = new Bestelling(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }
        db.close();
        return bestelling;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_USER_TABLE_QUERY);
        db.execSQL(SQL_BESTELLING_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
