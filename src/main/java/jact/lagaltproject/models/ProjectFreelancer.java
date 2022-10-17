package jact.lagaltproject.models;


import jact.lagaltproject.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ProjectFreelancer {

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
    @JsonBackReference(value = "freelancer_pf")
    Freelancer freelancer;

    @ManyToOne(optional = true)
    @MapsId("project_id")
    @JoinColumn(name = "project_id")
    @JsonBackReference(value = "project_pf")
    Project project;


}
