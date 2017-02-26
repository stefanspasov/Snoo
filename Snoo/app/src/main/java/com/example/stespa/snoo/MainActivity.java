package com.example.stespa.snoo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.stespa.snoo.helpers.DividerItemDecoration;
import com.example.stespa.snoo.helpers.EntriesAdapter;
import com.example.stespa.snoo.helpers.RecyclerTouchListener;
import com.example.stespa.snoo.models.RedditEntry;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.LayoutManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        final ArrayList<RedditEntry> entries = (ArrayList<RedditEntry>) extras.getSerializable("entries");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reddit_entries_view);
        EntriesAdapter entriesAdapter = new EntriesAdapter(entries);
        LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(entriesAdapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerTouchListener(getApplicationContext(), new RecyclerTouchListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (entries != null) {
                            RedditEntry redditEntry = entries.get(position);
                            // Fixes "URI signature match failed" bug with Reddit
                            String newUrl = redditEntry.getUrl().replaceAll("&amp;", "&");
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newUrl));
                            startActivity(browserIntent);
                        }
                    }
                })
        );
    }
}
