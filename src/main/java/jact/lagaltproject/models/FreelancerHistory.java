package jact.lagaltproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class FreelancerHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "bigint[]")
    @Type(type = "jact.lagaltproject.models.types.PostgreSqlLongArrayType")
    private Long[] viewed;

    @Column(columnDefinition = "bigint[]")
    @Type(type = "jact.lagaltproject.models.types.PostgreSqlLongArrayType")
    private Long[] clicked;

    @Column(columnDefinition = "bigint[]")
    @Type(type = "jact.lagaltproject.models.types.PostgreSqlLongArrayType")
    private Long[] applied;

    @Column(columnDefinition = "bigint[]")
    @Type(type = "jact.lagaltproject.models.types.PostgreSqlLongArrayType")
    private Long[] participated;

    @OneToOne(mappedBy = "freelancerHistory")
    @JsonBackReference(value = "freelancer_fh")
    private Freelancer freelancer;

}