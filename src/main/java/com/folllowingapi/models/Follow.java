package com.folllowingapi.models;

//import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "Follow")
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
