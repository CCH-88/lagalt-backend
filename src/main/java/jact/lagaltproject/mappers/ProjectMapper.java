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


    protected FreelancerService freelancerService;
    protected ProjectService projectService;

}
