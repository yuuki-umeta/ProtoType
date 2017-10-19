package com.example.umeta.prototype;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.umeta.prototype.Database.ItemProperty;
import com.example.umeta.prototype.Database.ItemPropertyDao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PropertyInputActivity extends AppCompatActivity implements View.OnClickListener {

    private ItemProperty itemProperty = new ItemProperty();

    private String itemName = null;
    private String itemColor = null;
    private String itemSize = null;
    private String itemBrand = null;
    private String itemPurchaseDate = null;
    private String itemPrice = null;

    private Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_input);

        Button bt = (Button) findViewById(R.id.entry);
        bt.setOnClickListener(this);


    }

    public void onClick(View v) {
        Toast.makeText(this, "登録しました。", Toast.LENGTH_LONG).show();

        Spinner spinnerItem = (Spinner)findViewById(R.id.item_list);
        Spinner spinnerColor = (Spinner)findViewById(R.id.color_list);
        Spinner spinnerSize = (Spinner) findViewById(R.id.size_list);
        EditText editTextBrand = (EditText) findViewById(R.id.brand);

        itemName = (String) spinnerItem.getSelectedItem();
        itemColor = (String) spinnerColor.getSelectedItem();
        itemSize = (String) spinnerSize.getSelectedItem();
        itemBrand = editTextBrand.getText().toString();


        itemPropertyInput();

        Intent intent = this.getIntent();
        Uri _imageUri = intent.getParcelableExtra("Uri");
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), _imageUri);
        }catch (IOException e){
            e.printStackTrace();
        }

        Long itemId = itemProperty.getItemId();

        try {
            final FileOutputStream out = openFileOutput(itemId.toString(), Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finish();
    }

    public void itemPropertyInput(){
        itemProperty.setItemName(itemName);
        itemProperty.setItemColor(itemColor);
        itemProperty.setItemSize(itemSize);
        itemProperty.setItemBrand(itemBrand);
        itemProperty.setItemPurchaseDate(itemPurchaseDate);
        itemProperty.setItemPrice(itemPrice);

        ItemPropertyDao dao = new ItemPropertyDao(this);
        itemProperty = dao.save(itemProperty);

        return;
    }
}
