package com.baeldung.algorithms.greedy;


public class Follower {

    String username;
    long count;
    
    public Follower(String username, long count) {
        super();
        this.username = username;
        this.count = count;
    }
    
    @Override
    public String toString() {
        return "User: " + username + ", Followers: " + count + "\n\r" ; 
    }
}
