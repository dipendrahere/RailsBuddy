package com.example.dipendra.railbuddy;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dipendra.railbuddy.adapters.LiveAdapter;
import com.example.dipendra.railbuddy.models.Trains;
import com.example.dipendra.railbuddy.utils.Task;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LiveStatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_status);
        String url = getIntent().getExtras().get("url").toString();
        Task t = new Task(LiveStatusActivity.this, 1);
        URL u = null;
        try {
            u = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Trains trains = new Trains();
        try {
            trains = t.execute(u).get();
        } catch (InterruptedException e) {
            Toast.makeText(this, "Fetch Unsuccessful", Toast.LENGTH_SHORT).show();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        TextView tvv = (TextView) findViewById(R.id.trainNolive);
        tvv.setText(trains.getTrain_number()+"");//TODO ::ff
        ListView lv = (ListView) findViewById(R.id.listcurrent);
        ArrayList<Trains> a = new ArrayList<Trains>();
        a.add(trains);
        LiveAdapter adapter = new LiveAdapter(this, R.layout.rowlivestatus, a);
        if(trains.getPosition().equals("-")){
            Toast.makeText(this, "Date May be Wrong!", Toast.LENGTH_SHORT).show();
        }
        LinearLayout relativeLayout = (LinearLayout) findViewById(R.id.activity_live_status);
        Snackbar.make(relativeLayout, trains.getPosition()+"       ",Snackbar.LENGTH_INDEFINITE).show();
        lv.setAdapter(adapter);}
        catch (Exception e){
            Toast.makeText(this, "Something Went Wrong :(", Toast.LENGTH_SHORT).show();
        }
    }
}


