package com.example.umeta.prototype.Database;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by yuuki on 2017/08/29.
 */

public class ItemProperty implements Serializable {
    public static final String TABLE_NAME = "item_property";

    public static final String COLUMN_ITEM_ID = "item_id";
    public static final String COLUMN_ITEM_NAME = "item_name";
    public static final String COLUMN_ITEM_COLOR = "item_color";
    public static final String COLUMN_ITEM_SIZE = "item_size";
    public static final String COLUMN_ITEM_BRAND = "item_brand";
    public static final String COLUMN_ITEM_PURCHASE_DATE = "item_purchase_date";
    public static final String COLUMN_ITEM_PRICE = "item_price";
    public static final String COLUMN_ITEM_LAST_USE_DATE = "item_last_use_date";
    public static final String COLUMN_ITEM_FREQUENCY = "item_frequency";


    private Long itemId = null;
    private String itemName = null;
    private String itemColor = null;
    private String itemSize = null;
    private String itemBrand = null;
    private String itemPurchaseDate = null;
    private String itemPrice = null;
    private String itemLastUseDate = null;
    private String itemFrequency = null;

    public Long getItemId(){
        return itemId;
    }

    public void setItemId(Long itemId){
        this.itemId = itemId;
    }

    public String getItemName(){
        return itemName;
    }

    public void setItemName(String itemName){
        this.itemName = itemName;
    }

    public String getItemColor() {
        return itemColor;
    }

    public void setItemColor(String itemColor){
        this.itemColor = itemColor;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public String getItemBrand() {
        return itemBrand;
    }

    public void setItemBrand(String itemBrand) {
        this.itemBrand = itemBrand;
    }

    public String getItemPurchaseDate() {
        return itemPurchaseDate;
    }

    public void setItemPurchaseDate(String itemPurchaseDate) {
        this.itemPurchaseDate = itemPurchaseDate;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemsPrice) {
        this.itemPrice = itemsPrice;
    }

    public String getItemLastUseDate() {
        return itemLastUseDate;
    }

    public void setItemLastUseDate(String itemsLastTime) {
        this.itemLastUseDate = itemsLastTime;
    }

    public String getItemFrequency() {
        return itemFrequency;
    }

    public void setItemFrequency(String itemFrequency) {
        this.itemFrequency = itemFrequency;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(getItemId());
        builder.append(",");
        builder.append(getItemName());
        builder.append(",");
        builder.append(getItemColor());
        builder.append(",");
        builder.append(getItemSize());
        builder.append(",");
        builder.append(getItemBrand());
        builder.append(",");
        builder.append(getItemPurchaseDate());
        return builder.toString();
    }
}
