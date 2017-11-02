package com.example.umeta.prototype.Search;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.umeta.prototype.Database.ItemProperty;
import com.example.umeta.prototype.Extras.PropertyDatePickerDialogFragment;
import com.example.umeta.prototype.Extras.SearchDatePickerDialogFragment;
import com.example.umeta.prototype.R;

import java.util.Calendar;
import java.util.TimeZone;

public class ItemSearchActivity extends AppCompatActivity implements View.OnClickListener {

    private ItemProperty itemProperty = new ItemProperty();

    boolean search[] = new boolean[10];

    private String itemCategory = null;
    private String itemColor = null;
    private String itemSize = null;
    private String itemBrand = null;
    private String itemPurchaseDate = null;
    private String itemPrice = null;
    private TextView textDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search);

        textDate = (TextView) findViewById(R.id.date);
        Calendar todayDateCalendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
        textDate.setText(String.valueOf(todayDateCalendar.get(Calendar.YEAR)) +
                "/ " + String.valueOf(todayDateCalendar.get(Calendar.MONTH) + 1) +
                "/ " + String.valueOf(todayDateCalendar.get(Calendar.DAY_OF_MONTH)));

        Button bt_calender = (Button) findViewById(R.id.calender);
        Button bt_search = (Button) findViewById(R.id.search);

        bt_calender.setOnClickListener(this);
        bt_search.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calender:
                SearchDatePickerDialogFragment datePicker = new SearchDatePickerDialogFragment();
                datePicker.show(getSupportFragmentManager(), "datePicker");
                break;

            case R.id.search:
                Switch sw_category = (Switch) findViewById(R.id.button_category);
                Switch sw_color = (Switch) findViewById(R.id.button_color);
                Switch sw_size = (Switch) findViewById(R.id.button_size);
                Switch sw_brand = (Switch) findViewById(R.id.button_brand);
                Switch sw_purchase_date = (Switch) findViewById(R.id.button_purchase_date);
                Switch sw_price = (Switch) findViewById(R.id.button_price);

                sw_category.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            search[0] = true;
                        }
                    }
                });
                sw_color.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            search[1] = true;
                        }
                    }
                });
                sw_size.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            search[2] = true;
                        }
                    }
                });
                sw_brand.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            search[3] = true;
                        }
                    }
                });
                sw_purchase_date.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            search[4] = true;
                        }
                    }
                });
                sw_price.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            search[5] = true;
                        }
                    }
                });
/*                Intent intent = new Intent(this, SearchResultActivity.class);
                intent.putExtra("search", search);
                startActivity(intent);*/

                break;

        }
    }

    public void setDate(int year, int month, int day) {
        String dateStr = String.valueOf(year) + "/ " + String.valueOf(month + 1) + "/ " + String.valueOf(day);
        textDate.setText(dateStr);
    }
}

