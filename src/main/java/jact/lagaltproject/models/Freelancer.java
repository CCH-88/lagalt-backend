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
    @Column(length = 100, nullable = false, unique = true)
    private String username;
    @Column(length = 100, nullable = false)
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

    @Column()
    private String portfolio;
    @Column(length = 4000)
    private String description;

    //Relationships
    @OneToMany(mappedBy = "freelancer", fetch = FetchType.LAZY)
    Set<Message> messages;

    @OneToMany(mappedBy = "freelancer")
    private Set<ProjectFreelancer> project_freelancers;

    @OneToOne(cascade = CascadeType.ALL)
    private FreelancerHistory freelancer_history;

}
