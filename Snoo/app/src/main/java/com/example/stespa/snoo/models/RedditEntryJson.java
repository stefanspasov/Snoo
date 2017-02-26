package com.example.stespa.snoo.models;

/**
 * Created by stespa on 2017-02-24.
 */

public class RedditEntryJson {
    public RootData data;

    public static class RootData {
        public Child[] children;
    }

    public static class Child {
        public Data data;
    }

    public static class Data {
        public String subreddit;
        public String url;
        public Integer score;
        public String title;
    }
}





