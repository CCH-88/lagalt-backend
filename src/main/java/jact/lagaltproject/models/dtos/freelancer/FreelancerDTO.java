package jact.lagaltproject.models.dtos.freelancer;

import lombok.Data;

import java.util.Set;

@Data
public class FreelancerDTO {
    private String id;
    private String username;
    private String email;
    private boolean hidden;
    private String[] skills;
    private String googleToken;
    private String portfolio;
    private String description;

    private Set<String> projectFreelancers;

    private Set<Long> messages;
    private Long freelancer_history;
}
