package com.example.dipendra.railbuddy.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dipendra.railbuddy.R;
import com.example.dipendra.railbuddy.info.infoDetail;
import com.example.dipendra.railbuddy.info.trainRouteInfo;

import java.util.ArrayList;

/**
 * Created by ydeepak on 21/11/16.
 */

public class trainRouteListAdapter extends ArrayAdapter<infoDetail> {

    public trainRouteListAdapter(Activity a, ArrayList<infoDetail> arr) {
        super(a, 0, arr);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        trainRouteInfo obj;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_train_route, parent, false);
        }

        obj = (trainRouteInfo) getItem(position);
        if(position%2 == 0) {
            convertView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.even));
        }else {
            convertView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.odd));
        }
        TextView t1, t2, t3, t4, t5, t6, t7;
        t1 = (TextView) convertView.findViewById(R.id.srno);
        t2 = (TextView) convertView.findViewById(R.id.code);
        t3 = (TextView) convertView.findViewById(R.id.stName);
        t4 = (TextView) convertView.findViewById(R.id.scharr);
        t5 = (TextView) convertView.findViewById(R.id.schdep);
        t6 = (TextView) convertView.findViewById(R.id.day);
        t7 = (TextView) convertView.findViewById(R.id.distance);

        t1.setText(obj.getSrno());
        t2.setText(obj.getCode());
        t3.setText(obj.getFullName());
        t4.setText(obj.getScharr());
        t5.setText(obj.getSchdep());
        t6.setText(obj.getDay());
        t7.setText(obj.getDistance());
        return convertView;
    }
}
