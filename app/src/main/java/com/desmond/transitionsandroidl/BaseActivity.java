package com.desmond.transitionsandroidl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

    protected void initTransition() {}

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
    protected void onDestroy() {
        TransitionHelper.of(this).removeListener(this);
        super.onDestroy();
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
