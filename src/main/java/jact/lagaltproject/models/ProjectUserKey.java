package jact.lagaltproject.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class ProjectUserKey implements Serializable {
    @Column(name = "user_id")
    long userId;

    @Column(name = "project_id")
    long projectId;
}
