package com.example.stespa.snoo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by stespa on 2017-02-24.
 */

public class ListAdapter extends ArrayAdapter<RedditEntry>  {

    private static class VieHolder {
        TextView txtName;
        TextView txtType;
        TextView txtVersion;
    }

    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapter(Context context, int resource, List<RedditEntry> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.reddit_entry, null);
        }

        RedditEntry p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.id);
            TextView tt2 = (TextView) v.findViewById(R.id.categoryId);
            TextView tt3 = (TextView) v.findViewById(R.id.description);

            if (tt1 != null) {
                tt1.setText(p.getTitle());
            }

            if (tt2 != null) {
                tt2.setText(String.valueOf(p.getScore()));
            }

            if (tt3 != null) {
                tt3.setText(p.getSubReddit());
            }
        }

        return v;
    }
}