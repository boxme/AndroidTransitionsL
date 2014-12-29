package com.desmond.transitionsandroidl.FragmentTransition;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.desmond.transitionsandroidl.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalledFragment extends Fragment {

    public static Fragment newInstance() {
        CalledFragment fragment = new CalledFragment();
        return fragment;
    }

    public CalledFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_called, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        setEnterTransition(Transition);
//        setReturnTransition(Transition);

//        TransitionSet sharedElementEnterTransition =
//                (TransitionSet) TransitionInflater.from(getActivity())
//                        .inflateTransition(R.transition.shared_element_enter);
//
//        TransitionSet sharedElementReturnTransition =
//                (TransitionSet) TransitionInflater.from(getActivity())
//                        .inflateTransition(R.transition.shared_element_exit);
//
//        setSharedElementEnterTransition(sharedElementEnterTransition);
//        setSharedElementReturnTransition(sharedElementReturnTransition);
    }
}
