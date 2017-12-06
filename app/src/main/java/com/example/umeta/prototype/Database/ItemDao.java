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

public class ItemDao {

    private DatabaseOpenHelper helper = null;

    public ItemDao(Context context){
        helper = new DatabaseOpenHelper(context);
    }

    public Item save(Item item){
        SQLiteDatabase db = helper.getWritableDatabase();
        Item result = null;
        try{
            ContentValues values = new ContentValues(9);
            values.put(Item.COLUMN_ITEM_CATEGORY, item.getItemCategory());
            values.put(Item.COLUMN_ITEM_COLOR, item.getItemColor());
            values.put(Item.COLUMN_ITEM_SIZE, item.getItemSize());
            values.put(Item.COLUMN_ITEM_BRAND, item.getItemBrand());
            values.put(Item.COLUMN_ITEM_PURCHASE_DATE, item.getItemPurchaseDate());
            values.put(Item.COLUMN_ITEM_PRICE, item.getItemPrice());
            values.put(Item.COLUMN_ITEM_LAST_USE_DATE, item.getItemLastUseDate());
            values.put(Item.COLUMN_ITEM_FREQUENCY, item.getItemFrequency());

            Long itemId = item.getItemId();
            if(itemId == null) {
                itemId = db.insert(Item.TABLE_ITEM, null, values);
            }
            else{
                db.update(Item.TABLE_ITEM, values, Item.COLUMN_ITEM_ID + "=?", new String[]{String.valueOf(itemId)});
            }
            result = load(itemId);
        }finally {
            db.close();
        }
        return result;
    }

    public void delete(Item item){
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            db.delete(Item.TABLE_ITEM, Item.COLUMN_ITEM_ID + "=?", new String[]{String.valueOf(item.getItemId())});
        }finally {
            db.close();
        }
    }

    public Item load(Long itemId){
        SQLiteDatabase db = helper.getReadableDatabase();

        Item item = null;
        try{
            Cursor cursor = db.query(Item.TABLE_ITEM, null, Item.COLUMN_ITEM_ID + "=?", new String[]{String.valueOf(itemId)}, null, null, null);
            cursor.moveToFirst();
            item = getItemProperty(cursor);
        }finally {
            db.close();
        }
        return item;
    }


    public List<Item> list(){
        SQLiteDatabase db = helper.getReadableDatabase();

        List<Item> itemList;
        try{
            Cursor cursor = db.query(Item.TABLE_ITEM, null, null, null, null, null, Item.COLUMN_ITEM_ID);
            itemList = new ArrayList<Item>();
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                itemList.add(getItemProperty(cursor));
                cursor.moveToNext();
            }
        }finally {
            db.close();
        }
        return itemList;
    }

    public List<Item> search(String column[]){
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
                selection = selection + Item.COLUMNS[i] + " =" + " ?";
            }
        }

        List<Item> itemList;
        try {
            Cursor cursor = db.query(Item.TABLE_ITEM, null, selection, selectionArgs, null, null, Item.COLUMN_ITEM_ID);
            itemList = new ArrayList<Item>();
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                itemList.add(getItemProperty(cursor));
                cursor.moveToNext();
            }
        }finally {
            db.close();
        }
        return itemList;
    }

    private List<Item> makeList(List<Item> itemList, Long rowId){
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(Item.TABLE_ITEM, null,  Item.COLUMN_ITEM_ID + "=?", new String[]{String.valueOf(rowId)}, null, null, null);
        itemList.add(getItemProperty(cursor));
        return itemList;
    }

    private Item getItemProperty(Cursor cursor){
        Item item = new Item();

        item.setItemId(cursor.getLong(0));
        item.setItemCategory(cursor.getString(1));
        item.setItemColor(cursor.getString(2));
        item.setItemSize(cursor.getString(3));
        item.setItemBrand(cursor.getString(4));
        item.setItemPurchaseDate(cursor.getString(5));
        item.setItemPrice(cursor.getString(6));
        item.setItemLastUseDate(cursor.getString(7));
        item.setItemFrequency(cursor.getString(8));

        return item;
    }
}
