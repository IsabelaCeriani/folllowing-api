package com.folllowingapi.repositories;


import com.folllowingapi.models.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FollowRepository extends JpaRepository<Follow, UUID> {

    Optional<Follow> findByFollowerUserIdAndFollowedUserId(UUID followerUserID, UUID followedUserID);
    List<Follow> findAllByFollowedUserId(UUID followedId);
    List<Follow> findAllByFollowerUserId(UUID followerId);







}
