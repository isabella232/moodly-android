package com.leanovate.moodly.app;


import android.util.Log;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class VotingService {

    private String endPoint;
    private RestTemplate restTemplate = new RestTemplate();

    public VotingService(String endPoint) {
        this.endPoint = endPoint;
    }

    public VoteResponse vote(Vote vote) {
        VoteResponse response;
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        try {
            response = restTemplate.postForObject(endPoint + "/ballots", vote, VoteResponse.class);
            return response;
        } catch (Exception e) {
           Log.e("VOTING_SERVICE", "REST ERRROR : " + e.getMessage());
        }
        return new VoteResponse();
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
