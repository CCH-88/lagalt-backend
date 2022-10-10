package jact.lagaltproject.models;

import jact.lagaltproject.enums.Role;
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
    @Enumerated(EnumType.STRING)
    @Type(type = "jact.lagaltproject.models.types.PostgreSqlStringArrayType")
    //Possible solutions: Change type from Role[] to String[].
    // Or change role to a var: Role[] -> Role
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

    @OneToOne
    @JoinColumn(name = "freelancer_history_id", referencedColumnName = "id")
    private Freelancer_history freelancer_history;

}
