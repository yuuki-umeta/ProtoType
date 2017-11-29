package com.example.umeta.prototype.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.SearchEvent;

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
            ContentValues values = new ContentValues(9);
            values.put(ItemProperty.COLUMN_ITEM_CATEGORY, itemProperty.getItemCategory());
            values.put(ItemProperty.COLUMN_ITEM_COLOR, itemProperty.getItemColor());
            values.put(ItemProperty.COLUMN_ITEM_SIZE, itemProperty.getItemSize());
            values.put(ItemProperty.COLUMN_ITEM_BRAND, itemProperty.getItemBrand());
            values.put(ItemProperty.COLUMN_ITEM_PURCHASE_DATE, itemProperty.getItemPurchaseDate());
            values.put(ItemProperty.COLUMN_ITEM_PRICE, itemProperty.getItemPrice());
            values.put(ItemProperty.COLUMN_ITEM_LAST_USE_DATE, itemProperty.getItemLastUseDate());
            values.put(ItemProperty.COLUMN_ITEM_FREQUENCY, itemProperty.getItemFrequency());

            Long itemId = itemProperty.getItemId();
            if(itemId == null) {
                itemId = db.insert(ItemProperty.TABLE_NAME, null, values);
            }
            else{
                db.update(ItemProperty.TABLE_NAME, values, ItemProperty.COLUMN_ITEM_ID + "=?", new String[]{String.valueOf(itemId)});
            }
            result = load(itemId);
        }finally {
            db.close();
        }
        return result;
    }

    public void delete(ItemProperty itemProperty){
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            db.delete(ItemProperty.TABLE_NAME, ItemProperty.COLUMN_ITEM_ID + "=?", new String[]{String.valueOf(itemProperty.getItemId())});
        }finally {
            db.close();
        }
    }

    public ItemProperty load(Long itemId){
        SQLiteDatabase db = helper.getReadableDatabase();

        ItemProperty itemProperty = null;
        try{
            Cursor cursor = db.query(ItemProperty.TABLE_NAME, null, ItemProperty.COLUMN_ITEM_ID + "=?", new String[]{String.valueOf(itemId)}, null, null, null);
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
            Cursor cursor = db.query(ItemProperty.TABLE_NAME, null, null, null, null, null, ItemProperty.COLUMN_ITEM_ID);
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

    public List<ItemProperty> Search(String column[]){
        SQLiteDatabase db = helper.getReadableDatabase();

        int args = 0;
        for (int i = 0; i < column.length; i++)
            if (column[i] != null)
                args++;

        String selection = "";
        String selectionArgs[] = new String[args];
        args = 0;
        for(int i = 0; i < column.length; i++){
            if(column[i] != null){
                selectionArgs[args] = column[i];
                args++;
                if(selection != ""){
                    selection = selection + " AND ";
                }
                selection = selection + ItemProperty.COLUMNS[i] + " =" + " ?";
            }
        }

        List<ItemProperty> itemPropertyList;
        try {
            Cursor cursor = db.query(ItemProperty.TABLE_NAME, null, selection, selectionArgs, null, null, ItemProperty.COLUMN_ITEM_ID);
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

        itemProperty.setItemId(cursor.getLong(0));
        itemProperty.setItemCategory(cursor.getString(1));
        itemProperty.setItemColor(cursor.getString(2));
        itemProperty.setItemSize(cursor.getString(3));
        itemProperty.setItemBrand(cursor.getString(4));
        itemProperty.setItemPurchaseDate(cursor.getString(5));
        itemProperty.setItemPrice(cursor.getString(6));
        itemProperty.setItemLastUseDate(cursor.getString(7));
        itemProperty.setItemFrequency(cursor.getString(8));

        return itemProperty;
    }
}
