package com.desmond.transitionsandroidl.ActivityTransition;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.desmond.transitionsandroidl.R;

/**
 * 1) enter transition
 * moves Views into the called Activity (e.g. fade)
 *
 * 2) return transition
 * moves Views out of the called Activity when going back.
 * Defaults to the enter transition.
 *
 * 3) shared element enter transition
 * moves shared elements from the location/size
 * in the calling Activity to the final location/size.
 *
 * 4) shared element return transition
 * when going back, moves the shared elements from the
 * location/size in the called Activity to the location/size in the calling Activity.
 * Defaults to shared element enter transition.
 */
public class CalledActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_called);

        /*
        material-themed applications have their enter/return content transitions started a tiny bit
        before their exit/reenter content transitions complete, creating a small overlap that makes
        the overall effect more seamless and dramatic. If you wish to explicitly disable
        this behavior, you can do so by calling the
         */
//        getWindow().setAllowEnterTransitionOverlap(boolean);
//        getWindow().setAllowReturnTransitionOverlap(boolean);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_called, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        // To programatically trigger a return transition,
        // call finishAfterTransition() instead of finish().
        finishAfterTransition();
    }
}
