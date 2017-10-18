package com.example.umeta.prototype;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Gallery;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.io.IOException;

public class GalleryActivity extends AppCompatActivity {

    private Uri _imageUri;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        gallery();
    }

    public void gallery() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, 1001);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                _imageUri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), _imageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ImageView photo = (ImageView) findViewById(R.id.Photo);
                photo.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setTitle("確認");
        alertDlg.setMessage("このアイテムを登録しますか？");
        alertDlg.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intentPropertyInput = new Intent(GalleryActivity.this, PropertyInputActivity.class);
                        intentPropertyInput.putExtra("Uri", _imageUri);
                        startActivity(intentPropertyInput);
                        finish();
                    }
                }
        );
        alertDlg.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        gallery();
                    }
                }
        );
        alertDlg.create().show();
        return super.onTouchEvent(event);
    }


}
