package jact.lagaltproject.mappers;

import jact.lagaltproject.models.Chat;
import jact.lagaltproject.models.Project;
import jact.lagaltproject.models.Project_freelancer;
import jact.lagaltproject.models.dtos.project.ProjectDTO;
import jact.lagaltproject.services.chat.ChatService;
import jact.lagaltproject.services.project.ProjectService;
import jact.lagaltproject.services.projectFreelancer.ProjectFreelancerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ProjectMapper {
//
//    protected FreelancerService freelancerService;
     @Autowired
     protected ProjectService projectService;

     @Autowired
     protected ChatService chatService;

     @Autowired
     protected ProjectFreelancerService projectFreelancerService;
//
//    @Mapping(target = "project", source = "project.id")
//    @Mapping(target = "freelancer", source = "freelancer.id")
//    public abstract ProjectDTO projectToProjectDto(Project project);
//
//    public abstract Collection<ProjectDTO> projectToProjectDto(Collection<Project> projects);
//
//    @Mapping(target = "project", source = "project", qualifiedByName = "projectIdToProject")
//    @Mapping(target = "freelancers", source = "freelancers", qualifiedByName = "freelancerIdsToFreelancers")
//    public abstract Project projectDtoToProject(ProjectDTO dto);
//
//    //Custom Mappings
//    @Named("projectIdToProject")
//    Project mapIdToProject(int id) {
//        return projectService.findById(id);
//    }
//
//    @Named("freelancerIdsToFreelancers")
//    Set<Freelancer> mapIdsToFreelancers(Set<Integer> id) {
//        return id.stream()
//                .map(i -> freelancerService.findById(i))
//                .collect(Collectors.toSet());
//    }

    @Mapping(target = "chatId", source = "chat.id")
    @Mapping(target = "projectFreelancers", source = "project_freelancers", qualifiedByName = "projectFreelancersToIds")
    public abstract ProjectDTO projectToDTO(Project project);

    @Named("projectFreelancersToIds")
    Set<Long> mapProjectFreelancersToIds(Set<Project_freelancer> project_freelancers) {
        if (project_freelancers == null) {
            return null;
        }
        return project_freelancers.stream().map(project_freelancer -> project_freelancer.getId().getFreelancer_id() + project_freelancer.getId().getProject_id()).collect(Collectors.toSet());
    }

    @Mapping(target = "chat", source = "chatId", qualifiedByName = "chatIdToChat")
    @Mapping(target = "project_freelancers", source = "projectFreelancers", qualifiedByName =  "projectFreelancerIdsToProjectFreelancers" )
    public abstract Project projectDTOToProject(ProjectDTO projectDTO);

    @Named("chatIdToChat")
    Chat mapChatIdToChat(Long id) {
        return chatService.findById(id);
    }

    @Named("projectFreelancerIdsToProjectFreelancers")
    Set<Project_freelancer> mapProjectFreelancerIdsToProjectFreelancers(Set<Long> ids) {
        if (ids == null) return null;
        return ids.stream().map(id ->projectFreelancerService.findById(id)).collect(Collectors.toSet());
    }
}
