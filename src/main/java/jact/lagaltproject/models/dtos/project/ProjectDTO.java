package jact.lagaltproject.models.dtos.project;

import lombok.Data;

import java.util.Set;

@Data
public class ProjectDTO {
    private Long id;
    private String name;
    private int ownerId;
    private String description;
    private String[] projectImages;

    private Set<Long> projectFreelancers;
    private Long chatId;
}
