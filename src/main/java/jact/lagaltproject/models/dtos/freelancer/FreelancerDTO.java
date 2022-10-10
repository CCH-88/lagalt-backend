package jact.lagaltproject.models.dtos.freelancer;

import lombok.Data;

import java.util.Set;

@Data
public class FreelancerDTO {
    private Long id;
    private String name;
    private String email;
    private boolean hidden;
    private String[] skills;
    private String googleToken;
    private String twitterToken;
    private String portfolio;
    private String description;

    private Set<Long> projectFreelancers;

    private Set<Long> messages;
    private Long project_history;
}
