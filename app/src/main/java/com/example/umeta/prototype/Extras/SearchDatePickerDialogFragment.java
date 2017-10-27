package com.example.umeta.prototype.Extras;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.example.umeta.prototype.PropertyInputActivity;
import com.example.umeta.prototype.Search.ItemSearchActivity;

import java.util.Calendar;

/**
 * Created by umeta on 2017/10/27.
 */

public class SearchDatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calender = Calendar.getInstance();
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH);
        int day = calender.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),this, year, month, day);

        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {

        ItemSearchActivity itemSearchActivity = (ItemSearchActivity) getActivity();
        itemSearchActivity.setDate(year, month, day);
    }
}
