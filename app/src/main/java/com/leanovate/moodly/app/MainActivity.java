package com.leanovate.moodly.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.util.Log;
import android.widget.ViewFlipper;

import java.util.UUID;


public class MainActivity extends Activity implements VoteResponseCallback {

    public static final String VOTE_RESPONSE_KEY = "com.leanovate.moodly.app.voteResponse";
    private UrlExtractor urlExtractor = new UrlExtractor();
    private ViewFlipper moodFlipper;
    private GestureDetector gestureDetector;
    private float lastX;
    private String moodlyId = "";
    private String host="";
    private SharedPreferences sharedPreferences;
    private String clientId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        Uri data = intent.getData();
        sharedPreferences = getSharedPreferences(getString(R.string.moodlyId), Context.MODE_PRIVATE);
        setPreferences(sharedPreferences);
        if(data != null) {
            moodlyId = urlExtractor.extractMoodlyId(data.getFragment());
            sharedPreferences.edit().putString("moodlyId",moodlyId).apply();
            host = data.getHost();
            sharedPreferences.edit().putString("host", host).apply();
        } else if( host.isEmpty() || moodlyId.isEmpty() ) {
            Intent settings = new Intent(this,Settings.class);
            startActivity(settings);
        }

        gestureDetector = new GestureDetector(this,new  MyGestureListener());
        moodFlipper = (ViewFlipper) findViewById(R.id.moodViewFlipper);
    }

    private void setPreferences(SharedPreferences sharedPreferences) {
        moodlyId = sharedPreferences.getString("moodlyId","");
        host = sharedPreferences.getString("host","");
        clientId = sharedPreferences.getString("clientId","");
        if (clientId.isEmpty()) {
            clientId = UUID.randomUUID().toString();
            sharedPreferences.edit().putString("clientId",clientId).apply();
        }
    }

    @Override
    protected void onResume() {
        setPreferences(sharedPreferences);
        super.onResume();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent settings = new Intent(this,Settings.class);
            startActivity(settings);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void voteResponse(VoteResponse voteResponse) {
        Intent intent = new Intent(this,DisplayVoteStatsActivity.class);
        intent.putExtra(VOTE_RESPONSE_KEY,voteResponse);
        startActivity(intent);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG,"onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
            if(event1.getX() < event2.getX()) {
                moodFlipper.showNext();
            }
            if(event1.getX() > event2.getX()) {
                moodFlipper.showPrevious();
            }

            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d(DEBUG_TAG,"onDoubleTap: " + e.toString() );
            int displayedChild = moodFlipper.getDisplayedChild();
            Log.d(DEBUG_TAG,"onDoubleTap --> " + displayedChild);
            VoteMoodTask voteMoodTask = new VoteMoodTask(MainActivity.this,clientId,host,moodlyId);
            voteMoodTask.execute(new Vote(displayedChild-1,clientId));
            return true;
        }
    }

}
