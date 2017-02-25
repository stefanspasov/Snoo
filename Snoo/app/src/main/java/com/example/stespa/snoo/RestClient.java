package com.example.stespa.snoo;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by stespa on 2017-02-24.
 */

public class RestClient extends AsyncTask<String, Integer, String> {
    String charset = "UTF-8";
    String param ="20";
    @Override
    protected String doInBackground(String... params) {
        if(android.os.Debug.isDebuggerConnected())
            android.os.Debug.waitForDebugger();
        String targetURL = params[0];
        String httpMethod = params[1];
        URL url;
        HttpURLConnection connection = null;
        try {

            String query = String.format("limit=%s",
                    URLEncoder.encode(param, charset));
            url = new URL(targetURL + "?" + query);

            connection = (HttpURLConnection) url.openConnection();
         //   connection.setRequestProperty("Accept-Charset", charset);
            InputStream in = new BufferedInputStream(connection.getInputStream());
            BufferedReader rd = new BufferedReader(new InputStreamReader(in));
            String line;
            StringBuffer response = new StringBuffer();
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
        }
    }
}
