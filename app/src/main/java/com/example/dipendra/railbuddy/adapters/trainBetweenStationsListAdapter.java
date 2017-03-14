package com.example.dipendra.railbuddy.adapters;



import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dipendra.railbuddy.R;
import com.example.dipendra.railbuddy.info.infoDetail;
import com.example.dipendra.railbuddy.info.trainBetweenStationInfo;

import java.util.ArrayList;

/**
 * Created by ydeepak on 21/11/16.
 */

public class trainBetweenStationsListAdapter extends ArrayAdapter<infoDetail> {

    public trainBetweenStationsListAdapter(Activity a, ArrayList<infoDetail> arr) {
        super(a, 0, arr);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        trainBetweenStationInfo obj;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.train_between_stations_list_item, parent, false);
        }

        obj = (trainBetweenStationInfo) getItem(position);
        TextView t1, t2, t3, t4, t5, t6;
        LinearLayout gridLayout;
        t1 = (TextView) convertView.findViewById(R.id.trainName);
        t2 = (TextView) convertView.findViewById(R.id.trainNumber);
        t3 = (TextView) convertView.findViewById(R.id.sourceName);
        t4 = (TextView) convertView.findViewById(R.id.destinationName);
        t5 = (TextView) convertView.findViewById(R.id.depTime);
        t6 = (TextView) convertView.findViewById(R.id.arrTime);
        t1.setText(obj.getTrainName());
        t2.setText(obj.getTrainNumber());
        t3.setText(obj.getSource());
        t4.setText(obj.getDestination());
        t5.setText(obj.getDepTime());
        t6.setText(obj.getArrTime());
        gridLayout = (LinearLayout) convertView.findViewById(R.id.daysGrid);
        String[] arr = obj.getRunningDays();
        View view;

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            view = gridLayout.getChildAt(i);

            if (arr[i].equals("Y")) {
                view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.runningDay));
                Log.i("Yes", arr[i]);
            } else {
                Log.i("NO", "N");
                view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.daysColor));

            }
        }


        return convertView;
    }
}
