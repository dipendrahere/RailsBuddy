package com.example.dipendra.railbuddy.features;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dipendra.railbuddy.R;
import com.example.dipendra.railbuddy.adapters.trainRouteListAdapter;
import com.example.dipendra.railbuddy.info.infoDetail;
import com.example.dipendra.railbuddy.info.trainRouteInfo;
import com.example.dipendra.railbuddy.util.apiFetch;

import java.util.ArrayList;

public class trainRouteList extends AppCompatActivity {

    private Bundle extras;
    private String trainNumber = "";
    static ListView listView;
    static Activity a;
    static View view, temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_route_list);
        view = this.findViewById(android.R.id.content).getRootView();
        extras = getIntent().getExtras();

        if(extras != null) {
            trainNumber = extras.getString("trainNumberForRoute");
           // Toast.makeText(this, trainNumber, Toast.LENGTH_LONG).show();
        }
        a = (Activity)view.getContext();
        listView = (ListView) findViewById(R.id.listViewTrainRoute);
        apiFetch apiFetchData = new apiFetch(2, trainRouteList.this, trainNumber);
        apiFetchData.execute();
    }
    public static void update(trainRouteInfo info) {
        String [] running = info.getRunningDays();
        LinearLayout gridLayout = (LinearLayout) view.findViewById(R.id.running);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            temp = gridLayout.getChildAt(i);

            if(running[i].equals("Y")) {
                temp.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.runningDay));
            }else {
                temp.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.daysColor));

            }
        }

        TextView t1, t2;
        t1 = (TextView) view.findViewById(R.id.trainNum);
        t2 = (TextView) view.findViewById(R.id.trainN);
        t2.setText(info.getTrainName());
        t1.setText(info.getTrainNumber());
        gridLayout = (LinearLayout) view.findViewById(R.id.trainInfo);
        gridLayout.setVisibility(View.VISIBLE);
        gridLayout = (LinearLayout) view.findViewById(R.id.running);
        gridLayout.setVisibility(View.VISIBLE);
        gridLayout = (LinearLayout) view.findViewById(R.id.head);
        gridLayout.setVisibility(View.VISIBLE);
    }
    public static void updateUi(ArrayList<infoDetail> arr){
        if(arr == null) {
            Toast.makeText(a, "Some Went Wrong :( Please verify Train Number", Toast.LENGTH_LONG).show();
            return ;
        }
        update((trainRouteInfo)arr.get(0));
        trainRouteListAdapter adapter = new trainRouteListAdapter(a, arr);
        listView.setAdapter(adapter);
    }
}
