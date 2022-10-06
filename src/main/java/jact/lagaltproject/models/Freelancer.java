package jact.lagaltproject.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
public class Freelancer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String email;
    @Column()
    private boolean hidden;
//    @Type(type = "list-array")
//    @Column(name = "user_skills", columnDefinition = "text[]")
//    private String[] skills;
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

    //Relationships
    @OneToMany(mappedBy = "freelancer")
    Set<Message> messages;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}
