package com.example.umeta.prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.umeta.prototype.Database.ItemProperty;
import com.example.umeta.prototype.Database.ItemPropertyDao;
import com.example.umeta.prototype.Search.ItemSearchActivity;
import com.example.umeta.prototype.Search.SearchResultActivity;

import java.util.List;

public class WearReportActivity extends AppCompatActivity implements View.OnClickListener{

    List<ItemProperty> itemList = null;
    ItemPropertyDao dao = new ItemPropertyDao(this);

    ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wear_report);

        listView = (ListView) findViewById(R.id.item_list);

        Button bt_coordinate = (Button) findViewById(R.id.coordinate);
        Button bt_entry = (Button) findViewById(R.id.entry);
        Button bt_back = (Button) findViewById(R.id.back);

/*        ArrayAdapter<ItemProperty> arrayAdapter = new ArrayAdapter<ItemProperty>(WearReportActivity.this, android.R.layout.simple_list_item_1, itemList);
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
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == 120){
            System.out.println(intent.getLongExtra("rowId", 0));
            itemList.add(dao.load(intent.getLongExtra("rowId", 0)));
            ArrayAdapter<ItemProperty> arrayAdapter = new ArrayAdapter<ItemProperty>(WearReportActivity.this, android.R.layout.simple_list_item_1, itemList);
            listView.setAdapter(arrayAdapter);
        }
    }
}
