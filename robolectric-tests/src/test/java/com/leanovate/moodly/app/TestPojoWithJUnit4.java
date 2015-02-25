package com.leanovate.moodly.app;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Matchers;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class TestPojoWithJUnit4 {

    public static final String END_POINT = "http://someendpoint/endpoint";


    private RestTemplate mockRestTemplate;

    private VotingService votingService;

    @Before
    public void setup() {
        mockRestTemplate = mock(RestTemplate.class);
        votingService = new VotingService(END_POINT);
        votingService.setRestTemplate(mockRestTemplate);
        when(mockRestTemplate.postForObject(anyString(),any(Vote.class), Matchers.<Class<Object>>any()))
                .thenReturn(new VoteResponse("ID", "MOODLY_ID", 5, 1));

    }

    @Test
    public void shouldVote() {
        Vote vote = new Vote(5,"ID");

        VoteResponse voteResponse =votingService.vote(vote);

        assertThat(voteResponse, notNullValue());
    }



}
