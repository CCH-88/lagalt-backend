package jact.lagaltproject.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String email;
    @Column()
    private boolean hidden;
    //TODO: add for skills array.
    @Column(length = 800)
    private String googleToken;
    @Column(length = 800)
    private String githubToken;
    @Column(length = 800)
    private String twitterToken;
    @Column()
    private String portfolio;
    @Column(length = 4000)
    private String description;
}
