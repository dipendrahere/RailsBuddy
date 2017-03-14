package com.example.dipendra.railbuddy.util;

import com.example.dipendra.railbuddy.features.trainRouteList;
import com.example.dipendra.railbuddy.features.train_between_stations_list;
import com.example.dipendra.railbuddy.info.infoDetail;
import com.example.dipendra.railbuddy.info.trainBetweenStationInfo;
import com.example.dipendra.railbuddy.info.trainRouteInfo;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by ydeepak on 20/11/16.
 */

public class apiFetch extends AsyncTask<URL, Void, ArrayList<infoDetail>> {

    private String REQUEST_URL = "";
    private int id = 0;
    final static String apiKey = "cer1dfi4";
    public static final String LOG_TAG = apiFetch.class.getSimpleName();
    Activity context;
    // public abstract void updateUi(infoDetail info, int id);
    private ProgressDialog dialog;

    public apiFetch(int id, Activity context, String... str) {
        this.context = context;
        if (id == 1) {
            this.id = id;
            dialog = new ProgressDialog((train_between_stations_list) context);
            REQUEST_URL = "http://api.railwayapi.com/between/source/" + str[0] + "/dest/" + str[1] + "/date/" + str[2] + "/apikey/" + apiKey + "/";

        } else if (id == 2) {
            this.id = id;
            dialog = new ProgressDialog(((trainRouteList) context));
            REQUEST_URL = "http://api.railwayapi.com/route/train/" + str[0] + "/apikey/" + apiKey + "/";
        }
        Log.i(LOG_TAG, REQUEST_URL);
        Log.i(LOG_TAG, "Success1111");
    }


    @Override
    protected void onPreExecute() {
        this.dialog.setMessage("Please wait...");
        this.dialog.show();
    }

    @Override
    protected ArrayList<infoDetail> doInBackground(URL... urls) {
        // Create URL object
        URL url = createUrl(REQUEST_URL);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
            Log.i(LOG_TAG, jsonResponse);
        } catch (IOException e) {
            // TODO Handle the IOException
            Log.i(LOG_TAG, e.toString());
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object
        ArrayList<infoDetail> generalTrainInfo = null;

        if (id == 1) {
            generalTrainInfo = extractTrainBetweenStation(jsonResponse);
        } else if (id == 2) {
            generalTrainInfo = extractTrainRoute(jsonResponse);
        }

        // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
        return generalTrainInfo;
    }


    @Override
    protected void onPostExecute(ArrayList<infoDetail> generalInfo) {
        if (id == 1)
            train_between_stations_list.updateUi(generalInfo);
        else if (id == 2) {
            trainRouteList.updateUi(generalInfo);
        }
        // Toast.makeText, "Success "+id, Toast.LENGTH_LONG).show();
        if (dialog.isShowing()) {
            dialog.dismiss();
        }

        Log.i(LOG_TAG, "Success " + id);
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } catch (IOException e) {
            // TODO: Handle the exception
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    private ArrayList<infoDetail> extractTrainBetweenStation(String json) {
        try {
            JSONObject baseJsonResponse = new JSONObject(json);
            JSONArray jsonArray = baseJsonResponse.getJSONArray("train");
            if (jsonArray.length() == 0) {
                return null;
            }
            JSONObject specificTrain;
            JSONArray days;
            String name = "", number = "", arrTime = "", depTime = "", source = "", destination = "";
            String[] runningDays = new String[7];
            ArrayList<infoDetail> infoinfo = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                specificTrain = jsonArray.getJSONObject(i);
                name = specificTrain.getString("name");
                number = specificTrain.getString("number");
                arrTime = specificTrain.getString("src_departure_time");
                depTime = specificTrain.getString("dest_arrival_time");
                source = specificTrain.getJSONObject("from").getString("name") + " " + specificTrain.getJSONObject("from").getString("code");
                destination = specificTrain.getJSONObject("to").getString("name") + " " + specificTrain.getJSONObject("to").getString("code");

                days = specificTrain.getJSONArray("days");
                runningDays = new String[7];
                for (int j = 0; j < days.length(); j++) {
                    runningDays[j] = days.getJSONObject(j).getString("runs");
                    if (runningDays.equals("Y"))
                        Log.i("Running Status", runningDays[j]);
                }
                infoinfo.add(new trainBetweenStationInfo(number, name, runningDays, source, destination, depTime, arrTime));
            }
            return infoinfo;
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the trainBetweenData JSON results", e);
        }
        return null;
    }

    private ArrayList<infoDetail> extractTrainRoute(String json) {
        try {
            JSONObject baseJsonResponse = new JSONObject(json);
            JSONObject obj = baseJsonResponse.getJSONObject("train");
            JSONArray runningDays = obj.getJSONArray("days");
            String[] running = new String[7];
            for (int i = 0; i < runningDays.length(); i++) {
                running[i] = runningDays.getJSONObject(i).getString("runs");
            }
            String name, number;
            name = obj.getString("name");
            number = obj.getString("number");
            String stName, stCode, srno, scharr, schdep, day, distance;
            JSONArray arr = baseJsonResponse.getJSONArray("route");
            if (arr.length() == 0) {
                return null;
            }
            ArrayList<infoDetail> infoinfo = new ArrayList<>();
            trainRouteInfo t;
            for (int i = 0; i < arr.length(); i++) {
                obj = arr.getJSONObject(i);
                stName = obj.getString("fullname");
                stCode = obj.getString("code");
                srno = String.valueOf(obj.getInt("no"));
                scharr = obj.getString("scharr");
                schdep = obj.getString("schdep");
                day = String.valueOf(obj.getInt("day"));
                distance = String.valueOf(obj.getInt("distance"));
                t = new trainRouteInfo(stName, stCode, srno, scharr, schdep, day, distance);
                t.setTrainName(name);
                t.setTrainNumber(number);
                t.setRunningDays(running);
                infoinfo.add(t);
            }

            return infoinfo;

        } catch (JSONException e) {
            Log.e(LOG_TAG, e.toString());
        }

        return null;
    }
}

