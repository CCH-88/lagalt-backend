package jact.lagaltproject.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Type(type = "list-array")
//    @Column(name = "chat_message", columnDefinition = "text[]")
//    private String[] chatMessages;

    @OneToMany(mappedBy = "chat")
    private Set<Message> messages;

    @OneToOne
    private Project project;

}
