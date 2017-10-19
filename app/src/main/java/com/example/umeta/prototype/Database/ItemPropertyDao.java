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

    public ItemPropertyDao(Context context){
        helper = new DatabaseOpenHelper(context);
    }

    public ItemProperty save(ItemProperty itemProperty){
        SQLiteDatabase db = helper.getWritableDatabase();
        ItemProperty result = null;
        try{
            ContentValues values = new ContentValues();
            values.put(ItemProperty.COLUMN_PARTS_NAME, itemProperty.getPartsName());
            values.put(ItemProperty.COLUMN_PARTS_COLOR, itemProperty.getPartsColor());
            values.put(ItemProperty.COLUMN_PARTS_SIZE, itemProperty.getPartsSize());
            values.put(ItemProperty.COLUMN_PARTS_BRAND, itemProperty.getPartsBrand());
            values.put(ItemProperty.COLUMN_PARTS_PURCHASE_DATA, itemProperty.getPartsPurchaseDate());
            values.put(ItemProperty.COLUMN_PARTS_PRICE, itemProperty.getPartsPrice());
            values.put(ItemProperty.COLUMN_PARTS_LAST_USE_DATE, itemProperty.getPartsLastUseDate());
            values.put(ItemProperty.COLUMN_PARTS_FREQUENCY, itemProperty.getPartsFrequency());

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
        itemProperty.setPartsColor(cursor.getString(2));
        itemProperty.setPartsSize(cursor.getString(3));
        itemProperty.setPartsBrand(cursor.getString(4));
        itemProperty.setPartsPurchaseDate(cursor.getString(5));
        itemProperty.setPartsPrice(cursor.getString(6));
        itemProperty.setPartsLastUseDate(cursor.getString(7));
        itemProperty.setPartsFrequency(cursor.getString(8));

        return itemProperty;
    }
}
