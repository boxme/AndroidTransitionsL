package com.desmond.transitionsandroidl;


import android.support.v4.app.Fragment;
import android.view.View;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment implements TransitionHelper.TransitionListener {

    public BaseFragment() {}

    @Override
    public void onBeforeViewShows(View contentView) {

    }

    @Override
    public void onBeforeEnter(View contentView) {

    }

    @Override
    public void onAfterEnter() {

    }

    @Override
    public boolean onBeforeBack() {
        return false;
    }

    @Override
    public void onBeforeReturn() {

    }
}
