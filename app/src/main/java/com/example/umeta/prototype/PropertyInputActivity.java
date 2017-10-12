package com.example.umeta.prototype;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class PropertyInputActivity extends AppCompatActivity implements View.OnClickListener {

    private ItemProperty itemProperty = new ItemProperty();

    private String partsName = null;
    private String stringUri = null;

    private Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_input);

        Button bt = (Button) findViewById(R.id.entry);
        Spinner spinner = (Spinner)findViewById(R.id.parts);

        bt.setOnClickListener(this);
        partsName = (String) spinner.getSelectedItem();
    }

    public void onClick(View v) {
        Toast.makeText(this, "登録しました。", Toast.LENGTH_LONG).show();

        Intent intent = this.getIntent();
        itemPropertyInput();


        Long id = itemProperty.getRowId();
        String stringId = id.toString();

        Uri _imageUri = intent.getParcelableExtra("Uri");

       try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), _imageUri);
        }catch (IOException e){
            e.printStackTrace();
        }

        try {
            final FileOutputStream out = openFileOutput(stringId, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finish();
    }

    public void itemPropertyInput(){
        itemProperty.setPartsName(partsName);

        ItemPropertyDao dao = new ItemPropertyDao(this);
        itemProperty = dao.save(itemProperty);



        return;
    }
}
