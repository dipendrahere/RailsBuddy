package com.example.dipendra.railbuddy.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import com.example.dipendra.railbuddy.models.Trains;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dipendra on 22/11/16.
 */

public class Task extends AsyncTask<URL, String, Trains> {

    int code;
    Activity context;
    ProgressDialog pd;
    Trains t;
    public Task(Activity context, int code){
        this.context = context;
        this.code = code;
    }

    @Override
    protected void onPreExecute() {
        //super.onPreExecute();


        pd = new ProgressDialog(context);
        pd.setMessage("Loading! Please Wait..");
        pd.show();
        t = new Trains();
    }

    private void showDialog() {
        pd.show();
    }

    @Override
        protected Trains doInBackground(URL... urls) {
            Gson gson = new Gson();
//            pd.show();
            Trains train = new Trains();
            StringBuffer json = new StringBuffer("");
            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader;
            InputStreamReader inputStreamReader = null;
            try {
                httpURLConnection=(HttpURLConnection) urls[0].openConnection();
                inputStreamReader = new InputStreamReader(urls[0].openStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                String temp = "";
                while((temp = bufferedReader.readLine()) != null){
                    json.append(temp);
                }
                train = gson.fromJson(json.toString(), Trains.class);

            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                httpURLConnection.disconnect();
            }
            return train;
        }

    private void livestatus(Trains train){
        pd.dismiss();
        Toast.makeText(context, train.getPosition(), Toast.LENGTH_LONG).show();
    }

        @Override
        protected void onPostExecute(Trains train) {
            super.onPostExecute(train);
            if(code == 1){
                livestatus(train);
            }
            else if(code == 2){
                seatavailability(train);
            }
            else if(code == 3){
                trainfare(train);
            }
            else if(code == 4){

            }

        }

    private void trainfare(Trains train) {
        pd.dismiss();
    }

    private void seatavailability(Trains train) {
        //TODO: add code
    }
}

