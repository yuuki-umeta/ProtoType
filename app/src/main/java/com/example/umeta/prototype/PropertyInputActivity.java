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

    private String partsName = null;
    private String partsColor = null;
    private String partsSize = null;
    private String partsBrand = null;
    private String partsPurchaseDate = null;
    private String partsPrice = null;

    private Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_input);

        Button bt = (Button) findViewById(R.id.entry);
        bt.setOnClickListener(this);

        Intent intent = this.getIntent();
        Uri _imageUri = intent.getParcelableExtra("Uri");
        try {
            ImageView photo = (ImageView) findViewById(R.id.Photo);
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), _imageUri);
            photo.setImageBitmap(bitmap);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void onClick(View v) {
        Toast.makeText(this, "登録しました。", Toast.LENGTH_LONG).show();

        Spinner spinner = (Spinner)findViewById(R.id.parts);
        partsName = (String) spinner.getSelectedItem();

        Intent intent = this.getIntent();
        itemPropertyInput();


        Long rowId = itemProperty.getRowId();

        try {
            final FileOutputStream out = openFileOutput(rowId.toString(), Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finish();
    }

    public void itemPropertyInput(){
        itemProperty.setPartsName(partsName);
        itemProperty.setPartsColor(partsColor);
        itemProperty.setPartsSize(partsSize);
        itemProperty.setPartsBrand(partsBrand);
        itemProperty.setPartsPurchaseDate(partsPurchaseDate);
        itemProperty.setPartsPrice(partsPrice);

        ItemPropertyDao dao = new ItemPropertyDao(this);
        itemProperty = dao.save(itemProperty);

        return;
    }
}
