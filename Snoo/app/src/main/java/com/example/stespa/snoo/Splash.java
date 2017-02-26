package com.example.stespa.snoo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.stespa.snoo.helpers.RestClient;
import com.example.stespa.snoo.models.RedditEntry;
import com.example.stespa.snoo.models.RedditEntryJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by stespa on 2017-02-24.
 */

public class Splash extends Activity {
    private String mUrl = "https://www.reddit.com/r/gaming/top.json";
    private String mLimit = "50";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        if (!isConnected()) {
            Toast.makeText(getApplicationContext(), "Network is unavailable.", Toast.LENGTH_LONG).show();
        } else {
            new RestClient() {
                @Override
                public void onPostExecute(String result) {
                    try {
                        Intent openStart = new Intent("android.intent.action.MAINPAGE");
                        openStart.putExtra("entries", getRedditEntriesFromResult(result));
                        startActivity(openStart);
                    } catch (Exception e) {
                        Log.e("splash", "Cannot open main page", e);
                    }
                }
            }.execute(mUrl, getQueryParams());
        }
    }

    private ArrayList<RedditEntry> getRedditEntriesFromResult(String result) {
        ArrayList<RedditEntry> entries = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        try {
            RedditEntryJson redditJson = gson.fromJson(result, RedditEntryJson.class);

            if (redditJson != null && redditJson.data != null) {
                for (RedditEntryJson.Child entry : redditJson.data.children) {
                    if (entry.data != null) {
                        RedditEntry newEntry = new RedditEntry(
                                entry.data.title,
                                entry.data.score,
                                entry.data.subreddit,
                                entry.data.url);
                        entries.add(newEntry);
                    }
                }
            }

            return entries;
        } catch (Exception e) {
            Log.e("splash", "Cannot parse reddit entries", e);
            return entries;
        }
    }

    private String getQueryParams() {
        String charset = "UTF-8";
        try {
            return String.format("limit=%s", URLEncoder.encode(mLimit, charset));
        } catch (Exception e) {
            Log.e("splash", "Cannot get query parameters", e);
            return "";
        }
    }

    private boolean isConnected()
    {
        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
