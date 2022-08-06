package com.ndcalabrese.topick_simple.model;

import com.ndcalabrese.topick_simple.exception.TopickException;

import java.util.Arrays;

public enum VoteType {
    UPVOTE(1), DOWNVOTE(-1),
    ;

    private int direction;

    VoteType(int direction) {
    }

    public static VoteType lookup(Integer direction) {
        return Arrays.stream(VoteType.values())
                .filter(value -> value.getDirection().equals(direction))
                .findAny()
                .orElseThrow(() -> new TopickException("Vote not found"));
    }

    public Integer getDirection() {
        return direction;
    }
}
