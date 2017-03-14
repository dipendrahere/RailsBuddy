package com.example.dipendra.railbuddy.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dipendra.railbuddy.R;
import com.example.dipendra.railbuddy.models.Trains;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dipendra on 26/11/16.
 */

public class LiveAdapter extends BaseAdapter {
    private ArrayList<Trains.Route> route;
    int res;
    View v;
    private Context context;
    public LiveAdapter(Context context, int resource, ArrayList<Trains> objects) {
        this.route = objects.get(0).getRoute();
        //this.trains = objects.get(0);
        this.context=context;
        this.res = resource;
    }

    @Override
    public int getCount() {if(route.size() == 0){
        Toast.makeText(context, "No data fetched", Toast.LENGTH_LONG).show();
        return 0;
    }
        return route.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try{
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = layoutInflater.inflate(res, parent, false);
        TextView name = (TextView) v.findViewById(R.id.rownamelive);
        TextView schdep = (TextView) v.findViewById(R.id.schdeprowlive);
        TextView actdep = (TextView) v.findViewById(R.id.actdeprowlive);
        TextView latemin = (TextView) v.findViewById(R.id.laterowlive);

        name.setText(route.get(position).getStation_().getName().toString());

        boolean hasar = route.get(position).isHas_arrived();
        boolean hasdp = route.get(position).isHas_departed();
        if(hasar == false && hasdp == false){
            v.setBackgroundResource(R.color.livegreen);
            actdep.setText("-");
            latemin.setText("-");
            schdep.setText("-");
        }
        else if(hasar ==true && hasdp == false){
            actdep.setText("-");
            if(route.get(position).getLatemin() <= 0){
                latemin.setTextColor(context.getResources().getColor(R.color.textgreen));
                latemin.setText(""+route.get(position).getLatemin()+" mins");
            }
            else{
                latemin.setTextColor(context.getResources().getColor(R.color.textrred));
                latemin.setText(""+route.get(position).getLatemin()+" mins");
            }
            v.setBackgroundResource(R.color.liveyellow);
            schdep.setText(route.get(position).getActarr().toString());
        }
        else if(hasar == true && hasdp == true){
            if(route.get(position).getLatemin() <= 0){
                latemin.setTextColor(context.getResources().getColor(R.color.textgreen));
                int x = route.get(position).getLatemin();
                latemin.setText(""+x+" mins");
            }
            else{
                latemin.setTextColor(context.getResources().getColor(R.color.textrred));
                latemin.setText(""+route.get(position).getLatemin()+" mins");
            }
            schdep.setText(route.get(position).getActarr().toString());
            actdep.setText(route.get(position).getActdep().toString());
            v.setBackgroundResource(R.color.livered);
        }
        if(position == 0){
            schdep.setText("Source");
        }
        return v;}
        catch (Exception e){
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}
