package com.folllowingapi.repositories;


import com.folllowingapi.models.Follow;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FollowRepository extends JpaRepository<Follow, UUID> {

    Optional<Follow> findByFollowerUserIdAndFollowedUserId(UUID followerUserID, UUID followedUserID);
    Page<Follow> findAllByFollowedUserId(UUID followedId, Pageable pageable);
    Page<Follow> findAllByFollowerUserId(UUID followerId, Pageable pageable);







}
