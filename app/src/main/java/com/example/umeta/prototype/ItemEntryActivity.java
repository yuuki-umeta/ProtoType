package com.example.umeta.prototype;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ItemEntryActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_entry);

        Button btEntryCamera = (Button) findViewById(R.id.item_entry_camera);
        Button btEntryGallery = (Button) findViewById(R.id.item_entry_gallery);
        Button btBack = (Button) findViewById(R.id.back);
        btEntryCamera.setOnClickListener(this);
        btEntryGallery.setOnClickListener(this);
        btBack.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_entry_camera:
                Intent intentEntryCamera = new Intent(this, CameraActivity.class);
                startActivity(intentEntryCamera);
                finish();
                break;

            case R.id.item_entry_gallery:
                Intent intentEntryGallery = new Intent(this, GalleryActivity.class);
                startActivity(intentEntryGallery);
                finish();
                break;

            case R.id.back:
                finish();
                break;
        }
    }
}