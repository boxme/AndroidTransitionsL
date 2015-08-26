package com.desmond.transitionsandroidl.ActivityTransition.gridview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.desmond.transitionsandroidl.BaseActivity;
import com.desmond.transitionsandroidl.R;
import com.desmond.transitionsandroidl.TransitionHelper;
import com.desmond.transitionsandroidl.adapter.RadioHeadAdapter;

public class CallingGridViewActivity extends BaseActivity implements RadioHeadAdapter.OnClickListener {

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
        adapter.setOnClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view, int position) {
        startActivityWithTransitions(view, position);
    }

    private void startActivityWithTransitions(View view, int position) {
        ActivityOptionsCompat activityOptionsCompat
                = TransitionHelper.makeOptionsCompat(this, Pair.create(view, getString(R.string.hero_image)));

//        Intent intent = new Intent(this, CalledGridViewActivity.class);
        Intent intent = new Intent(this, CalledDetailsViewActivity.class);

        intent.putExtra(CalledGridViewActivity.POSITION, position);
        startActivity(intent, activityOptionsCompat.toBundle());
    }
}
