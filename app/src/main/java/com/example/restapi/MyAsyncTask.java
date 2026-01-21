package com.example.restapi;

import static com.example.restapi.MainActivity.TAG;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MyAsyncTask extends AsyncTask {
    InputStream inputStream;
    URL url = null;

    @Override
    protected Object doInBackground(Object[] objects) {
        Log.d(TAG, "doInBackground: ");

        try {
            url = new URL("https://randomuser.me/api");
            inputStream = url.openStream();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        byte[] buffer = new byte[4096];
        StringBuilder stringBuilder = new StringBuilder();

        while (true) {
            try {
                if (!(inputStream.read(buffer) > 0))
                    break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            stringBuilder.append(new String(buffer));
        }
        try {
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            JSONArray results = jsonObject.getJSONArray("results");
            JSONObject person = results.getJSONObject(0);


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }



        publishProgress(stringBuilder.toString());
        return null;
    }

    @Override
    protected void onPreExecute() {
        Log.d(TAG, "onPreExecute: ");
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        Log.d(TAG, "onProgressUpdate: ");
        Log.d(TAG, values[0].toString());
        super.onProgressUpdate(values);
    }
}
