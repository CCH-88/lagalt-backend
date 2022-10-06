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
public class ProjectFreelancerKey implements Serializable {
    @Column(name = "freelancer_id")
    long freelancer_id;

    @Column(name = "project_id")
    long project_id;
}
