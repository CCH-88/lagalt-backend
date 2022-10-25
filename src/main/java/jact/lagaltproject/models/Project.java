package jact.lagaltproject.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jact.lagaltproject.enums.Progress;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
public class Project {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false, name = "project_id")
    private String id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 36, nullable = false)
    private String ownerId;
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
    @JsonManagedReference(value = "project_pf")
    private Set<ProjectFreelancer> projectFreelancers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_chat")
    @JsonManagedReference(value = "project_chat")
    private Chat chat;

    public void addProjectFreelancer(ProjectFreelancer projectFreelancer){
        this.projectFreelancers.add(projectFreelancer);
    }

}
