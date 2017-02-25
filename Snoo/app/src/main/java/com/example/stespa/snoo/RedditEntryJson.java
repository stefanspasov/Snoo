package com.example.stespa.snoo;

/**
 * Created by stespa on 2017-02-24.
 */

public class RedditEntryJson {
    public RootData data;

    static class RootData{
        public Child[] children;
    }

    static class Child
    {
        public Data data;
    }

    static class Data
    {
        public String subreddit;
        public String url;
        public Integer score;
        public String title;
    }
}





