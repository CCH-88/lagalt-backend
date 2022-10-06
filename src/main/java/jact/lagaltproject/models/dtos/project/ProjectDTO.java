package jact.lagaltproject.models.dtos.project;

import jact.lagaltproject.models.Project_freelancer;
import lombok.Data;

import java.util.Set;

@Data
public class ProjectDTO {
    private Long id;
    private String name;
    private int ownerId;
    private String description;
    private String[] projectImages;

    private Set<Project_freelancer> projectFreelancers;
    private Long chatId;
}
