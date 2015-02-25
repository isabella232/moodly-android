package com.leanovate.moodly.app;

import android.app.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;


public class DisplayVoteStatsActivity extends Activity {

    private int yourVote;
    private int currentIteration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_vote_stats);
        VoteResponse voteResponse = (VoteResponse) getIntent().getSerializableExtra(MainActivity.VOTE_RESPONSE_KEY);
        TextView voteValue = (TextView) findViewById(R.id.vote_value);
        TextView iterationValue = (TextView) findViewById(R.id.iteration_value);
        yourVote = voteResponse.getVote();
        currentIteration = voteResponse.getIterationCount();
        voteValue.setText(Integer.toString(yourVote));
        iterationValue.setText(Integer.toString(currentIteration));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_vote_stats, menu);

        return true;
    }

}
