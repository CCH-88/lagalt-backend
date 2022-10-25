package jact.lagaltproject.models.dtos.project;

import lombok.Data;

import java.util.Set;

@Data
public class ProjectDTO {
    private String id;
    private String name;
    private String ownerId;
    private String description;
    private String[] projectImages;

    private Set<String> projectFreelancers;
    private Long chatId;
}
