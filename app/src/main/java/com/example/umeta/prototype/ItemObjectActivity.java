package com.example.umeta.prototype;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umeta.prototype.Database.ItemProperty;
import com.example.umeta.prototype.Database.ItemPropertyDao;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;

public class ItemObjectActivity extends AppCompatActivity implements View.OnClickListener{

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

        Button bt_edit = (Button) findViewById(R.id.edit);
        Button bt_delete = (Button) findViewById(R.id.delete);
        Button bt_back = (Button) findViewById(R.id.back);

        if(this.getIntent().getBooleanExtra("coordinate", true)){
            bt_edit.setText("コーディネートに加える");
            bt_delete.setText("");
        }

        bt_edit.setOnClickListener(this);
        bt_delete.setOnClickListener(this);
        bt_back.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.edit:
                if(this.getIntent().getBooleanExtra("coordinate", true)) {
                    Intent intent = new Intent();
                    intent.putExtra("rowId", itemProperty.getItemId());
                    setResult(RESULT_OK, intent);
                }else {
                    Intent propertyIntputIntent = new Intent(this, PropertyInputActivity.class);
                    propertyIntputIntent.putExtra("edit", true);
                    propertyIntputIntent.putExtra("id", itemProperty.getItemId());
                    propertyIntputIntent.putExtra("category", itemProperty.getItemCategory());
                    propertyIntputIntent.putExtra("color", itemProperty.getItemColor());
                    propertyIntputIntent.putExtra("size", itemProperty.getItemSize());
                    propertyIntputIntent.putExtra("brand", itemProperty.getItemBrand());
                    propertyIntputIntent.putExtra("purchaseDate", itemProperty.getItemPurchaseDate());
                    propertyIntputIntent.putExtra("price", itemProperty.getItemPrice());
                    startActivity(propertyIntputIntent);
                }
                finish();
                break;

            case R.id.delete:
                if(this.getIntent().getBooleanExtra("coordinate", true)) {
                    break;
                }else {
                    dao.delete(itemProperty);
                    Toast.makeText(this, "削除しました", Toast.LENGTH_LONG).show();
                    finish();
                    break;
                }

            case R.id.back:
                finish();
                break;
        }
    }

}
