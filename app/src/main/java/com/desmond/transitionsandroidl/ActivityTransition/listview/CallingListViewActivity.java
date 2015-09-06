package com.desmond.transitionsandroidl.ActivityTransition.listview;

import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.desmond.transitionsandroidl.BaseActivity;
import com.desmond.transitionsandroidl.R;
import com.desmond.transitionsandroidl.TransitionHelper;

public class CallingListViewActivity extends BaseActivity implements TextAdapter.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling_list_view);

        setupToolbar();

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvList);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        TextAdapter adapter = new TextAdapter();
        adapter.setOnClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view, int position) {
        ActivityOptionsCompat activityOptionsCompat
                = TransitionHelper.makeOptionsCompat(this, Pair.create(view, getString(R.string.list_item)));
    }
}
