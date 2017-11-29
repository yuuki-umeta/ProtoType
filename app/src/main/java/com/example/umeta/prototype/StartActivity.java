package com.example.umeta.prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.umeta.prototype.Search.ItemSearchActivity;
import com.example.umeta.prototype.Search.SearchResultActivity;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button btItemEntry = (Button) findViewById(R.id.item_entry);
        Button btItemCheck = (Button) findViewById(R.id.item_check);
        Button btItemSearch = (Button) findViewById(R.id.item_search);
        Button btWearReport = (Button) findViewById(R.id.wear_report);

        btItemEntry.setOnClickListener(this);
        btItemCheck.setOnClickListener(this);
        btItemSearch.setOnClickListener(this);
        btWearReport.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_entry:
                Intent intentItemEntry = new Intent(this, ItemEntryActivity.class);
                startActivity(intentItemEntry);
                break;

            case R.id.item_check:
                Intent intentSearchResult = new Intent(this, SearchResultActivity.class);
                intentSearchResult.putExtra("all", true);
                intentSearchResult.putExtra("coordinate", false);
                startActivity(intentSearchResult);
                break;

            case R.id.item_search:
                Intent intentItemSearch = new Intent(this, ItemSearchActivity.class);
                startActivity(intentItemSearch);
                break;

            case R.id.wear_report:
                Intent intentWearReport = new Intent(this, WearReportActivity.class);
                startActivity(intentWearReport);
                break;
        }
    }
}


















































































