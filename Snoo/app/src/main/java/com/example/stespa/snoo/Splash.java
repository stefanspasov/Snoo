package com.example.stespa.snoo;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by stespa on 2017-02-24.
 */

public class Splash extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        String url = "https://www.reddit.com/r/gaming/top.json";
        String queryParams = "";
        String charset = "UTF-8";
        String limit ="20";
        try {
            queryParams = String.format("limit=%s",
                    URLEncoder.encode(limit, charset));
        }
        catch (Exception e) {
         // TODO LOG
        }

        new RestClient()
        {
            @Override
            public void onPostExecute(String result)
            {
                try {
                    android.os.Debug.waitForDebugger();
                    ArrayList<RedditEntry> entries = new ArrayList<RedditEntry>();
                    Gson gson = new GsonBuilder().create();

                    RedditEntryJson redditJson = gson.fromJson(result, RedditEntryJson.class);
                    for (RedditEntryJson.Child entry : redditJson.data.children) {
                        RedditEntry newEntry = new RedditEntry(entry.data.title, entry.data.score, entry.data.subreddit, entry.data.url);
                        entries.add(newEntry);
                    }
                    Intent openStart = new Intent("android.intent.action.MAINPAGE");
                    openStart.putExtra("entries", entries);
                    startActivity(openStart);
                }
                catch (Exception e)
                {
                    // TODO log
                }
            }
        }.execute(new String[]{url, queryParams});
    }
}
