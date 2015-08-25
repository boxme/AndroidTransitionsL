package com.desmond.transitionsandroidl;


import android.support.v4.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment implements TransitionHelper.TransitionListener {

    public BaseFragment() {}

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
