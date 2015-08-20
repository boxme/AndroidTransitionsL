package com.desmond.transitionsandroidl.ActivityTransition.gridview;

import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Window;
import android.widget.ImageView;

import com.desmond.transitionsandroidl.BaseActivity;
import com.desmond.transitionsandroidl.R;
import com.desmond.transitionsandroidl.view.SquareImageView;

public class CalledGridViewActivity extends BaseActivity {

    public static final String POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_called_grid_view);

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

        initTransition();
    }

    private void initTransition() {
        Window window = getWindow();
        TransitionInflater inflater = TransitionInflater.from(this);
        Transition returnTransition = inflater.inflateTransition(R.transition.called_grid_activity_exit);
        window.setReturnTransition(returnTransition);

//        window.setExitTransition();
//        window.setReenterTransition();
//        window.setEnterTransition();
    }
}
