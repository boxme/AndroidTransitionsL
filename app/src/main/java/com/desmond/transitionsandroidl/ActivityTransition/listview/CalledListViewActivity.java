package com.desmond.transitionsandroidl.ActivityTransition.listview;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.desmond.transitionsandroidl.BaseActivity;
import com.desmond.transitionsandroidl.R;

public class CalledListViewActivity extends BaseActivity {

    private TextView        mTitleTextView;
    private TextView        mDetailsBodyTextView;
    private OverScrollView  mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_called_list_view);
        attachUI();
        setupToolbar();
    }

    private void attachUI() {
        mTitleTextView = (TextView) findViewById(R.id.detail_title);
        mDetailsBodyTextView = (TextView) findViewById(R.id.detail_body);
        mScrollView = (OverScrollView) findViewById(R.id.overscroll_view);

        final String textDetails = getString(R.string.long_text);
        String text = textDetails + " " + textDetails + " " + textDetails + " " + textDetails;
        mDetailsBodyTextView.setText(text);
    }

    @Override
    public void onBeforeViewsShow() {
        mDetailsBodyTextView.setAlpha(0);
        new Handler().postDelayed(new Runnable(){
            public void run() {
                mDetailsBodyTextView.animate().alpha(1).start();
            }
        }, 500);
    }

    @Override
    public void onBeforeEnter() {}

    @Override
    public void onBeforeReturn() {}

    @Override
    public void onAfterEnter() {}
}
