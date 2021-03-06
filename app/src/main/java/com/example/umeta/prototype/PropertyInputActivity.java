package com.example.umeta.prototype;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umeta.prototype.Database.Item;
import com.example.umeta.prototype.Database.ItemDao;
import com.example.umeta.prototype.Extras.GetSpinnerId;
import com.example.umeta.prototype.Extras.PropertyDatePickerDialogFragment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

public class PropertyInputActivity extends AppCompatActivity implements View.OnClickListener {

    private Item item = new Item();

    private Boolean edit = null;

    private String itemCategory = null;
    private String itemColor = null;
    private String itemSize = null;
    private String itemBrand = null;
    private String itemPurchaseDate = null;
    private String itemPrice = null;

    private Bitmap bitmap = null;
    private TextView textDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_input);

        Intent intent = this.getIntent();
        edit = intent.getBooleanExtra("edit", true);

        if (edit) {
            Spinner spinnerCategory = (Spinner) findViewById(R.id.category_list);
            Spinner spinnerColor = (Spinner) findViewById(R.id.color_list);
            Spinner spinnerSize = (Spinner) findViewById(R.id.size_list);
            EditText editTextBrand = (EditText) findViewById(R.id.brand);
            textDate = (TextView) findViewById(R.id.date);
            EditText editTextPrice = (EditText) findViewById(R.id.price);

            GetSpinnerId getSpinnerId = new GetSpinnerId();
            spinnerCategory.setSelection(getSpinnerId.getCategorySpinnerId(intent.getStringExtra("category")));
            spinnerColor.setSelection(getSpinnerId.getColorSpinnerId(intent.getStringExtra("color")));
            spinnerSize.setSelection(getSpinnerId.getSizeSpinnerId(intent.getStringExtra("size")));
            editTextBrand.setText(intent.getStringExtra("brand"));
            textDate.setText(intent.getStringExtra("purchaseDate"));
            editTextPrice.setText(intent.getStringExtra("price"));
        } else {
            textDate = (TextView) findViewById(R.id.date);
            Calendar todayDateCalendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
            textDate.setText(String.valueOf(todayDateCalendar.get(Calendar.YEAR)) +
                    "/ " + String.valueOf(todayDateCalendar.get(Calendar.MONTH) + 1) +
                    "/ " + String.valueOf(todayDateCalendar.get(Calendar.DAY_OF_MONTH)));

        }
        Button bt_entry = (Button) findViewById(R.id.entry);
        Button bt_calender = (Button) findViewById(R.id.calender);

        bt_entry.setOnClickListener(this);
        bt_calender.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calender:
                PropertyDatePickerDialogFragment datePicker = new PropertyDatePickerDialogFragment();
                datePicker.show(getSupportFragmentManager(), "datePicker");
                break;

            case R.id.entry:
                Toast.makeText(this, "登録しました。", Toast.LENGTH_LONG).show();

                Spinner spinnerCategory = (Spinner) findViewById(R.id.category_list);
                Spinner spinnerColor = (Spinner) findViewById(R.id.color_list);
                Spinner spinnerSize = (Spinner) findViewById(R.id.size_list);
                EditText editTextBrand = (EditText) findViewById(R.id.brand);
                EditText editTextPrice = (EditText) findViewById(R.id.price);

                itemCategory = (String) spinnerCategory.getSelectedItem();
                itemColor = (String) spinnerColor.getSelectedItem();
                itemSize = (String) spinnerSize.getSelectedItem();
                itemBrand = editTextBrand.getText().toString();
                itemPurchaseDate = textDate.getText().toString();
                itemPrice = editTextPrice.getText().toString();

                itemPropertyInput();

                if (!edit) {
                    Intent intent = this.getIntent();
                    Uri _imageUri = intent.getParcelableExtra("Uri");
                    Long itemId = item.getItemId();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), _imageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        final FileOutputStream out = openFileOutput(itemId.toString(), Context.MODE_PRIVATE);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                finish();
                break;
        }
    }


    public void itemPropertyInput() {
        if (edit) {
            Intent intent = this.getIntent();
            item.setItemId(intent.getLongExtra("id", 0));
        }
            item.setItemCategory(itemCategory);
        item.setItemColor(itemColor);
        item.setItemSize(itemSize);
        item.setItemBrand(itemBrand);
        item.setItemPurchaseDate(itemPurchaseDate);
        item.setItemPrice(itemPrice);

        ItemDao dao = new ItemDao(this);
        item = dao.save(item);
    }

    public void setDate(int year, int month, int day) {
        String dateStr = String.valueOf(year) + "/ " + String.valueOf(month + 1) + "/ " + String.valueOf(day);
        textDate.setText(dateStr);
    }
}
