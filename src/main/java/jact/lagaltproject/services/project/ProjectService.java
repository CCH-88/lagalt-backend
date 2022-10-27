package jact.lagaltproject.services.project;

import jact.lagaltproject.models.Project;
import jact.lagaltproject.models.ProjectFreelancer;
import jact.lagaltproject.models.ProjectFreelancerKey;
import jact.lagaltproject.services.CrudService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ProjectService extends CrudService<Project, String> {

    Collection<Project> findAllByName(String name);

    Collection<Project> findAllByField(String field);

    void application(Project project, ProjectFreelancer project_freelancer);

    void join(ProjectFreelancerKey projectFreelancerKey, Project project);
}
