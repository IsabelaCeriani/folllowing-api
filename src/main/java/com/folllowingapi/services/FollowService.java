package com.folllowingapi.services;


import com.folllowingapi.dtos.FollowDTO;
import com.folllowingapi.models.Follow;
import com.folllowingapi.repositories.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FollowService {

    @Autowired
    FollowRepository followRepository;

    public FollowDTO followUser(FollowDTO followDTO) {
        if(followExists(followDTO.getFollowerUserId(), followDTO.getFollowedUserId())) throw new RuntimeException("Already following this user");
        Follow follow = followDTO.toFollow();
        followRepository.save(follow);
        return followDTO;
    }
    public boolean followExists(UUID followedID, UUID followerID){
        return followRepository.findByFollowerUserIdAndFollowedUserId(followerID, followedID).isPresent();
    }

    public FollowDTO unfollowUser(FollowDTO followDTO) {
        Follow follow = followRepository
                .findByFollowerUserIdAndFollowedUserId(followDTO.getFollowerUserId(), followDTO.getFollowedUserId())
                .orElseThrow(() -> new IllegalArgumentException("Unable to unfollow because follow does not exists"));
        followRepository.delete(follow);
        return followDTO;
    }

    public List<UUID> getFollowers(UUID id, Integer pageSize) {
//        Pageable pageable = (Pageable) PageRequest.of(0, pageSize);
        List<Follow> allByFollowedUserId = followRepository.findAllByFollowedUserId(id);
        return allByFollowedUserId
                .stream()
                .map(Follow::getFollowerUserId)
                .collect(Collectors.toList());
    }

    public List<UUID> getFollowing(UUID id) {
//        Pageable pageable = (Pageable) PageRequest.of(0, pageSize);
        List<Follow> allUserFollows = followRepository.findAllByFollowerUserId(id);
        return allUserFollows
                .stream()
                .map(Follow::getFollowedUserId)
                .collect(Collectors.toList());

    }
}
