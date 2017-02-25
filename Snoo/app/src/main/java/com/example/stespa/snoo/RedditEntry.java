package com.example.stespa.snoo;

import java.io.Serializable;

/**
 * Created by stespa on 2017-02-24.
 */

public class RedditEntry implements Serializable{
    private  String Title="";
    private  int Score=0;
    private  String SubReddit="";
    private String Url;

    public RedditEntry()
    {}

    public RedditEntry(String title, int score, String subReddit, String url)
    {
        setTitle(title);
        setScore(score);
        setSubReddit(subReddit);
        setUrl(url);
    }

    /*********** Set Methods ******************/

    public void setTitle(String Title)
    {
        this.Title = Title;
    }

    public void setSubReddit(String SubReddit)
    {
        this.SubReddit = SubReddit;
    }

    public void setScore(int Score)
    {
        this.Score = Score;
    }

    public void setUrl(String url) { this.Url = url;  }

    /*********** Get Methods ****************/

    public String getTitle()
    {
        return this.Title;
    }

    public String getSubReddit()
    {
        return this.SubReddit;
    }

    public int getScore()
    {
        return this.Score;
    }

    public String getUrl() { return this.Url; }
}
