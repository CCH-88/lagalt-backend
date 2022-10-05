package jact.lagaltproject.models;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String email;
    @Column()
    private boolean hidden;
    @Type(type = "list-array")
    @Column(name = "user_skill", columnDefinition = "text[]")
    private String[] skills;
    @Column(length = 800)
    private String googleToken;
    @Column(length = 800)
    private String githubToken;
    @Column(length = 800)
    private String twitterToken;
    @Column()
    private String portfolio;
    @Column(length = 4000)
    private String description;

    //Relationships
    @OneToMany(mappedBy = "user")
    Set<Message> messages;

}
