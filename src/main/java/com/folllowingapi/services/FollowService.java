package com.folllowingapi.services;


import com.folllowingapi.dtos.FollowDTO;
import com.folllowingapi.models.Follow;
import com.folllowingapi.pageConfig.RestResponsePage;
import com.folllowingapi.repositories.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class FollowService {

    @Autowired
    FollowRepository followRepository;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public FollowDTO followUser(FollowDTO followDTO) {
        if(followExists(followDTO.getFollowerUserId(), followDTO.getFollowedUserId())) throw new RuntimeException("Already following this user");
        return createFollow(followDTO);
    }

    private FollowDTO createFollow(FollowDTO followDTO) {
        Follow follow = followDTO.toFollow();
        followRepository.save(follow);
        logger.info("Added follow: " + follow.getId());
        return followDTO;
    }

    public boolean followExists(UUID followedID, UUID followerID){
        logger.info("Checking if follow exists");
        return followRepository.findByFollowerUserIdAndFollowedUserId(followerID, followedID).isPresent();
    }

    public FollowDTO unfollowUser(FollowDTO followDTO) {
        Follow follow = followRepository
                .findByFollowerUserIdAndFollowedUserId(followDTO.getFollowerUserId(), followDTO.getFollowedUserId())
                .orElseThrow(() -> new IllegalArgumentException("Unable to unfollow because follow does not exists"));
        followRepository.delete(follow);
        logger.info("Deleted follow: " + follow.getId());
        return followDTO;
    }

    public Page<UUID> getFollowers(UUID id) {
//        Pageable pageable = PageRequest.of(0, pageSize);
        List<Follow> allByFollowedUserId = followRepository.findAllByFollowedUserId(id);
        List<UUID> followersIdsList = allByFollowedUserId
                .stream()
                .map(Follow::getFollowerUserId)
                .collect(Collectors.toList());
        logger.info("Found followers: " + followersIdsList);
        return new PageImpl<UUID>(followersIdsList);
    }

    public Page<UUID> getFollowing(UUID id) {
        return new RestResponsePage<UUID>(getFollowingList(id));

    }

    public List<UUID> getFollowingList(UUID id) {
        System.out.println("entre");
//        Pageable pageable = (Pageable) PageRequest.of(0, pageSize);
        List<Follow> allUserFollows = followRepository.findAllByFollowerUserId(id);
        logger.info("Found following: " + allUserFollows);
        return   allUserFollows
                .stream()
                .map(Follow::getFollowedUserId)
                .collect(Collectors.toList());

    }

    public FollowDTO toggleFollow(FollowDTO followDTO) {
        Optional<Follow> followOptional = followRepository.findByFollowerUserIdAndFollowedUserId(followDTO.getFollowerUserId(), followDTO.getFollowedUserId());
        if(followOptional.isPresent()) {
            followRepository.delete(followOptional.get());
            return followDTO;
        }
        logger.info("Adding follow: " + followDTO.getFollowedUserId());
        return createFollow(followDTO);
    }

    public Boolean getIsFollowing(FollowDTO followDTO) {
        logger.info("Checking if following");
        return followExists(followDTO.getFollowerUserId(), followDTO.getFollowedUserId());
    }
}
