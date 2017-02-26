package com.example.stespa.snoo.models;

import java.io.Serializable;

/**
 * Created by stespa on 2017-02-24.
 */

public class RedditEntry implements Serializable {
    private String mTitle = "";
    private int mScore = 0;
    private String mSubReddit = "";
    private String mUrl;

    public RedditEntry() {
        // Needed for Serializable
    }

    public RedditEntry(String title, int score, String subReddit, String url) {
        setTitle(title);
        setScore(score);
        setSubReddit(subReddit);
        setUrl(url);
    }

    /***********
     * Get Methods
     ****************/

    public String getTitle() {
        return this.mTitle;
    }

    /***********
     * Set Methods
     ******************/

    private void setTitle(String Title) {
        this.mTitle = Title;
    }

    public String getSubReddit() {
        return this.mSubReddit;
    }

    private void setSubReddit(String SubReddit) {
        this.mSubReddit = SubReddit;
    }

    public int getScore() {
        return this.mScore;
    }

    private void setScore(int Score) {
        this.mScore = Score;
    }

    public String getUrl() {
        return this.mUrl;
    }

    private void setUrl(String url) {
        this.mUrl = url;
    }
}
