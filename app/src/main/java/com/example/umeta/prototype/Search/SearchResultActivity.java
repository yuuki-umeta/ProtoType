package com.example.umeta.prototype.Search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.umeta.prototype.Database.Item;
import com.example.umeta.prototype.Database.ItemDao;
import com.example.umeta.prototype.ItemObjectActivity;
import com.example.umeta.prototype.R;

import java.util.List;

public class SearchResultActivity extends AppCompatActivity implements View.OnClickListener{

    Long id = null;
    Long rowId = null;
    List<Item> itemList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Button btBack = (Button) findViewById(R.id.back);
        btBack.setOnClickListener(this);

        ListView itemListView = (ListView) findViewById(R.id.item_list);

        ItemDao dao = new ItemDao(this);
        Intent intent = this.getIntent();
        Boolean all = intent.getBooleanExtra("all", true);
        if(all){
            itemList = dao.list();
        }
        else{
            String column[] = intent.getStringArrayExtra("column");
            itemList = dao.search(column);
        }

        ArrayAdapter<Item> arrayAdapterI = new ArrayAdapter<Item>(SearchResultActivity.this, android.R.layout.simple_list_item_1, itemList);
        itemListView.setAdapter(arrayAdapterI);
        itemListView.setOnItemClickListener(new SearchResultActivity.ListItemClickListener());
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Item item = itemList.get(i);
            rowId = item.getItemId();
            itemObjectIntent(rowId);
        }
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }

    public void itemObjectIntent(Long rowId){
        Intent itemObjectIntent = new Intent(this, ItemObjectActivity.class);
        itemObjectIntent.putExtra("id", rowId);
        if(this.getIntent().getBooleanExtra("coordinate", true)){
            itemObjectIntent.putExtra("coordinate", true);
            startActivityForResult(itemObjectIntent, 120);
        }else{
            itemObjectIntent.putExtra("coordinate", false);
            startActivity(itemObjectIntent);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if(resultCode == RESULT_OK && requestCode == 120){
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
