package jact.lagaltproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "chat-messages")
    private Set<Message> messages;

    @OneToOne(mappedBy = "chat")
    @JsonBackReference(value = "project_chat")
    private Project project;

}
