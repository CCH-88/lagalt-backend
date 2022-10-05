package jact.lagaltproject.mappers;

import jact.lagaltproject.models.Project;
import jact.lagaltproject.models.dtos.project.ProjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDTO projectToProjectDto(Project project);

    @Mapping(target = "user", ignore = true)
    Project projectDtoToProject(ProjectDTO projectDTO);
}
