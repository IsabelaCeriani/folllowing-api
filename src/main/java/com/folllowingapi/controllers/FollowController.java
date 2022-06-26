package com.folllowingapi.controllers;


import com.folllowingapi.dtos.FollowDTO;
import com.folllowingapi.services.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.metrics.annotation.Timed;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("follows")
@Timed("post_controller_time")
public class FollowController {


    @Autowired
    FollowService followService;

    @PostMapping("/followUser")
    @Timed
    public ResponseEntity<FollowDTO> followUser(@RequestBody FollowDTO followDTO){
       return ResponseEntity.ok(followService.followUser(followDTO));
    }

    @PostMapping("/toggleFollow")
    @Timed
    public ResponseEntity<FollowDTO> toggleFollow(@RequestBody FollowDTO followDTO){
        return ResponseEntity.ok(followService.toggleFollow(followDTO));
    }

    @GetMapping("/getIsFollowing")
    @Timed
    public ResponseEntity<Boolean> getIsFollowing(@RequestBody FollowDTO followDTO){
        return ResponseEntity.ok(followService.getIsFollowing(followDTO));
    }


    @PostMapping("/unfollowUser")
    @Timed
    public ResponseEntity<FollowDTO> unfollowUser(@RequestBody FollowDTO followDTO){
        return ResponseEntity.ok(followService.unfollowUser(followDTO));

    }

    @GetMapping("/getFollowers/{id}")
    @Timed
    public ResponseEntity<Page<UUID>> getFolllowers(@PathVariable UUID id){
        return ResponseEntity.ok(followService.getFollowers(id));

    }


    @GetMapping("/getFollowing/{id}")
    @Timed
    public ResponseEntity<Page<UUID>> getFollowing(@PathVariable UUID id){
        return ResponseEntity.ok(followService.getFollowing(id));
    }

    @GetMapping("/getFollowingList/{id}")
    @Timed
    public ResponseEntity<List<UUID>> getFollowingList(@PathVariable UUID id){
        return ResponseEntity.ok(followService.getFollowingList(id));
    }

}
