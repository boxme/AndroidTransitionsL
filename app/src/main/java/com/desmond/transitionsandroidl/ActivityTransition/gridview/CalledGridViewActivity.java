package com.desmond.transitionsandroidl.ActivityTransition.gridview;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.desmond.transitionsandroidl.BaseActivity;
import com.desmond.transitionsandroidl.R;
import com.desmond.transitionsandroidl.TransitionHelper;
import com.desmond.transitionsandroidl.view.SquareImageView;

public class CalledGridViewActivity extends BaseActivity {

    public static final String POSITION = "position";

    LinearLayout mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_called_grid_view);
        TransitionHelper.of(this).addListener(this);

        final int position = getIntent().getIntExtra(POSITION, 0);
        final ImageView ivThumbnail = (SquareImageView) findViewById(R.id.ivThumbnail);
        switch (position) {
            case 0: {
                ivThumbnail.setImageResource(R.drawable.radiohead_art_1);
                break;
            }
            case 1: {
                ivThumbnail.setImageResource(R.drawable.radiohead_art_2);
                break;
            }
            case 2: {
                ivThumbnail.setImageResource(R.drawable.radiohead_art_3);
                break;
            }
            case 3: {
                ivThumbnail.setImageResource(R.drawable.radiohead_art_4);
                break;
            }
        }

        mRootView = (LinearLayout) findViewById(R.id.rootView);
        initTransition();
    }

    @TargetApi(21)
    private void initTransition() {
        Window window = getWindow();
        TransitionInflater inflater = TransitionInflater.from(this);

        Transition enterTransition = inflater.inflateTransition(R.transition.called_grid_activity_enter);
        window.setEnterTransition(enterTransition);

        Transition returnTransition = inflater.inflateTransition(R.transition.called_grid_activity_return);
        window.setReturnTransition(returnTransition);

//        window.setExitTransition();
//        window.setReenterTransition();
    }

    @Override
    public void onBeforeEnter(View contentView) {
        findViewById(R.id.top_panel).setAlpha(0f);
    }

    @Override
    public void onBeforeReturn() {

    }

    @Override
    public void onAfterEnter() {
    }
}
