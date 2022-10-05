package jact.lagaltproject.models;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;


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
    @Type(type = "list-array")
    @Column(name = "project_images", columnDefinition = "text[]")
    private String[] projectImages;


}
