package com.desmond.transitionsandroidl.ActivityTransition.gridview;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.desmond.transitionsandroidl.ActivityTransition.BaseActivity;
import com.desmond.transitionsandroidl.R;
import com.desmond.transitionsandroidl.adapter.RadioHeadAdapter;

public class CallingGridViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling_grid_view);
        setupToolbar();

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvFeed);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        RadioHeadAdapter adapter = new RadioHeadAdapter();
        recyclerView.setAdapter(adapter);
    }
}
