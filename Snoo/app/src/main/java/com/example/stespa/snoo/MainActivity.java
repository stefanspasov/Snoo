package com.example.stespa.snoo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListAdapter eAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        final   ArrayList<RedditEntry> list = (ArrayList<RedditEntry>)extras.getSerializable("entries");

        recyclerView = (RecyclerView) findViewById(R.id.reddit_entries_view);
        eAdapter = new ListAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(eAdapter);
        recyclerView.addOnItemTouchListener(
               new RecyclerTouchListener(getApplicationContext() ,new RecyclerTouchListener.OnItemClickListener() {
                     @Override public void onItemClick(View view, int position) {
                           RedditEntry redditEntry= list.get(position);
                           // Fixes "URI signature match failed" bug with Reddit
                           String newUrl = redditEntry.getUrl().replaceAll("&amp;", "&");
                           Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newUrl));
                           startActivity(browserIntent);
                      }
               })
        );
    }
}
