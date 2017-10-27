package com.example.umeta.prototype.Search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.umeta.prototype.Database.ItemProperty;
import com.example.umeta.prototype.Extras.PropertyDatePickerDialogFragment;
import com.example.umeta.prototype.R;

public class ItemSearchActivity extends AppCompatActivity implements View.OnClickListener {

    private ItemProperty itemProperty = new ItemProperty();

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

        Button bt_calender = (Button) findViewById(R.id.calender);

        bt_calender.setOnClickListener(this);    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calender:
                PropertyDatePickerDialogFragment datePicker = new PropertyDatePickerDialogFragment();
                datePicker.show(getSupportFragmentManager(), "datePicker");
                break;
        }
    }

    public void setDate(int year, int month, int day){
        String dateStr = String.valueOf(year) + "/ " + String.valueOf(month + 1) + "/ " + String.valueOf(day);
        textDate.setText(dateStr);
    }
}
