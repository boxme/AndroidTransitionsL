package com.desmond.transitionsandroidl.ActivityTransition;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.desmond.transitionsandroidl.R;

public class BaseActivity extends AppCompatActivity {

    Toolbar mToolBar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
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
    }

    public View getToolbar() {
        return mToolBar;
    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
        super.onBackPressed();
    }
}
