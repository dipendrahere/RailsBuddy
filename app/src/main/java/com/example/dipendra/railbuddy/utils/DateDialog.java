package com.example.dipendra.railbuddy.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by dipendra on 23/11/16.
 */

public class DateDialog {
    private static EditText e;

    static private ArrayList<Integer> dt;
    public static ArrayList<Integer> fetchdate(Activity context, EditText et){
        dt = new ArrayList<>();
        e = et;
        Calendar newCalendar = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dt.add(dayOfMonth);
                dt.add(monthOfYear);
                dt.add(year);
                e.setText(""+year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH)).show();
        return dt;
    }


}
