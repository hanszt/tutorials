package com.baeldung.algorithms.greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class SocialUser {

    private final String username;
    private final List<SocialUser> followers;

    public SocialUser(String username) {
        this.username = username;
        this.followers = new ArrayList<>();
    }
    
    public SocialUser(String username, List<SocialUser> followers) {
        this.username = username;
        this.followers = followers;
    }
    
    public long getFollowersCount() {
        return followers.size();
    }
    
    public void addFollowers(List<SocialUser> followers) {
        this.followers.addAll(followers);
    }
    
    @Override
    public boolean equals(Object obj) {
        return ((SocialUser) obj).username.equals(username);
    }

    public String getUsername() {
        return username;
    }

    public List<SocialUser> getFollowers() {
        return followers;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "SocialUser{" +
               "username='" + username + '\'' +
               '}';
    }
}
