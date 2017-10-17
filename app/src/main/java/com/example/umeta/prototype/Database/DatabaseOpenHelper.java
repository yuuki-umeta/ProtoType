package com.example.umeta.prototype.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yuuki on 2017/08/29.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ITEM_PROPERTY.db";

    public static final String TABLE_NAME = "item_property";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PARTS_NAME = "parts_name";

    public DatabaseOpenHelper(Context context){
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();

        try {
            StringBuilder createSql = new StringBuilder();
            createSql.append("create table " + TABLE_NAME + "(");
            createSql.append(COLUMN_ID + " integer primary key autoincrement,");
            createSql.append(COLUMN_PARTS_NAME + " text");
            //createSql.append(ItemProperty.COLUMN_ITEM_NUMBER + "text");
            createSql.append(")");

            db.execSQL(createSql.toString());
            db.setTransactionSuccessful();

        }finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}