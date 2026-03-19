package com.example.restapi;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallAPI {

    public interface OnDataLoadedListener {
        void onDataLoaded(List<Item> items);
    }

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    public void execute(int count, OnDataLoadedListener listener) {
        executor.execute(() -> {
            try {
                URL url = new URL("https://randomuser.me/api/?results=" + count);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                List<Item> items = parseJson(response.toString());

                handler.post(() -> listener.onDataLoaded(items));

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private List<Item> parseJson(String jsonString) throws Exception {
        List<Item> list = new ArrayList<>();
        JSONObject root = new JSONObject(jsonString);
        JSONArray results = root.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            JSONObject p = results.getJSONObject(i);

            JSONObject name = p.getJSONObject("name");
            JSONObject picture = p.getJSONObject("picture");
            String fullName = String.format("%s. %s %s", name.getString("title"), name.getString("first"), name.getString("last"));

            list.add(
                    new Item(
                            fullName,
                            p.getString("email"),
                            picture.getString("large")
                    )
            );
        }
        return list;
    }
}