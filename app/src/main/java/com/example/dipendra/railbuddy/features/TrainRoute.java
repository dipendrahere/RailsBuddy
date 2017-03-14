package com.example.dipendra.railbuddy.features;



import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dipendra.railbuddy.R;


public class TrainRoute extends AppCompatActivity {

    private EditText trainNumber;
    private Button getRouteBtn;
    private String trainNumberText;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_route);

        trainNumber = (EditText) findViewById(R.id.trainNumberStart);
        getRouteBtn = (Button) findViewById(R.id.getTrainRoute);
        intent = new Intent(this, trainRouteList.class);
        getRouteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trainNumber = (EditText) findViewById(R.id.trainNumberStart);
                trainNumberText = trainNumber.getText().toString().trim();
                if (trainNumberText.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please re-check train number", Toast.LENGTH_LONG).show();
                } else {
                    intent.putExtra("trainNumberForRoute", trainNumberText);
                    startActivity(intent);
                }

            }
        });
    }
}
