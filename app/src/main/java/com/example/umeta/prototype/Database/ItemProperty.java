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
    public static final String COLUMN_PARTS_COLOR = "parts_color";
    public static final String COLUMN_PARTS_SIZE = "parts_size";
    public static final String COLUMN_PARTS_BRAND = "parts_brand";
    public static final String COLUMN_PARTS_PURCHASE_DATA = "parts_purchase_date";
    public static final String COLUMN_PARTS_PRICE = "parts_price";
    public static final String COLUMN_PARTS_LAST_USE_DATE = "parts_last_use_date";
    public static final String COLUMN_PARTS_FREQUENCY = "parts_frequency";


    private Long rowId = null;
    private String partsName = null;
    private String partsColor = null;
    private String partsSize = null;
    private String partsBrand = null;
    private String partsPurchaseDate = null;
    private String partsPrice = null;
    private String partsLastUseDate = null;
    private String partsFrequency = null;

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

    public String getPartsColor() {
        return partsColor;
    }

    public void setPartsColor(String partsColor){
        this.partsColor = partsColor;
    }

    public String getPartsSize() {
        return partsSize;
    }

    public void setPartsSize(String partsSize) {
        this.partsSize = partsSize;
    }

    public String getPartsBrand() {
        return partsBrand;
    }

    public void setPartsBrand(String partsBrand) {
        this.partsBrand = partsBrand;
    }

    public String getPartsPurchaseDate() {
        return partsPurchaseDate;
    }

    public void setPartsPurchaseDate(String partsPurchaseDate) {
        this.partsPurchaseDate = partsPurchaseDate;
    }

    public String getPartsPrice() {
        return partsPrice;
    }

    public void setPartsPrice(String partsPrice) {
        this.partsPrice = partsPrice;
    }

    public String getPartsLastUseDate() {
        return partsLastUseDate;
    }

    public void setPartsLastUseDate(String partsLastTime) {
        this.partsLastUseDate = partsLastTime;
    }

    public String getPartsFrequency() {
        return partsFrequency;
    }

    public void setPartsFrequency(String partsFrequency) {
        this.partsFrequency = partsFrequency;
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
