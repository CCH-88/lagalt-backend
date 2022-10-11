package jact.lagaltproject.models;


import jact.lagaltproject.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Project_freelancer {

    @EmbeddedId
    ProjectFreelancerKey id;

    @Column(nullable = false)
    @ColumnDefault("'applicant'")
    @Enumerated(EnumType.STRING)
    private Role role;

    // true = accepted, false = denied, null = not yet reviewed
    @Column(columnDefinition = "Boolean default null")
    private Boolean accepted;

    @Column(length = 2000, nullable = false)
    private String motivation;

    // Relationships
    @ManyToOne(optional = true)
    @MapsId("freelancer_id")
    @JoinColumn(name = "freelancer_id")
    Freelancer freelancer;

    @ManyToOne(optional = true)
    @MapsId("project_id")
    @JoinColumn(name = "project_id")
    Project project;


}
