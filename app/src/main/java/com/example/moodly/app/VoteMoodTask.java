package com.example.moodly.app;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


public class VoteMoodTask extends AsyncTask<Vote,Void,VoteResponse> {
    private static final String DEBUG_TAG = "Voteing";

    private String clientId = "";
    private String endPoint = "";
    private VoteResponseCallback voteResponseCallback;
    private VotingService votingService;


    public VoteMoodTask(VoteResponseCallback voteResponseCallback, String clientId, String host, String moodlyId) {
        this.voteResponseCallback = voteResponseCallback;
        this.endPoint = "http://" + host +"/rest/moodlies/" + moodlyId;
        this.clientId = clientId;
        votingService = new VotingService(this.endPoint);
    }

    @Override
    protected VoteResponse doInBackground(Vote... params) {
        Vote vote = params[0];
        vote.setCookieId(clientId);
        return votingService.vote(vote);
    }

    @Override
    protected void onPostExecute(VoteResponse voteResponse) {
        Log.d(DEBUG_TAG, "response --> " + voteResponse.toString());
        super.onPostExecute(voteResponse);
        voteResponseCallback.voteResponse(voteResponse);
    }
}
