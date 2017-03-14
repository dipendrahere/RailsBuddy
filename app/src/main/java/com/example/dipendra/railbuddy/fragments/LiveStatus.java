package com.example.dipendra.railbuddy.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dipendra.railbuddy.LiveStatusActivity;
import com.example.dipendra.railbuddy.R;
import com.example.dipendra.railbuddy.models.Trains;
import com.example.dipendra.railbuddy.utils.DateDialog;
import com.example.dipendra.railbuddy.utils.Task;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;


public class LiveStatus extends Fragment implements View.OnClickListener {
    View v;
    ArrayList<Integer> date;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;


    public LiveStatus() {

    }

    public static LiveStatus newInstance(String param1, String param2) {
        LiveStatus fragment = new LiveStatus();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_live_status, container, false);
        ImageButton imageView = (ImageButton) v.findViewById(R.id.LiveStatusCalender);
        imageView.setOnClickListener(this);
        v.findViewById(R.id.fetchlivestatus).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        try{
        if(view.getId() == R.id.LiveStatusCalender || view.getId() == R.id.dateFareEnquiry){
            date = new ArrayList<Integer>();
            date = new DateDialog().fetchdate(getActivity(),  (EditText) v.findViewById(R.id.dateFareEnquiry));
        }
        if(view.getId() == R.id.fetchlivestatus){
            URL url = null;
            String apikey = getString(R.string.apikey);
            String trainNo = "";
            EditText ed = (EditText) v.findViewById(R.id.trainNoLiveStatus);
            trainNo = ed.getText().toString();
            if(trainNo.length() != 5){
                ed.requestFocus();
                Toast.makeText(getContext(), "Invalid Train number", Toast.LENGTH_SHORT).show();
            }
            else {
                Date d = new Date(date.get(2) - 1900, date.get(1), date.get(0));
                String dates = new SimpleDateFormat("yyyyMMdd").format(d);
                String str = "http://api.railwayapi.com/live/train/" + trainNo + "/doj/" + dates + "/apikey/" + apikey + "/";
          //      Toast.makeText(getContext(), str, Toast.LENGTH_LONG).show();
                Intent i = new Intent(getActivity(), LiveStatusActivity.class);
                i.putExtra("url", "" + str);
                startActivity(i);
            }
        }
    }catch (Exception e ){
            Toast.makeText(getContext(),"Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}
