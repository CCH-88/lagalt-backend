package jact.lagaltproject.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Project_user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


}
