package com.example.umeta.prototype.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yuuki on 2017/08/29.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME= "ITEM.db";

    public static final String TABLE_ITEM = "item";
    public static final String COLUMN_ITEM_ID = "item_id";
    public static final String COLUMN_ITEM_CATEGORY = "item_category";
    public static final String COLUMN_ITEM_COLOR = "item_color";
    public static final String COLUMN_ITEM_SIZE = "item_size";
    public static final String COLUMN_ITEM_BRAND = "item_brand";
    public static final String COLUMN_ITEM_PURCHASE_DATE = "item_purchase_datE";
    public static final String COLUMN_ITEM_PRICE = "item_price";
    public static final String COLUMN_ITEM_LAST_USE_DATE = "item_last_use_date";
    public static final String COLUMN_ITEM_FREQUENCY = "item_frequency";

    public static final String TABLE_COORDINATE = "COORDINATE";
    public static final String COLUMN_COORDINATE_ID = "coordinate_id";
    public static final String COLUMN_COORDINATE_ITEM_NUMBER = "coordinate_item_number";
    public static final String COLUMN_COORDINATE_ITEM_ID_1 = "coordinate_item_1";
    public static final String COLUMN_COORDINATE_ITEM_ID_2 = "coordinate_item_2";
    public static final String COLUMN_COORDINATE_ITEM_ID_3 = "coordinate_item_3";
    public static final String COLUMN_COORDINATE_ITEM_ID_4 = "coordinate_item_4";
    public static final String COLUMN_COORDINATE_ITEM_ID_5 = "coordinate_item_5";
    public static final String COLUMN_COORDINATE_ITEM_ID_6 = "coordinate_item_6";
    public static final String COLUMN_COORDINATE_ITEM_ID_7 = "coordinate_item_7";
    public static final String COLUMN_COORDINATE_ITEM_ID_8 = "coordinate_item_8";
    public static final String COLUMN_COORDINATE_ITEM_ID_9 = "coordinate_item_9";

    public DatabaseOpenHelper(Context context){
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();

        try {
            StringBuilder createSql = new StringBuilder();
            createSql.append("create table " + TABLE_ITEM + "(");
            createSql.append(COLUMN_ITEM_ID + " integer primary key autoincrement,");
            createSql.append(COLUMN_ITEM_CATEGORY + " text,");
            createSql.append(COLUMN_ITEM_COLOR + " text,");
            createSql.append(COLUMN_ITEM_SIZE + " text,");
            createSql.append(COLUMN_ITEM_BRAND + " text,");
            createSql.append(COLUMN_ITEM_PURCHASE_DATE + " text,");
            createSql.append(COLUMN_ITEM_PRICE + " text,");
            createSql.append(COLUMN_ITEM_LAST_USE_DATE + " text,");
            createSql.append(COLUMN_ITEM_FREQUENCY + " text");
            createSql.append(")");

            db.execSQL(createSql.toString());
            db.setTransactionSuccessful();

        }finally {
            db.endTransaction();
        }

        try{
            StringBuilder createSql = new StringBuilder();
            createSql.append("create table " + TABLE_COORDINATE + "(");
            createSql.append(COLUMN_COORDINATE_ID + " integer primary key autoincrement,");
            createSql.append(COLUMN_COORDINATE_ITEM_NUMBER + " text,");
            createSql.append(COLUMN_COORDINATE_ITEM_ID_1 + " text,");
            createSql.append(COLUMN_COORDINATE_ITEM_ID_2 + " text,");
            createSql.append(COLUMN_COORDINATE_ITEM_ID_3 + " text,");
            createSql.append(COLUMN_COORDINATE_ITEM_ID_4 + " text,");
            createSql.append(COLUMN_COORDINATE_ITEM_ID_5 + " text,");
            createSql.append(COLUMN_COORDINATE_ITEM_ID_6 + " text,");
            createSql.append(COLUMN_COORDINATE_ITEM_ID_7 + " text,");
            createSql.append(COLUMN_COORDINATE_ITEM_ID_8 + " text,");
            createSql.append(COLUMN_COORDINATE_ITEM_ID_9 + " text");
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
