package jact.lagaltproject.models;


import jact.lagaltproject.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Project_user {

    @EmbeddedId
    ProjectUserKey id;

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
    @OneToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @OneToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    Project project;


}
