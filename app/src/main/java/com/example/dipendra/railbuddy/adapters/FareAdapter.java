package com.example.dipendra.railbuddy.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dipendra.railbuddy.R;
import com.example.dipendra.railbuddy.models.Trains;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dipendra on 24/11/16.
 */

public class FareAdapter extends BaseAdapter {
    Context context;
    int choice;
    int res;
    ArrayList<Trains> trains;

    public FareAdapter(Context context, int resource, ArrayList<Trains> objects) {

        this.context = context;
        this.res = resource;
        this.trains = objects;
    }

    @Override
    public int getCount() {if(trains.get(0).getFarelist().size() == 0){
        Toast.makeText(context,"No data found",Toast.LENGTH_SHORT).show();
        return 0;
    }
        return trains.get(0).getFarelist().size();

    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {try{
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(res, viewGroup, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.ticket);
            String Quota = trains.get(0).getQuota().getCode().toLowerCase();
            if(Quota.equalsIgnoreCase("gn")){
                choice = 0;
                imageView.setBackgroundResource(R.drawable.gen);
            }
            else {
                choice = 1;
                imageView.setBackgroundResource(R.drawable.tatkal);
            }
            TextView tv = (TextView) view.findViewById(R.id.farerate);
        TextView tvs = (TextView) view.findViewById(R.id.rowfaretype);
        tvs.setText(""+trains.get(choice).getFare().get(i).getName().toString());
            tv.setText(context.getResources().getString(R.string.rs)+" "+trains.get(choice).getFare().get(i).getFare().toString());}
    catch (Exception e){
        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
    }
            return view;
    }
}
