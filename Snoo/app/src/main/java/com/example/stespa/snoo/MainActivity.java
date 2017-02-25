package com.example.stespa.snoo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        final   ArrayList<RedditEntry> list = (ArrayList<RedditEntry>)extras.getSerializable("entries");

        ListAdapter customAdapter = new ListAdapter(this, R.layout.reddit_entry, list);
        ListView yourListView = (ListView) findViewById(R.id.listViewEntries);
        yourListView.setAdapter(customAdapter);
        yourListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                RedditEntry dataModel= list.get(position);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(dataModel.getUrl()));
                startActivity(browserIntent);
            }
        });
    }
}
