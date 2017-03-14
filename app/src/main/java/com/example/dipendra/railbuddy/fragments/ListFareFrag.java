package com.example.dipendra.railbuddy.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dipendra.railbuddy.R;
import com.example.dipendra.railbuddy.adapters.FareAdapter;
import com.example.dipendra.railbuddy.models.Trains;
import com.example.dipendra.railbuddy.utils.Task;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutionException;

public class ListFareFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListFareFrag() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFareFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFareFrag newInstance(String param1, String param2) {
        ListFareFrag fragment = new ListFareFrag();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list_fare, container, false);
        Bundle b = this.getArguments();
        String date = b.getString("date","00000000");
        String from = b.getString("from",null);
        String to = b.getString("to", null);
        String trainno = b.getString("trainno",null);
        Trains gn=new Trains();
        Trains ck = new Trains();
        String quota;
        quota = b.getString("quota",null);
        Trains pt = new Trains();
        URL ugn = null, uck = null, upt = null;
        String pre = "http://api.railwayapi.com/fare/train/"+trainno+"/source/"+from+"/dest/"+to+"/age/18/quota/";
        String lst = "/doj/"+date+"/apikey/"+getString(R.string.apikey)+"/";
        if(quota.equals("gn")){try {
            ugn = new URL(pre+"gn"+lst);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }}
        else if(quota.equals("ck")){
        try {
            uck = new URL(pre+"ck"+lst);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }}
        else{
            Toast.makeText(getContext(), "Invalid!", Toast.LENGTH_SHORT).show();
        }
        try {
            if(quota.equals("gn")){
            gn = new Task(getActivity(),3).execute(ugn).get();}
            else{
            ck = new Task(getActivity(), 3).execute(uck).get();}
        } catch (InterruptedException e) {
            Toast.makeText(getContext(),"Unsuccessful Attemp!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Something went wrong :(",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        ArrayList<Trains> trainses =new ArrayList<>();
        if(quota.equals("gn"))
        {
            trainses.add(gn);
        }
        else {
            trainses.add(ck);
        }
        ListView listView = (ListView) v.findViewById(R.id.listfare); FareAdapter fareAdapter = null;

            fareAdapter = new FareAdapter(getContext(), R.layout.row_fare, trainses);


        listView.setAdapter(fareAdapter);
        return v;
    }




}
