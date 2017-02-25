package com.example.stespa.snoo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * Created by stespa on 2017-02-24.
 */

public class RedditClient {
    String urlBase = "https://www.reddit.com/r/gaming/top.json";
    Integer numberOfEntries = 50;

    public ArrayList<RedditEntry> GetRedditEntries()
    {
        String url = urlBase;
        new RestClient()
        {
            @Override
            public void onPostExecute(String result)
            {
                android.os.Debug.waitForDebugger();
                ArrayList<RedditEntry> entries = new ArrayList<RedditEntry>();
                Gson gson = new GsonBuilder().create();
                RedditEntryJson redditJson = gson.fromJson(result, RedditEntryJson.class);
                for (RedditEntryJson.Child entry: redditJson.data.children) {
                    RedditEntry newEntry = new RedditEntry(entry.data.title, entry.data.score, entry.data.subreddit, entry.data.url);
                    entries.add(newEntry);
                };
            }
        }.execute(new String[]{url, "GET"});

        return null;
    }
}
