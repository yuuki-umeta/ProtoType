package com.example.umeta.prototype.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuuki on 2017/08/30.
 */

public class ItemPropertyDao {

    private DatabaseOpenHelper helper = null;
/*
    private static final String TABLE_NAME = "ItemProperty";
    private static final String COLUMN_ID = "rowid";
    private static final String COLUMN_DATA = "data";
    private static final String[] COLUMNS = {COLUMN_ID, COLUMN_DATA};
*/
    public ItemPropertyDao(Context context){
        helper = new DatabaseOpenHelper(context);
    }

    public ItemProperty save(ItemProperty itemProperty){
        SQLiteDatabase db = helper.getWritableDatabase();
        ItemProperty result = null;
        try{
            ContentValues values = new ContentValues();
            values.put(ItemProperty.COLUMN_PARTS_NAME, itemProperty.getPartsName());
//            values.put(ItemProperty.COLUMN_ITEM_NUMBER, itemProperty.getItemNumber());

            Long rowId = itemProperty.getRowId();
            if(rowId == null) {
                rowId = db.insert(ItemProperty.TABLE_NAME, null, values);
            }
            else{
                db.update(ItemProperty.TABLE_NAME, values, ItemProperty.COLUMN_ID + "=?", new String[]{String.valueOf(rowId)});
            }
            result = load(rowId);
        }finally {
            db.close();
        }
        return result;
    }

    public void delete(ItemProperty itemProperty){
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            db.delete(ItemProperty.TABLE_NAME, ItemProperty.COLUMN_ID + "=?", new String[]{String.valueOf(itemProperty.getRowId())});
        }finally {
            db.close();
        }
    }

    public ItemProperty load(Long rowId){
        SQLiteDatabase db = helper.getReadableDatabase();

        ItemProperty itemProperty = null;
        try{
            Cursor cursor = db.query(ItemProperty.TABLE_NAME, null, ItemProperty.COLUMN_ID + "=?", new String[]{String.valueOf(rowId)}, null, null, null);
            cursor.moveToFirst();
            itemProperty = getItemProperty(cursor);
        }finally {
            db.close();
        }
        return itemProperty;
    }


    public List<ItemProperty> list(){
        SQLiteDatabase db = helper.getReadableDatabase();

        List<ItemProperty> itemPropertyList;
        try{
            Cursor cursor = db.query(ItemProperty.TABLE_NAME, null, null, null, null, null, ItemProperty.COLUMN_ID);
            itemPropertyList = new ArrayList<ItemProperty>();
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                itemPropertyList.add(getItemProperty(cursor));
                cursor.moveToNext();
            }
        }finally {
            db.close();
        }
        return itemPropertyList;
    }

    private ItemProperty getItemProperty(Cursor cursor){
        ItemProperty itemProperty = new ItemProperty();

        itemProperty.setRowId(cursor.getLong(0));
        itemProperty.setPartsName(cursor.getString(1));
        return itemProperty;
    }
}
