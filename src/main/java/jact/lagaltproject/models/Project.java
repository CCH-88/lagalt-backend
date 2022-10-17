package jact.lagaltproject.models;

import jact.lagaltproject.enums.Progress;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 10, nullable = false)
    private int ownerId;
    @Column(length = 1000, nullable = false)
    private String description;
    @Column(nullable = false)
    private String field;

    @Column(columnDefinition = "text[]")
    @Type(type = "jact.lagaltproject.models.types.PostgreSqlStringArrayType")
    private String[] projectImages;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Progress progress;


    //Relationships

    @OneToMany(mappedBy = "project")
    private Set<ProjectFreelancer> projectFreelancers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_chat")
    private Chat chat;

}
