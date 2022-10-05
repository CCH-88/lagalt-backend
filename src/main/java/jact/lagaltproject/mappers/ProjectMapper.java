package jact.lagaltproject.mappers;

import jact.lagaltproject.models.Project;
import jact.lagaltproject.models.User;
import jact.lagaltproject.models.dtos.project.ProjectDTO;
import jact.lagaltproject.services.project.ProjectService;
import jact.lagaltproject.services.userService.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ProjectMapper {
//
//    @Autowired
//    protected UserService userService;
//    @Autowired
//    protected ProjectService projectService;
//
//    @Mapping(target = "project", source = "project.id")
//    @Mapping(target = "user", source = "user.id")
//    public abstract ProjectDTO projectToProjectDto(Project project);
//
//    public abstract Collection<ProjectDTO> projectToProjectDto(Collection<Project> projects);
//
//    @Mapping(target = "project", source = "project", qualifiedByName = "projectIdToProject")
//    @Mapping(target = "users", source = "users", qualifiedByName = "userIdsToUser")
//    public abstract Project projectDtoToProject(ProjectDTO dto);
//
//    //Custom Mappings
//    @Named("projectIdToProject")
//    Project mapIdToProject(int id) {
//        return projectService.findById(id);
//    }
//
//    @Named("userIdsToUsers")
//    Set<User> mapIdsToUsers(Set<Integer> id) {
//        return id.stream()
//                .map(i -> userService.findById(i))
//                .collect(Collectors.toSet());
//    }
//

}
