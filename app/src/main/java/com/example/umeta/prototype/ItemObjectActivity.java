package com.example.umeta.prototype;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.umeta.prototype.Database.ItemProperty;
import com.example.umeta.prototype.Database.ItemPropertyDao;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;

public class ItemObjectActivity extends AppCompatActivity {

    ItemProperty itemProperty = new ItemProperty();
    ItemPropertyDao dao = new ItemPropertyDao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_object);

        Intent intent = this.getIntent();
        Long rowId = intent.getLongExtra("id", 0);

        itemProperty = dao.load(rowId);

        TextView category = (TextView) findViewById(R.id.category);
        TextView color = (TextView) findViewById(R.id.color);
        TextView size = (TextView) findViewById(R.id.size);
        TextView brand = (TextView) findViewById(R.id.brand);
        TextView purchase_date = (TextView) findViewById(R.id.purcase_date);
        TextView price = (TextView) findViewById(R.id.price);

        category.setText(itemProperty.getItemCategory());
        color.setText(itemProperty.getItemColor());
        size.setText(itemProperty.getItemSize());
        brand.setText(itemProperty.getItemBrand());
        purchase_date.setText(itemProperty.getItemPurchaseDate());
        price.setText(itemProperty.getItemPrice());

        try {
            InputStream in = openFileInput(rowId.toString());
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            ImageView photo = (ImageView) findViewById(R.id.Photo);
            photo.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
