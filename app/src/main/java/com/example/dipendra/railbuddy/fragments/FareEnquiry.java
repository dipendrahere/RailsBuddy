package com.example.dipendra.railbuddy.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dipendra.railbuddy.R;
import com.example.dipendra.railbuddy.utils.DateDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FareEnquiry extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private View v;
    private ArrayList<Integer> dates;
    private EditText date;
    private String q;
    private ArrayList<String> quota;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private EditText from;
    private EditText to;
    private EditText trainno;

    public FareEnquiry() {

    }

    public static FareEnquiry newInstance(String param1, String param2) {
        FareEnquiry fragment = new FareEnquiry();
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
        quota = new ArrayList<>();
        quota.add("General Quota");
        quota.add("Tatkal Quota");
        v = inflater.inflate(R.layout.fragment_fare_enquiry, container, false);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, quota);
        Spinner s = (Spinner) v.findViewById(R.id.spinnerfare);
        s.setAdapter(arrayAdapter);
        s.setOnItemSelectedListener(this);
        from = (EditText) v.findViewById(R.id.fromFareEnquiry);
        to = (EditText) v.findViewById(R.id.toFareEnquiry);
        trainno = (EditText) v.findViewById(R.id.trainNoFareEnquiry);
        date = (EditText) v.findViewById(R.id.dateFareEnquiry);
        Button b = (Button) v.findViewById(R.id.fetchfare);
        ImageButton imageButton = (ImageButton) v.findViewById(R.id.FareEnquiryCalender);
        imageButton.setOnClickListener(this);
        b.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.FareEnquiryCalender) {
            dates = DateDialog.fetchdate(getActivity(), date);
        }
        if (view.getId() == R.id.fetchfare) {
            if (from.getText().toString().trim().length() == 0 || to.getText().toString().trim().length() == 0 || trainno.getText().toString().matches("") || date.getText().toString().matches("")) {
                Toast.makeText(getActivity(), "Please fill all the details!", Toast.LENGTH_LONG).show();
                if (trainno.getText().toString().trim().length() != 5) {
                    trainno.requestFocus();
                } else if (from.getText().toString().trim().length() == 0) {
                    from.requestFocus();
                } else if (to.getText().toString().trim().length() == 0) {
                    to.requestFocus();
                }
            } else {
                Bundle bndl = new Bundle();
                ListFareFrag f = new ListFareFrag();
                bndl.putString("from", from.getText().toString());
                bndl.putString("to", to.getText().toString());
                bndl.putString("trainno", trainno.getText().toString());
                Date d = new Date(dates.get(2) - 1900, dates.get(1), dates.get(0));
                String dates = new SimpleDateFormat("dd-MM-yyyy").format(d);
                Spinner s = (Spinner) v.findViewById(R.id.spinnerfare);
                bndl.putString("quota",q);
                bndl.putString("date", dates);
                f.setArguments(bndl);
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_main, f).commit();

            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(quota.get(i).equals("General Quota")){
            q = "gn";
        }
        else{
            q = "ck";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
