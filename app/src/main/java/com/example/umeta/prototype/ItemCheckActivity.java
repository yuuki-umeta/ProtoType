package com.example.umeta.prototype;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.umeta.prototype.Database.ItemProperty;
import com.example.umeta.prototype.Database.ItemPropertyDao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ItemCheckActivity extends AppCompatActivity implements View.OnClickListener {

    private Bitmap bitmap;
    Long rowId = null;
    List <ItemProperty>itemList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_check);

        Button btCheck = (Button) findViewById(R.id.check);
        Button btBack = (Button) findViewById(R.id.back);
        btCheck.setOnClickListener(this);
        btBack.setOnClickListener(this);

        ListView listView = (ListView) findViewById(R.id.item_list);

        ItemPropertyDao dao = new ItemPropertyDao(this);
        itemList = dao.list();

        ArrayAdapter<ItemProperty> arrayAdapter = new ArrayAdapter<ItemProperty>(ItemCheckActivity.this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new ListItemClickListener());
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            ItemProperty item = itemList.get(i);
            rowId = item.getItemId();
        }
    }




    public void onClick(View v) {
        switch (v.getId()){
            case R.id.check:
                try {
                    InputStream in = openFileInput(rowId.toString());
                    bitmap = BitmapFactory.decodeStream(in);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ImageView photo = (ImageView) findViewById(R.id.Photo);
                photo.setImageBitmap(bitmap);

                break;

            case R.id.back:
                finish();
                break;
        }
    }
}
