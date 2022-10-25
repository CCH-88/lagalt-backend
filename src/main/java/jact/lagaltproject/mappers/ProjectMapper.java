package jact.lagaltproject.mappers;

import jact.lagaltproject.models.Chat;
import jact.lagaltproject.models.Project;
import jact.lagaltproject.models.ProjectFreelancer;
import jact.lagaltproject.models.dtos.project.ProjectDTO;
import jact.lagaltproject.services.chat.ChatService;
import jact.lagaltproject.services.project.ProjectService;
import jact.lagaltproject.services.projectFreelancer.ProjectFreelancerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ProjectMapper {

     @Autowired
     protected ProjectService projectService;

     @Autowired
     protected ChatService chatService;

     @Autowired
     protected ProjectFreelancerService projectFreelancerService;


    /*  Project To ProjectDTO   */
    @Mapping(target = "chatId", source = "chat.id")
    @Mapping(target = "projectFreelancers", source = "projectFreelancers", qualifiedByName = "projectFreelancersToIds")
    public abstract ProjectDTO projectToDTO(Project project);

    @Named("projectFreelancersToIds")
    Set<String> mapProjectFreelancersToIds(Set<ProjectFreelancer> project_freelancers) {
        if (project_freelancers == null) {
            return null;
        }
        return project_freelancers.stream().map(project_freelancer -> project_freelancer.getId().getFreelancer_id() + project_freelancer.getId().getProject_id()).collect(Collectors.toSet());
    }
    /*  Collection<Project> To Collection<ProjectDTO>   */
    public abstract Collection<ProjectDTO> projectToDTO(Collection<Project> projects);


    /*  ProjectDTO To Project   */
    @Mapping(target = "chat", source = "chatId", qualifiedByName = "chatIdToChat")
    @Mapping(target = "projectFreelancers", source = "projectFreelancers", qualifiedByName =  "projectFreelancerIdsToProjectFreelancers" )
    public abstract Project projectDTOToProject(ProjectDTO projectDTO);

    @Named("chatIdToChat")
    Chat mapChatIdToChat(Long id) {
        return chatService.findById(id);
    }

    @Named("projectFreelancerIdsToProjectFreelancers")
    Set<ProjectFreelancer> mapProjectFreelancerIdsToProjectFreelancers(Set<String> ids) {
        if (ids == null) return null;
        return ids.stream().map(id ->projectFreelancerService.findById(id)).collect(Collectors.toSet());
    }

    /*  Collection<ProjectDTO> To Collection<Project>   */
    public abstract Collection<Project> projectDTOToProject(Collection<ProjectDTO> projects);

}
