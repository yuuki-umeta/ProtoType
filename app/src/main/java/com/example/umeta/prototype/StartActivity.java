package com.example.umeta.prototype;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button btItemEntry = (Button) findViewById(R.id.item_entry);
        Button btItemCheck = (Button) findViewById(R.id.item_check);
        btItemEntry.setOnClickListener(this);
        btItemCheck.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_entry:
                Intent intentItemEntry = new Intent(this, ItemEntryActivity.class);
                startActivity(intentItemEntry);
                break;

            case R.id.item_check:
                Intent intentItemCheck = new Intent(this, ItemCheckActivity.class);
                startActivity(intentItemCheck);
        }
    }
}


















































































