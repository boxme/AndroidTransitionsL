package com.desmond.transitionsandroidl.ActivityTransition.gridview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.desmond.transitionsandroidl.ActivityTransition.BaseActivity;
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
    }
}
