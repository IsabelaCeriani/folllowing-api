package com.folllowingapi.dtos;

import com.folllowingapi.models.Follow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class FollowDTO {


    private UUID followerUserId;
    private UUID followedUserId;

    public FollowDTO(){}



    public Follow toFollow() {
        return new Follow(this.followerUserId, this.followedUserId);
    }



}
