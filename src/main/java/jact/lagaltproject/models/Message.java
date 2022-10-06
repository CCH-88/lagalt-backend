package jact.lagaltproject.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDateTime dateTime;
    @ManyToOne
    @JoinColumn(name="freelancer_id")
    private Freelancer freelancer;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @Column(length = 600, nullable = false)
    private String text;
}
