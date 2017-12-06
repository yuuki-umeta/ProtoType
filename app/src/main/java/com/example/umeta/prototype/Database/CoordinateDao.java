package com.example.umeta.prototype.Database;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.SearchEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuuki on 2017/12/04.
 */

public class CoordinateDao {
    private DatabaseOpenHelper helper = null;

    public CoordinateDao(Context context){
        helper = new DatabaseOpenHelper(context);
    }

    public Coordinate save(Coordinate coordinate){
        SQLiteDatabase db = helper.getWritableDatabase();
        Coordinate result = null;
        try{
            ContentValues values = new ContentValues(12);
            values.put(Coordinate.COLUMN_COORDINATE_ITEM_NUMBER, coordinate.getItemNumber());
            values.put(Coordinate.COLUMN_COORDINATE_ITEM_ID_1, coordinate.getItem1Id());
            values.put(Coordinate.COLUMN_COORDINATE_ITEM_ID_2, coordinate.getItem2Id());
            values.put(Coordinate.COLUMN_COORDINATE_ITEM_ID_3, coordinate.getItem3Id());
            values.put(Coordinate.COLUMN_COORDINATE_ITEM_ID_4, coordinate.getItem4Id());
            values.put(Coordinate.COLUMN_COORDINATE_ITEM_ID_5, coordinate.getItem5Id());
            values.put(Coordinate.COLUMN_COORDINATE_ITEM_ID_6, coordinate.getItem6Id());
            values.put(Coordinate.COLUMN_COORDINATE_ITEM_ID_7, coordinate.getItem7Id());
            values.put(Coordinate.COLUMN_COORDINATE_ITEM_ID_8, coordinate.getItem8Id());
            values.put(Coordinate.COLUMN_COORDINATE_ITEM_ID_9, coordinate.getItem9Id());
            Long coordinateId = coordinate.getCoordinateId();
            if(coordinateId == null){
                coordinateId = db.insert(Coordinate.TABLE_COORDINATE, null, values);
            }
            else{
                db.update(Coordinate.TABLE_COORDINATE, values, Coordinate.COLUMN_COORDINATE_ID + "=?", new String[]{String.valueOf(coordinateId)});
            }
            result = load(coordinateId);
        }finally {
            db.close();
        }
        return result;
    }

    public void delete(Coordinate coordinate){
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            db.delete(Coordinate.TABLE_COORDINATE, Coordinate.COLUMN_COORDINATE_ID + "=?", new String[]{String.valueOf(coordinate.getCoordinateId())});
        }finally {
            db.close();
        }
    }

    public Coordinate load(Long coordinateId){
        SQLiteDatabase db = helper.getReadableDatabase();

        Coordinate coordinate = null;
        try{
            Cursor cursor = db.query(Coordinate.TABLE_COORDINATE, null, Coordinate.COLUMN_COORDINATE_ID + "=?", new String[]{String.valueOf(coordinateId)}, null, null, null);
            cursor.moveToFirst();
            coordinate = getCoordinate(cursor);
        }finally {
            db.close();
        }
        return coordinate;
    }

    public List<Coordinate> list(){
        SQLiteDatabase db = helper.getReadableDatabase();

        List<Coordinate> coordinateList;
        try{
            Cursor cursor = db.query(Coordinate.TABLE_COORDINATE, null, null, null, null, null, Coordinate.COLUMN_COORDINATE_ID);
            coordinateList = new ArrayList<Coordinate>();
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                coordinateList.add(getCoordinate(cursor));
                cursor.moveToNext();
            }
        }finally {
            db.close();
        }
        return coordinateList;
    }

    public List<Coordinate> Search(String column[]){
        SQLiteDatabase db = helper.getReadableDatabase();

        int args = 0;
        for(int i = 0; i < column.length; i++)
            if(column[i] != null)
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
                selection = selection + Coordinate.COLUMNS[i] + " =" + " ?";
            }
        }

        List<Coordinate> coordinateList;
        try{
            Cursor cursor = db.query(Coordinate.TABLE_COORDINATE, null, selection, selectionArgs, null, null, Coordinate.COLUMN_COORDINATE_ID);
            coordinateList = new ArrayList<>();
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                coordinateList.add(getCoordinate(cursor));
                cursor.moveToNext();
            }
        }finally {
            db.close();
        }
        return coordinateList;
        }

        private Coordinate getCoordinate(Cursor cursor){
            Coordinate coordinate = new Coordinate();

            coordinate.setCoordinateId(cursor.getLong(0));
            coordinate.setItemNumber(cursor.getInt(1));
            coordinate.setItem1Id(cursor.getLong(2));
            coordinate.setItem2Id(cursor.getLong(3));
            coordinate.setItem3Id(cursor.getLong(4));
            coordinate.setItem4Id(cursor.getLong(5));
            coordinate.setItem5Id(cursor.getLong(6));
            coordinate.setItem6Id(cursor.getLong(7));
            coordinate.setItem7Id(cursor.getLong(8));
            coordinate.setItem8Id(cursor.getLong(9));
            coordinate.setItem9Id(cursor.getLong(10));

            return coordinate;
        }
}
