package com.example.dipendra.railbuddy.features;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dipendra.railbuddy.R;
import com.example.dipendra.railbuddy.util.DatePickerFragment;


public class trainsBetweenStations extends AppCompatActivity {

    private ImageButton imageButton;
    public static TextView textView;
    private Button getBtn, clearBtn;
    private int day = 0, month = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trains_between_stations);

        imageButton = (ImageButton) findViewById(R.id.selectDate);
        textView = (TextView) findViewById(R.id.dateOfJrn);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(getApplicationContext(), "Pick Date", Toast.LENGTH_LONG).show();
                showDatePickerDialog(view);
            }
        });

        getBtn = (Button) findViewById(R.id.getData);
        clearBtn = (Button) findViewById(R.id.clear);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String source, destination, date;
                EditText e;
                TextView t;
                e = (EditText) findViewById(R.id.source);
                source = e.getText().toString().toLowerCase().trim();
                e = (EditText) findViewById(R.id.destination);
                destination = e.getText().toString().toLowerCase().trim();
                t = (TextView) findViewById(R.id.dateOfJrn);
                date = t.getText().toString().trim();
                String[] dateParts = date.split(" - ");
                // Toast.makeText(getApplicationContext(), dateParts[0]+" "+dateParts[1], Toast.LENGTH_LONG).show();
                if (!source.equals("") && !destination.equals("") && !date.equals("")) {
                    Intent intent = new Intent(trainsBetweenStations.this, train_between_stations_list.class);
                    intent.putExtra("source", source);
                    intent.putExtra("destination", destination);
                    intent.putExtra("date", dateParts[0] + "-" + dateParts[1]);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please Re-check Station Codes", Toast.LENGTH_LONG).show();
                }
                //apiFetch apiFetchData = new apiFetch(1, source, destination, dateParts[0]+"-"+dateParts[1]);
                //apiFetchData.execute();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText e1, e2;
                TextView t1;
                e1 = (EditText) findViewById(R.id.source);
                e2 = (EditText) findViewById(R.id.destination);
                t1 = (TextView) findViewById(R.id.dateOfJrn);
                e1.setText("");
                e2.setText("");
                t1.setText("");
            }
        });

    }


    private void showDatePickerDialog(View v) {

        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static void update(int day, int month, int year) {
        String str;
        month++;
        str = day + " - " + month + " - " + year;
        textView.setText(str);
    }
}
