package jact.lagaltproject.mappers;

import jact.lagaltproject.models.Freelancer;
import jact.lagaltproject.models.Project;
import jact.lagaltproject.models.dtos.project.ProjectDTO;
import jact.lagaltproject.services.freelancerService.FreelancerService;
import jact.lagaltproject.services.project.ProjectService;
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
//    protected FreelancerService freelancerService;
//    protected ProjectService projectService;
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


}
