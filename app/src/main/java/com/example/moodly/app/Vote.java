package com.example.moodly.app;

/**
 * Created by claus on 04/02/15.
 */
public class Vote {
    private int vote;
    private String cookieId;

    public Vote(int voteValue,String cookieId) {
        this.vote = voteValue;
        this.cookieId = cookieId;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public String getCookieId() {
        return cookieId;
    }

    public void setCookieId(String cookieId) {
        this.cookieId = cookieId;
    }
}
