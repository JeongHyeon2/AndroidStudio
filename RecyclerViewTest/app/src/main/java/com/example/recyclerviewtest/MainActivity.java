package com.example.recyclerviewtest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TabListAdapter mAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_tab_list);

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        mAdapter = new TabListAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(mAdapter));

        mItemTouchHelper.attachToRecyclerView(mRecyclerView);


        ArrayList<TabItem> items = new ArrayList<>();
        TabItem item1 = new TabItem("SUBSCRIBE",0);
        TabItem item2 = new TabItem("BEST",0);
        TabItem item3 = new TabItem("MUSIC",0);
        TabItem item4 = new TabItem("SPORTS",0);
        TabItem item5 = new TabItem("GAME",0);
        TabItem item6 = new TabItem("MOVIE",0);
        TabItem item7 = new TabItem("NEWS",0);
        TabItem item8 = new TabItem("LIVE",0);
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);
        items.add(item7);
        items.add(item8);

        mAdapter.setItems(items);

    }

}
