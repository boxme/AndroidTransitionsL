package com.desmond.transitionsandroidl.ActivityTransition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.desmond.transitionsandroidl.R;

import java.util.List;

/**
 * 1) exit transition
 * removes Views from the calling Activity (e.g. explode)
 *
 * 2) reenter transition
 * moves Views back into the calling Activity when returning (e.g. slide).
 * Defaults to exit transition.
 *
 * 3) shared element exit transition
 * additional Transition to execute before transferring the shared element to the called Activity.
 * Does not have to be on the shared element. In this example, this lifts
 * the shared element off the hello Activity.
 *
 * 4) shared element reenter transition
 * when coming back, this is used after the shared element
 * has been transferred back to the calling Activity.
 * In this example, this drops the shared element back into the CallingActivity.
 * Defaults to shared element exit transition.
 */
public class CallingActivity extends AppCompatActivity {

    public static final String TAG = CallingActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);

        // Tell the system to put the shared element back to zero elevation
        // after the exit from CalledActivity
        setExitSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onSharedElementEnd(List<String> sharedElementNames,
                                           List<View> sharedElements,
                                           List<View> sharedElementSnapshots) {
                findViewById(R.id.hello).setTranslationZ(0);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calling, menu);
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

    public void clicked(View view) {
        View helloView = findViewById(R.id.hello);
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, helloView, "hello");
        Intent intent = new Intent(this, CalledActivity.class);
        startActivity(intent, options.toBundle());

        // When launch CalledActivity, have to call setTranslationZ to trigger
        // the shared element exit Transition:
        helloView.setTranslationZ(16);
    }
}
