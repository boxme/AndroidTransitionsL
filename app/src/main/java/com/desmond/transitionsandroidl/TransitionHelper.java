package com.desmond.transitionsandroidl;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;

import com.desmond.transitionsandroidl.ActivityTransition.BaseActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by desmond on 20/8/15.
 */
public class TransitionHelper {

    WeakReference<BaseActivity> mActivityRef;

    boolean mIsAfterEnter;
    boolean mIsPostponeEnterTransition;

    public interface Source {
        TransitionHelper getTransitionHelper();
        void setTransitionHelper(TransitionHelper transitionHelper);
    }

    private TransitionHelper(BaseActivity activity, Bundle savedInstanceState) {
        mActivityRef = new WeakReference<>(activity);
        mIsAfterEnter = savedInstanceState != null;
        postponeEnterTransition();
    }

    public static void init(Source source, Bundle savedInstanceState) {
        source.setTransitionHelper(new TransitionHelper((BaseActivity) source, savedInstanceState));
    }

    public static ActivityOptionsCompat makeOptionsCompat(
            BaseActivity activity, Pair<View, String>... shareElements ) {

        ArrayList<Pair<View, String>> list = new ArrayList<>(Arrays.asList(shareElements));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            list.add(Pair.create(activity.findViewById(android.R.id.statusBarBackground),
                    Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME));
            list.add(Pair.create(activity.findViewById(android.R.id.navigationBarBackground),
                    Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME));
        }
        list.add(Pair.create(activity.findViewById(R.id.toolbar),  activity.getString(R.string.tool_bar)));

        shareElements = list.toArray(new Pair[list.size()]);

        return ActivityOptionsCompat.makeSceneTransitionAnimation(activity, shareElements);
    }

    private void postponeEnterTransition() {
        BaseActivity activity = mActivityRef.get();
        if (mIsAfterEnter || activity == null) return;

        mIsPostponeEnterTransition = true;
        ActivityCompat.postponeEnterTransition(activity);
    }

    private void startPostponedEnterTransition() {
        final BaseActivity activity = mActivityRef.get();
        if (activity == null) return;

        final View decor = activity.getWindow().getDecorView();
        decor.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                decor.getViewTreeObserver().removeOnPreDrawListener(this);
                ActivityCompat.startPostponedEnterTransition(activity);
                return true;
            }
        });
    }

    /**
     * Should be called from Activity.onResume
     */
    public void onResume() {
        if (mIsAfterEnter) return;

        onViewCreated();
    }

    public void onViewCreated() {
        if (mIsPostponeEnterTransition) {
            mIsPostponeEnterTransition = false;
            startPostponedEnterTransition();
        }
    }

    public void onBackPressed() {
        BaseActivity activity = mActivityRef.get();
        if (activity == null) return;

        ActivityCompat.finishAfterTransition(activity);
    }
}
