package com.example.umeta.prototype.Search;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.umeta.prototype.Database.ItemProperty;
import com.example.umeta.prototype.Extras.PropertyDatePickerDialogFragment;
import com.example.umeta.prototype.Extras.SearchDatePickerDialogFragment;
import com.example.umeta.prototype.R;
import com.example.umeta.prototype.WearReportActivity;

import java.util.Calendar;
import java.util.TimeZone;

public class ItemSearchActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean searchColumn[] = new boolean[10];
    private String column[] = new String[10];

    private String itemCategory = null;
    private String itemColor = null;
    private String itemSize = null;
    private String itemBrand = null;
    private String itemPurchaseDate = null;
    private String itemPrice = null;
    private TextView textDate = null;

    private Long rowId;

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
        Button bt_back = (Button) findViewById(R.id.back);

        bt_calender.setOnClickListener(this);
        bt_search.setOnClickListener(this);
        bt_back.setOnClickListener(this);

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
                    searchColumn[1] = true;
                } else {
                    searchColumn[1] = false;
                }
            }
        });
        sw_color.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    searchColumn[2] = true;
                } else {
                    searchColumn[2] = false;
                }

            }
        });
        sw_size.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    searchColumn[3] = true;
                } else {
                    searchColumn[3] = false;
                }
            }
        });
        sw_brand.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    searchColumn[4] = true;
                } else {
                    searchColumn[4] = false;
                }
            }
        });
        sw_purchase_date.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    searchColumn[5] = true;
                } else {
                    searchColumn[5] = false;
                }
            }
        });
        sw_price.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    searchColumn[6] = true;
                } else {
                    searchColumn[6] = false;
                }
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calender:
                SearchDatePickerDialogFragment datePicker = new SearchDatePickerDialogFragment();
                datePicker.show(getSupportFragmentManager(), "datePicker");
                break;

            case R.id.search:
                Spinner spinnerItem = (Spinner) findViewById(R.id.category_list);
                Spinner spinnerColor = (Spinner) findViewById(R.id.color_list);
                Spinner spinnerSize = (Spinner) findViewById(R.id.size_list);
                EditText editTextBrand = (EditText) findViewById(R.id.brand);
                EditText editTextPrice = (EditText) findViewById(R.id.price);

                itemCategory = (String) spinnerItem.getSelectedItem();
                itemColor = (String) spinnerColor.getSelectedItem();
                itemSize = (String) spinnerSize.getSelectedItem();
                itemBrand = editTextBrand.getText().toString();
                itemPurchaseDate = textDate.getText().toString();
                itemPrice = editTextPrice.getText().toString();

                if(searchColumn[1]) column[1] = itemCategory;
                if(searchColumn[2]) column[2] = itemColor;
                if(searchColumn[3]) column[3] = itemSize;
                if(searchColumn[4]) column[4] = itemBrand;
                if(searchColumn[5]) column[5] = itemPurchaseDate;
                if(searchColumn[6]) column[6] = itemPrice;

                Intent searchResultIntent = new Intent(this, SearchResultActivity.class);
                searchResultIntent.putExtra("all", false);
                searchResultIntent.putExtra("column", column);

                if(this.getIntent().getBooleanExtra("coordinate", true)){
                    searchResultIntent.putExtra("coordinate", true);
                    startActivityForResult(searchResultIntent, 120);
                }
                else{
                    searchResultIntent.putExtra("coordinate", false);
                    startActivity(searchResultIntent);
                    finish();
                }
                break;

            case R.id.back:
                finish();
                break;
        }
    }

    public void setDate(int year, int month, int day) {
        String dateStr = String.valueOf(year) + "/ " + String.valueOf(month + 1) + "/ " + String.valueOf(day);
        textDate.setText(dateStr);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == 120){
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}

