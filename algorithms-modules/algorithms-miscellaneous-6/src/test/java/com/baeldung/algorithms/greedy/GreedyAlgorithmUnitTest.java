package com.baeldung.algorithms.greedy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;


class GreedyAlgorithmUnitTest {

    private SocialConnector prepareNetwork() {
        var root = new SocialUser("root");
        var child1 = new SocialUser("child1");
        var child2 = new SocialUser("child2");
        var child3 = new SocialUser("child3");
        var child21 = new SocialUser("child21");
        var child211 = new SocialUser("child211");
        var child2111 = new SocialUser("child2111");
        var child31 = new SocialUser("child31");
        var child311 = new SocialUser("child311");
        var child3111 = new SocialUser("child3111");
        child211.addFollowers(List.of(child2111));
        child311.addFollowers(List.of(child3111));
        child21.addFollowers(List.of(child211));
        child31.addFollowers(Arrays.asList(child311,
                new SocialUser("child312"), new SocialUser("child313"), new SocialUser("child314")));
        child1.addFollowers(Arrays.asList(new SocialUser("child11"), new SocialUser("child12")));
        child2.addFollowers(Arrays.asList(child21, new SocialUser("child22"), new SocialUser("child23")));
        child3.addFollowers(List.of(child31));
        root.addFollowers(Arrays.asList(child1, child2, child3));
        return new SocialConnector(List.of(root, child1, child2, child3, child21, child31, child311, child211));
    }

    @Test
    void greedyAlgorithmTest() {
        var ga = new GreedyAlgorithm(prepareNetwork());
        assertEquals(ga.findMostFollowersPath("root"), 5);
    }

    @Test
    void nongreedyAlgorithmTest() {
        var nga = new NonGreedyAlgorithm(prepareNetwork(), 0);
        assertThrows(IllegalStateException.class, () -> {
            nga.findMostFollowersPath("root");
          });
    }

    @Test
    void nongreedyAlgorithmUnboundedTest() {
        var sc = prepareNetwork();
        sc.switchCounter();
        var nga = new NonGreedyAlgorithm(sc, 0);
        assertEquals(nga.findMostFollowersPath("root"), 6);
    }
}