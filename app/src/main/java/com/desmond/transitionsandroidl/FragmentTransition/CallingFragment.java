package com.desmond.transitionsandroidl.FragmentTransition;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.SharedElementCallback;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.desmond.transitionsandroidl.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CallingFragment extends Fragment {

    public static final String TAG = CallingFragment.class.getSimpleName();

    public static Fragment newInstance() {
        CallingFragment fragment = new CallingFragment();
        return fragment;
    }

    public CallingFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calling, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked();
            }
        });

        setExitTransition(new Explode());
        setReenterTransition(new Slide());

        TransitionSet sharedElementEnterTransition =
                (TransitionSet) TransitionInflater.from(getActivity())
                        .inflateTransition(R.transition.shared_element_enter);

        TransitionSet sharedElementReturnTransition =
                (TransitionSet) TransitionInflater.from(getActivity())
                        .inflateTransition(R.transition.shared_element_exit);

        setSharedElementEnterTransition(sharedElementEnterTransition);
        setSharedElementReturnTransition(sharedElementReturnTransition);

        setExitSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onSharedElementStart(List<String> sharedElementNames,
                                             List<View> sharedElements,
                                             List<View> sharedElementSnapshots) {
                Log.d(TAG, "onSharedElementStart");
                super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots);
            }

            @Override
            public void onSharedElementEnd(List<String> sharedElementNames,
                                           List<View> sharedElements,
                                           List<View> sharedElementSnapshots) {
                Log.d(TAG, "onSharedElementEnd");
                getView().findViewById(R.id.hello).setTranslationZ(0);
            }
        });

    }

    private void clicked() {
        final View helloView = getView().findViewById(R.id.hello);


        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, CalledFragment.newInstance())
                .addToBackStack(null)
                .addSharedElement(helloView, helloView.getTransitionName())
                .commit();

        helloView.setTranslationZ(16);
    }
}
