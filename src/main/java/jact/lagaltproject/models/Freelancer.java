package jact.lagaltproject.models;

import lombok.*;
import org.hibernate.annotations.Type;

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
    @Column(columnDefinition = "Boolean default true")
    private Boolean hidden;

    @Column(columnDefinition = "text[]")
    @Type(type = "jact.lagaltproject.models.types.PostgreSqlStringArrayType")
    private String[] skills;

    @Column(length = 800)
    private String googleToken;

    /*
    @Column(length = 800)
    private String githubToken;
    @Column(length = 800)
    private String twitterToken;
    */

    @Column()
    private String portfolio;
    @Column(length = 4000)
    private String description;

    //Relationships
    @OneToMany(mappedBy = "freelancer")
    Set<Message> messages;

    @OneToMany(mappedBy = "freelancer")
    private Set<Project_freelancer> project_freelancers;

}
