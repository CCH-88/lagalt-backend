package jact.lagaltproject.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class ProjectFreelancerKey implements Serializable {
    String freelancer_id;

    String project_id;
}
