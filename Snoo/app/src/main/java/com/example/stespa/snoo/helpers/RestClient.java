package com.example.stespa.snoo.helpers;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by stespa on 2017-02-24.
 */

public class RestClient extends AsyncTask<String, Integer, String> {

    @Override
    protected String doInBackground(String... params) {
        if(params.length != 2)
        {
            return null;
        }
        String targetURL = params[0];
        String query = params[1];
        URL url;
        BufferedReader rd = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(targetURL + "?" + query);
            connection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(connection.getInputStream());
            rd = new BufferedReader(new InputStreamReader(in));

            String line;
            StringBuilder response = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                System.out.println("response" + line);
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (rd != null) {
                try {
                    rd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
