package com.example.umeta.prototype.Database;

import java.io.Serializable;

/**
 * Created by yuuki on 2017/11/30.
 */

public class Coordinate implements Serializable{
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

    public static final String[] COLUMNS = {COLUMN_COORDINATE_ID, COLUMN_COORDINATE_ITEM_NUMBER, COLUMN_COORDINATE_ITEM_ID_1,
            COLUMN_COORDINATE_ITEM_ID_2, COLUMN_COORDINATE_ITEM_ID_3, COLUMN_COORDINATE_ITEM_ID_4, COLUMN_COORDINATE_ITEM_ID_5,
            COLUMN_COORDINATE_ITEM_ID_6, COLUMN_COORDINATE_ITEM_ID_7, COLUMN_COORDINATE_ITEM_ID_8, COLUMN_COORDINATE_ITEM_ID_9} ;

    private Long coordinateId = null;
    private int itemNumber = 0;
    private Long item1Id = null;
    private Long item2Id = null;
    private Long item3Id = null;
    private Long item4Id = null;
    private Long item5Id = null;
    private Long item6Id = null;
    private Long item7Id = null;
    private Long item8Id = null;
    private Long item9Id = null;

    public Long getCoordinateId() {
        return coordinateId;
    }

    public void setCoordinateId(Long coordinateId) {
        this.coordinateId = coordinateId;
    }

    public Long getItem1Id() {
        return item1Id;
    }

    public void setItem1Id(Long item1Id) {
        this.item1Id = item1Id;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public Long getItem2Id() {
        return item2Id;
    }

    public void setItem2Id(Long item2Id) {
        this.item2Id = item2Id;
    }

    public Long getItem3Id() {
        return item3Id;
    }

    public void setItem3Id(Long item3Id) {
        this.item3Id = item3Id;
    }

    public Long getItem4Id() {
        return item4Id;
    }

    public void setItem4Id(Long item4Id) {
        this.item4Id = item4Id;
    }

    public Long getItem5Id() {
        return item5Id;
    }

    public void setItem5Id(Long item5Id) {
        this.item5Id = item5Id;
    }

    public Long getItem6Id() {
        return item6Id;
    }

    public void setItem6Id(Long item6Id) {
        this.item6Id = item6Id;
    }

    public Long getItem7Id() {
        return item7Id;
    }

    public void setItem7Id(Long item7Id) {
        this.item7Id = item7Id;
    }

    public Long getItem8Id() {
        return item8Id;
    }

    public void setItem8Id(Long item8Id) {
        this.item8Id = item8Id;
    }

    public Long getItem9Id() {
        return item9Id;
    }

    public void setItem9Id(Long item9Id) {
        this.item9Id = item9Id;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(getCoordinateId());
        builder.append(" : ");
        builder.append((getItemNumber()));
        if(itemNumber > 0){
            builder.append("  ");
            builder.append(getItem1Id());
        }
        if(itemNumber > 1) {
            builder.append(", ");
            builder.append(getItem2Id());
        }
        if(itemNumber > 2) {
            builder.append(", ");
            builder.append(getItem3Id());
        }
        if(itemNumber > 3) {
            builder.append(", ");
            builder.append(getItem4Id());
        }
        if(itemNumber > 4) {
            builder.append(", ");
            builder.append(getItem5Id());
        }
        if(itemNumber > 5) {
            builder.append(", ");
            builder.append(getItem6Id());
        }
        if(itemNumber > 6) {
            builder.append(", ");
            builder.append(getItem7Id());
        }
        if(itemNumber > 7) {
            builder.append(", ");
            builder.append(getItem8Id());
        }
        if(itemNumber > 8) {
            builder.append(", ");
            builder.append(getItem9Id());
        }
        return builder.toString();
    }

}
