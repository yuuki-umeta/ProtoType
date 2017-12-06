package com.example.umeta.prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.umeta.prototype.Database.Coordinate;
import com.example.umeta.prototype.Database.CoordinateDao;
import com.example.umeta.prototype.Database.Item;
import com.example.umeta.prototype.Database.ItemDao;
import com.example.umeta.prototype.Search.ItemSearchActivity;

import java.util.ArrayList;
import java.util.List;

public class CoordinateInputActivity extends AppCompatActivity implements View.OnClickListener{

    List<Item> itemList = new ArrayList<Item>();
    ItemDao dao = new ItemDao(this);

    ListView listView = null;

    Coordinate coordinate = new Coordinate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wear_report);

        listView = (ListView) findViewById(R.id.item_list);

        Button bt_coordinate = (Button) findViewById(R.id.coordinate);
        Button bt_entry = (Button) findViewById(R.id.entry);
        Button bt_back = (Button) findViewById(R.id.back);

/*        ArrayAdapter<Item> arrayAdapter = new ArrayAdapter<Item>(CoordinateInputActivity.this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(arrayAdapter);*/

        bt_coordinate.setOnClickListener(this);
        bt_entry.setOnClickListener(this);
        bt_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.coordinate:
                Intent itemSearchIntent = new Intent(this, ItemSearchActivity.class);
                itemSearchIntent.putExtra("coordinate", true);
                startActivityForResult(itemSearchIntent, 120);
                break;

            case R.id.back:
                finish();
                break;

            case R.id.entry:
                Toast.makeText(this, "登録しました。", Toast.LENGTH_LONG).show();
                coordinateInput();
                finish();
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if(resultCode == RESULT_OK && requestCode == 120){
            itemList.add(dao.load(intent.getLongExtra("rowId", 0)));
            ArrayAdapter<Item> arrayAdapter = new ArrayAdapter<Item>(CoordinateInputActivity.this, android.R.layout.simple_list_item_1, itemList);
            listView.setAdapter(arrayAdapter);
        }
    }

    public void coordinateInput(){
        Item item = new Item();

        coordinate.setItemNumber(itemList.size());
        if(itemList.size() > 0){
            item = itemList.get(0);
            coordinate.setItem1Id(item.getItemId());
        }
        if(itemList.size() > 1){
            item = itemList.get(1);
            coordinate.setItem2Id(item.getItemId());
        }
        if(itemList.size() > 2){
            item = itemList.get(2);
            coordinate.setItem3Id(item.getItemId());
        }
        if(itemList.size() > 3){
            item = itemList.get(3);
            coordinate.setItem4Id(item.getItemId());
        }
        if(itemList.size() > 4){
            item = itemList.get(4);
            coordinate.setItem5Id(item.getItemId());
        }
        if(itemList.size() > 5){
            item = itemList.get(5);
            coordinate.setItem6Id(item.getItemId());
        }
        if(itemList.size() > 6){
            item = itemList.get(6);
            coordinate.setItem7Id(item.getItemId());
        }
        if(itemList.size() > 7){
            item = itemList.get(7);
            coordinate.setItem8Id(item.getItemId());
        }
        if(itemList.size() > 8){
            item = itemList.get(8);
            coordinate.setItem9Id(item.getItemId());
        }

        CoordinateDao dao = new CoordinateDao(this);
        coordinate = dao.save(coordinate);


    }
}
