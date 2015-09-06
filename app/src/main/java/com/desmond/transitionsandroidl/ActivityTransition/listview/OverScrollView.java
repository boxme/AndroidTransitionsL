package com.desmond.transitionsandroidl.ActivityTransition.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ScrollView;

/**
 * Created by desmond on 6/9/15.
 */
public class OverScrollView extends ScrollView {

    public OverScrollView(Context context) {
        super(context);
    }

    public OverScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OverScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int mLastEventY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int eventY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                int yDistance = (int) getTranslationY();
                if (yDistance != 0 && mListener != null) {
                    if (!mListener.onOverScroll(yDistance, true)) { //only do this if mListener returns false
                        animate().translationY(0)
                                .setDuration(200)
                                .setInterpolator(new DecelerateInterpolator(6))
                                .start();
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                mLastEventY = eventY;
                break;
            case MotionEvent.ACTION_MOVE:
                if (getScrollY() == 0) {
                    handleOverscroll(event, false);
                } else {
                    View view = getChildAt(getChildCount() - 1);
                    if (view.getHeight() <= (getHeight() + getScrollY())) {
                        handleOverscroll(event, true);
                    }
                }
                break;
        }

        if (getTranslationY() != 0) {
            return true;
        }
        return super.onTouchEvent(event);

    }

    public static interface OverScrollListener {
        public boolean onOverScroll(int yDistance, boolean isReleased);
    }

    private OverScrollListener mListener;
    public void setOverScrollListener(OverScrollListener listener) {
        this.mListener = listener;
    }

    private void handleOverscroll(MotionEvent ev, boolean isBottom) {
        int pointerCount = ev.getHistorySize();
        for (int p = 0; p < pointerCount; p++) {
            int historicalY = (int) ev.getHistoricalY(p);
            int yDistance = (historicalY - mLastEventY) / 6;

            if ((isBottom && yDistance < 0) || (!isBottom && yDistance > 0)) {
                setTranslationY(yDistance);
                if (mListener != null) mListener.onOverScroll(yDistance, false);
            }
        }
    }
}
