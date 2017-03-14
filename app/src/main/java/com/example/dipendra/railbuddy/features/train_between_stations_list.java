package com.example.dipendra.railbuddy.features;



import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dipendra.railbuddy.R;
import com.example.dipendra.railbuddy.adapters.trainBetweenStationsListAdapter;
import com.example.dipendra.railbuddy.info.infoDetail;
import com.example.dipendra.railbuddy.util.apiFetch;

import java.util.ArrayList;

public class train_between_stations_list extends AppCompatActivity {

    private String source = "", destination = "", date = "";
    static ListView listView;
    static Activity a;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_between_stations_list);
        view = this.findViewById(android.R.id.content).getRootView();
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            source = extras.getString("source");
            destination = extras.getString("destination");
            date = extras.getString("date");
        }
        a = (Activity) view.getContext();
        listView = (ListView) findViewById(R.id.listViewTrainsBetweenStations);
        apiFetch apiFetchData = new apiFetch(1, train_between_stations_list.this, source, destination, date);
        apiFetchData.execute();
    }

    public static void updateUi(ArrayList<infoDetail> arr) {
        if(arr == null) {
            Toast.makeText(a, "Something Went Wrong :( Please Verify Stations Code", Toast.LENGTH_LONG).show();
            return ;
        }
        trainBetweenStationsListAdapter adapter = new trainBetweenStationsListAdapter(a, arr);
        listView.setAdapter(adapter);
    }
}
