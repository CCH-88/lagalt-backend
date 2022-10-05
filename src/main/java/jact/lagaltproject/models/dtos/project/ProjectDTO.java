package jact.lagaltproject.models.dtos.project;

import lombok.Data;

@Data
public class ProjectDTO {
    private Long id;
    private String name;
    private int ownerId;
    private String description;
    private String[] projectImages;
}
