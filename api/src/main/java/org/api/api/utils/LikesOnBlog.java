package org.api.api.utils;

import java.util.ArrayList;
import java.util.List;

public class LikesOnBlog {
    List<UserPair> userPairs=new ArrayList<UserPair>();
    int likes;
    public List<UserPair> getUserPairs() {
        return userPairs;
    }
    public void appendUserPairs(UserPair userPair) {
        this.userPairs.add(userPair);
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLikes(){
        return likes;
    }
    public void addLike(UserPair userPair){
        this.userPairs.add(userPair);
        likes++;
    }
}
