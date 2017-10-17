package com.example.umeta.prototype.Database;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by yuuki on 2017/08/29.
 */

public class ItemProperty implements Serializable {
    public static final String TABLE_NAME = "item_property";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PARTS_NAME = "parts_name";
//    public static final String COLUMN_ITEM_NUMBER = "item_number";

    private Long rowId = null;
    private String partsName = null;

    public Long getRowId(){
        return rowId;
    }

    public void setRowId(Long rowId){
        this.rowId = rowId;
    }

    public String getPartsName(){
        return partsName;
    }

    public void setPartsName(String partsName){
        this.partsName = partsName;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(getRowId());
        builder.append(",");
        builder.append(getPartsName());
        return builder.toString();
    }
}
