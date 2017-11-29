package com.example.umeta.prototype.Extras;

/**
 * Created by yuuki on 2017/11/16.
 */

public class GetSpinnerId {
    public int getCategorySpinnerId(String category){
        int id = 0;
        switch (category){
            case "トップス":
                id = 0;
                break;
            case "アウター":
                id = 1;
                break;
            case "パンツ":
                id = 2;
                break;
            case "スカート":
                id = 3;
                break;
            case "ワンピース":
                id = 4;
                break;
            case "ソックス":
                id = 5;
                break;
            case "シューズ":
                id = 6;
                break;
            case "バッグ":
                id = 7;
                break;
            case "小物":
                id = 8;
                break;
            case "アクセサリー":
                id = 9;
                break;
            case "帽子":
                id = 10;
                break;
            case "その他":
                id = 11;
                break;
        }
        return id;
    }

    public int getColorSpinnerId(String color){
        int id = 0;
        switch (color){
            case "白":
                id = 0;
                break;
            case "黒":
                id = 1;
                break;
            case "赤":
                id = 2;
                break;
            case "黄":
                id = 3;
                break;
            case "オレンジ":
                id = 4;
                break;
            case "緑":
                id = 5;
                break;
            case "青":
                id = 6;
                break;
            case "紺":
                id = 7;
                break;
            case "その他":
                id = 8;
                break;
        }
        return id;
    }

    public int getSizeSpinnerId(String size){
        int id = 0;
        switch (size){
            case "SS":
                id = 0;
                break;
            case "S":
                id = 1;
                break;
            case "M":
                id = 2;
                break;
            case "L":
                id = 3;
                break;
            case "LL":
                id = 4;
                break;
            case "22":
                id = 5;
                break;
            case "22.5":
                id = 6;
                break;
            case "23":
                id = 7;
                break;
            case "23.5":
                id = 8;
                break;
            case "24":
                id = 9;
                break;
            case "24.5":
                id = 10;
                break;
            case "25":
                id = 11;
                break;
            case "25.5":
                id = 12;
                break;
            case "26":
                id = 13;
                break;
            case "26.5":
                id = 14;
                break;
            case "27":
                id = 15;
                break;
            case "27.5":
                id = 16;
                break;
            case "28":
                id = 17;
                break;
            case "28.5":
                id = 18;
                break;
            case "29":
                id = 19;
                break;
            case "その他":
                id = 20;
                break;
        }
        return id;
    }
}
