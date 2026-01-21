package com.example.restapi;

import static com.example.restapi.MainActivity.TAG;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.lang.String.valueOf;

import android.os.AsyncTask;
import android.util.Log;

import com.example.restapi.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

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
    ActivityMainBinding b;

    public MyAsyncTask(ActivityMainBinding b) {
        super();
        this.b = b;
    }

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
            JSONObject personName = person.getJSONObject("name");

            String name = format("%s. %s %s",
                    personName.getString("title"),
                    personName.getString("first"),
                    personName.getString("last"));
            String email = person.getString("email");
            String gender = person.getString("gender");

            JSONObject picture = person.getJSONObject("picture");
            String link = picture.getString("large");

            publishProgress(name, 0);
            publishProgress(email, 1);
            publishProgress(gender, 2);
            publishProgress(link, 3);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
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

        Log.d(TAG, "values[0]="+values[1]);

        switch (parseInt(valueOf(values[1]))) {
            case 0:
                b.name.setText("Name: " + values[0]);
                break;
            case 1:
                b.email.setText("Email: " + values[0]);
                break;
            case 2:
                b.gender.setText("Gender: " + values[0]);
                break;
            case 3:
                Picasso.get().load(values[0].toString()).into(b.image);
                break;
        }

        super.onProgressUpdate(values);
    }
}
