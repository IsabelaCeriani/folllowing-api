package com.folllowingapi.controllers;


import com.folllowingapi.dtos.FollowDTO;
import com.folllowingapi.services.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("follows")
public class FollowController {


    @Autowired
    FollowService followService;

    @PostMapping("/followUser")
    public ResponseEntity<FollowDTO> followUser(@RequestBody FollowDTO followDTO){
       return ResponseEntity.ok(followService.followUser(followDTO));
    }

    @PostMapping("/unfollowUser")
    public ResponseEntity<FollowDTO> unfollowUser(@RequestBody FollowDTO followDTO){
        return ResponseEntity.ok(followService.unfollowUser(followDTO));

    }

    @GetMapping("/getFollowers/{id}")
    public ResponseEntity<List<UUID>> getFolllowers(@PathVariable UUID id){
        return ResponseEntity.ok(followService.getFollowers(id));

    }

    @GetMapping("/getFollowing/{id}")
    public ResponseEntity<List<UUID>> getFollowing(@PathVariable UUID id){
        return ResponseEntity.ok(followService.getFollowing(id));

    }

}