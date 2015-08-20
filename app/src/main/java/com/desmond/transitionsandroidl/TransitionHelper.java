package com.desmond.transitionsandroidl;

import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.Window;

import com.desmond.transitionsandroidl.ActivityTransition.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by desmond on 20/8/15.
 */
public class TransitionHelper {

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
}
