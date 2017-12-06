package com.example.umeta.prototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.umeta.prototype.Database.Coordinate;
import com.example.umeta.prototype.Database.CoordinateDao;
import com.example.umeta.prototype.Search.SearchResultActivity;

import java.util.List;

public class CoordinateCheckActivity extends AppCompatActivity implements View.OnClickListener{

    List<Coordinate> coordinatesList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinate_check);

        Button btBack = (Button) findViewById(R.id.back);
        btBack.setOnClickListener(this);

        CoordinateDao dao = new CoordinateDao(this);
        ListView coordinateListView = (ListView) findViewById(R.id.coordinate_list);
        coordinatesList = dao.list();

        ArrayAdapter<Coordinate> arrayAdapterC = new ArrayAdapter<Coordinate>(CoordinateCheckActivity.this, android.R.layout.simple_list_item_1, coordinatesList);
        coordinateListView.setAdapter(arrayAdapterC);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
