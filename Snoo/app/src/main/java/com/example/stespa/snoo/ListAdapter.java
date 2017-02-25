package com.example.stespa.snoo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by stespa on 2017-02-24.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>  {

    private List<RedditEntry> redditEntries;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, score, subreddit;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            subreddit = (TextView) view.findViewById(R.id.subreddit);
            score = (TextView) view.findViewById(R.id.score);
        }
    }

    public ListAdapter(List<RedditEntry> entries) {
        this.redditEntries = entries;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reddit_entry, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RedditEntry entry = redditEntries.get(position);
        holder.title.setText(entry.getTitle());
        holder.score.setText(Integer.toString(entry.getScore()));
        holder.subreddit.setText(entry.getSubReddit());
    }

    @Override
    public int getItemCount() {
        return redditEntries.size();
    }
}