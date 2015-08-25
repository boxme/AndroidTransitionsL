package com.desmond.transitionsandroidl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class BaseActivity extends AppCompatActivity
        implements TransitionHelper.Source, TransitionHelper.TransitionListener {

    Toolbar mToolBar;
    TransitionHelper mTransitionHelper;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TransitionHelper.of(this).onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionHelper.init(this, savedInstanceState);
        TransitionHelper.of(this).addListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setupToolbar() {
        if (mToolBar == null) {
            mToolBar = (Toolbar) findViewById(R.id.toolbar);
        }

        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("");
    }

    public View getToolbar() {
        return mToolBar;
    }

    @Override
    public void onBackPressed() {
        TransitionHelper.of(this).onBackPressed();
        super.onBackPressed();
    }

    @Override
    public TransitionHelper getTransitionHelper() {
        return mTransitionHelper;
    }

    @Override
    public void setTransitionHelper(TransitionHelper transitionHelper) {
        mTransitionHelper = transitionHelper;
    }

    @Override
    public void onBeforeViewsShow() {}

    @Override
    public void onBeforeEnter() {}

    @Override
    public void onAfterEnter() {}

    @Override
    public boolean onBeforeBack() {
        return false;
    }

    @Override
    public void onBeforeReturn() {}
}
