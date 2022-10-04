package jact.lagaltproject.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 10, nullable = false)
    private int ownerId;
    @Column(length = 1000, nullable = false)
    private String description;
    //TODO: add array of image urls

}
