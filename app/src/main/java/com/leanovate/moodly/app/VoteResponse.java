package com.leanovate.moodly.app;

import java.io.Serializable;

/**
 * Created by claus on 04/02/15.
 */
public class VoteResponse implements Serializable {
    private String id;
    private String moodlyId;
    private int vote;
    private int iterationCount;

    public VoteResponse() {
    }

    public VoteResponse(String id, String moodlyId, int vote, int iterationCount) {
        this.id = id;
        this.moodlyId = moodlyId;
        this.vote = vote;
        this.iterationCount = iterationCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoodlyId() {
        return moodlyId;
    }

    public void setMoodlyId(String moodlyId) {
        this.moodlyId = moodlyId;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public int getIterationCount() {
        return iterationCount;
    }

    public void setIterationCount(int iterationCount) {
        this.iterationCount = iterationCount;
    }

    @Override
    public String toString() {
        return "VoteResponse{" +
                "id='" + id + '\'' +
                ", moodlyId='" + moodlyId + '\'' +
                ", vote=" + vote +
                ", iterationCount=" + iterationCount +
                '}';
    }
}
