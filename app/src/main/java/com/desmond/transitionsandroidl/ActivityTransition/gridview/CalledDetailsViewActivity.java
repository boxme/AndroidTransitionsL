package com.desmond.transitionsandroidl.ActivityTransition.gridview;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.desmond.transitionsandroidl.BaseActivity;
import com.desmond.transitionsandroidl.R;

/**
 * Created by desmond on 26/8/15.
 */
public class CalledDetailsViewActivity extends BaseActivity {
    public static final String TAG = CalledDetailsViewActivity.class.getSimpleName();
    public static final String POSITION = "position";

    private View mDescView;
    private View mTitleTv;
    private View mSubTitleTv;
    private View mBottomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_called_details_view);

        final int position = getIntent().getIntExtra(POSITION, 0);
        final ImageView ivLargePhoto = (ImageView) findViewById(R.id.ivLargePhoto);

        mDescView = findViewById(R.id.llDescription);
        mTitleTv = findViewById(R.id.title);
        mSubTitleTv = findViewById(R.id.subtitle);
        mBottomView = findViewById(R.id.llBottom);

        switch (position) {
            case 0: {
                ivLargePhoto.setImageResource(R.drawable.radiohead_art_1);
                break;
            }
            case 1: {
                ivLargePhoto.setImageResource(R.drawable.radiohead_art_2);
                break;
            }
            case 2: {
                ivLargePhoto.setImageResource(R.drawable.radiohead_art_3);
                break;
            }
            case 3: {
                ivLargePhoto.setImageResource(R.drawable.radiohead_art_4);
                break;
            }
        }

        initTransition();
    }

    @TargetApi(21)
    @Override
    protected void initTransition() {
        Window window = getWindow();
        TransitionInflater inflater = TransitionInflater.from(this);

        Transition sharedElementEnterTransition
                = inflater.inflateTransition(R.transition.shared_element_called_details_activity_enter);
        window.setSharedElementEnterTransition(sharedElementEnterTransition);

        Transition enterTransition = inflater.inflateTransition(R.transition.called_details_view_activity_enter);
        window.setEnterTransition(enterTransition);

//        window.setSharedElementReturnTransition(null);
//        window.setReturnTransition(null);
    }

    @Override
    public void onBeforeViewsShow() {
        mDescView.setAlpha(0f);
        mBottomView.setAlpha(0f);
    }

    @Override
    public void onBeforeEnter() {
    }

    @Override
    public void onAfterEnter() {}
}
