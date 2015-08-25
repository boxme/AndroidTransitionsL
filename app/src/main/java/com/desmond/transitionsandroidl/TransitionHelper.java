package com.desmond.transitionsandroidl;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by desmond on 20/8/15.
 */
public class TransitionHelper {

    static final String TAG = TransitionHelper.class.getSimpleName();

    WeakReference<AppCompatActivity> mActivityRef;

    boolean mIsAfterEnter;
    boolean mIsPostponeEnterTransition;
    boolean mIsViewCreatedAlreadyCalled;

    private List<TransitionListener> mListeners;

    public interface Source {
        TransitionHelper getTransitionHelper();
        void setTransitionHelper(TransitionHelper transitionHelper);
    }

    /**
     * Listens for extra transition events
     * Activities, Fragments, and other views should implement Listener and call TransitionHelper.of(...).addListener(this)
     */
    public interface TransitionListener {
        /**
         * Called during every onViewCreated
         */
        void onBeforeViewsShow();

        /**
         * Called during onViewCreated only on an enter transition
         */
        void onBeforeEnter();

        /**
         * Called after enter transition is finished for L+, otherwise called immediately during first onResume
         */
        void onAfterEnter();

        /**
         * Called during Activity.onBackPressed()
         * @return true if the listener has consumed the event, false otherwise
         */
        boolean onBeforeBack();

        /**
         * Called before the run of a Return transition
         */
        void onBeforeReturn();
    }

    public static TransitionHelper of(AppCompatActivity activity) {
        return ((Source) activity).getTransitionHelper();
    }

    /**
     * Called in Activity.onCreate
     */
    public static void init(Source source, Bundle savedInstanceState) {
        source.setTransitionHelper(new TransitionHelper((BaseActivity) source, savedInstanceState));
    }

    private TransitionHelper(AppCompatActivity activity, Bundle savedInstanceState) {
        mActivityRef = new WeakReference<>(activity);
        mListeners = new ArrayList<>();
        mIsAfterEnter = savedInstanceState != null;
        mIsViewCreatedAlreadyCalled = false;
        postponeEnterTransition();
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

    public static void excludeEnterTarget(AppCompatActivity activity, int targetId, boolean toExclude) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().getEnterTransition().excludeTarget(targetId, toExclude);
        }
    }

    public void addListener(TransitionListener listener) {
        mListeners.add(listener);
    }

    private void onAfterEnter() {
        int size = mListeners.size();
        for (int i = 0; i < size; i++) {
            mListeners.get(i).onAfterEnter();
        }
        mIsAfterEnter = true;
    }

    /**
     * Should be called immediately after all shared elements transition views are inflated
     * If using fragments, call at beginning of Fragment.onViewCreated
     */
    public void onViewCreated() {
        AppCompatActivity activity = mActivityRef.get();
        if (activity == null || mIsViewCreatedAlreadyCalled) return;
        mIsViewCreatedAlreadyCalled = true;

        final int numOfListeners = mListeners.size();
        for (int i = 0; i < numOfListeners; i++) {
            mListeners.get(i).onBeforeViewsShow();
        }

        if (!mIsAfterEnter) {
            for (int i = 0; i < numOfListeners; i++) {
                mListeners.get(i).onBeforeEnter();
            }
        }

        if (mIsPostponeEnterTransition) {
            mIsPostponeEnterTransition = false;
            startPostponedEnterTransition();
        }
    }


    private void postponeEnterTransition() {
        final AppCompatActivity activity = mActivityRef.get();
        if (mIsAfterEnter || activity == null) return;

        mIsPostponeEnterTransition = true;
        ActivityCompat.postponeEnterTransition(activity);
    }

    private void startPostponedEnterTransition() {
        final AppCompatActivity activity = mActivityRef.get();
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
        AppCompatActivity activity = mActivityRef.get();
        if (activity == null || mIsAfterEnter) return;

        if (!mIsViewCreatedAlreadyCalled) onViewCreated();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            onAfterEnter();
        } else {
//            activity.getWindow().getEnterTransition().addListener();
//            activity.getWindow().getExitTransition().addListener();
//            activity.getWindow().getReenterTransition().addListener();
//            activity.getWindow().getReturnTransition().addListener();

//            activity.getWindow().getSharedElementExitTransition().addListener();
//            activity.getWindow().getSharedElementReenterTransition().addListener();
//            activity.getWindow().getSharedElementReturnTransition().addListener();

            activity.getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                    Log.d(TAG, "onTransitionStart");
                    if (mIsAfterEnter) {
                        final int numOfListeners = mListeners.size();
                        for (int i = 0; i < numOfListeners; i++) {
                            mListeners.get(i).onBeforeReturn();
                        }
                    }
                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    Log.d(TAG, "onTransitionEnd");
                    if (!mIsAfterEnter) {
                        onAfterEnter();
                    }
                }

                @Override
                public void onTransitionCancel(Transition transition) {
                    Log.d(TAG, "onTransitionCancel");
                    if (!mIsAfterEnter) {
                        onAfterEnter();
                    }
                }

                @Override
                public void onTransitionPause(Transition transition) {
                    Log.d(TAG, "onTransitionPause");
                }

                @Override
                public void onTransitionResume(Transition transition) {
                    Log.d(TAG, "onTransitionResume");
                }
            });
        }
    }

    /**
     * Should be called from Activity.onBackPressed
     */
    public void onBackPressed() {
        final AppCompatActivity activity = mActivityRef.get();
        if (activity == null) return;

        boolean isConsumed = false;
        final int numOfListeners = mListeners.size();
        for (int i = 0; i < numOfListeners; i++) {
            isConsumed = mListeners.get(i).onBeforeBack() || isConsumed;
        }

        if (!isConsumed) ActivityCompat.finishAfterTransition(activity);
    }
}
