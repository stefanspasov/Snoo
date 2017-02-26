package com.example.stespa.snoo.helpers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stespa.snoo.models.RedditEntry;
import com.example.stespa.snoo.R;

import java.util.List;

/**
 * Created by stespa on 2017-02-24.
 */

public class EntriesAdapter extends RecyclerView.Adapter<EntriesAdapter.EntriesViewHolder> {

    final private List<RedditEntry> mRedditEntries;

    public EntriesAdapter(List<RedditEntry> entries) {
        this.mRedditEntries = entries;
    }

    @Override
    public EntriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reddit_entry, parent, false);

        return new EntriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EntriesViewHolder holder, int position) {
        RedditEntry entry = mRedditEntries.get(position);
        holder.title.setText(entry.getTitle());
        holder.score.setText(Integer.toString(entry.getScore()));
        holder.subreddit.setText(entry.getSubReddit());
    }

    @Override
    public int getItemCount() {
        return mRedditEntries.size();
    }

    public class EntriesViewHolder extends RecyclerView.ViewHolder {
        final TextView title, score, subreddit;

        public EntriesViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            subreddit = (TextView) view.findViewById(R.id.subreddit);
            score = (TextView) view.findViewById(R.id.score);
        }
    }
}