package com.folllowingapi.models;

//import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrePersist;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
//@Table(name = "Follow")
public class Follow {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    private UUID followerUserId;

    private UUID followedUserId;

    private Date createdAt;

    public Follow(){}
    public Follow(UUID followerUserId, UUID followedUserId) {
        this.followerUserId = followerUserId;
        this.followedUserId = followedUserId;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }




    public UUID getId() {
        return id;
    }
}
