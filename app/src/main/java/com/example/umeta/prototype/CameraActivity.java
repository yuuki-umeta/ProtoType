package com.example.umeta.prototype;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends AppCompatActivity {

    private Uri _imageUri;
    private String fileName;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        camera();
        }

    public void camera(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, permissions, 2000);
            return;
        }

        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date(System.currentTimeMillis());
        String nowStr = dataFormat.format(now);
        fileName = "UseCameraActivityPhoto_" + nowStr + ".jpg";

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, fileName);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

        ContentResolver resolver = getContentResolver();
        _imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, _imageUri);
        startActivityForResult(intent, 200);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent date){
        if(requestCode == 200 && resultCode == RESULT_OK){
            try {
                ImageView photo = (ImageView) findViewById(R.id.Photo);
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), _imageUri);
                photo.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        else {
            Intent intentItemEntry = new Intent(CameraActivity.this, ItemEntryActivity.class);
            startActivity(intentItemEntry);
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(requestCode == 2000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            camera();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setTitle("確認");
        alertDlg.setMessage("このアイテムを登録しますか？");
        alertDlg.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intentPropertyInput = new Intent(CameraActivity.this, PropertyInputActivity.class);
                        intentPropertyInput.putExtra("Uri", _imageUri);
                        startActivity(intentPropertyInput);
                        finish();
                    }
                }
        );
        alertDlg.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        camera();
                    }
                }
        );
        alertDlg.create().show();
        return super.onTouchEvent(event);
    }
}
