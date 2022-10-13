package jact.lagaltproject.services.project;

import jact.lagaltproject.models.Freelancer;
import jact.lagaltproject.models.Project;
import jact.lagaltproject.models.Project_freelancer;
import jact.lagaltproject.services.CrudService;

import java.util.Collection;

public interface ProjectService extends CrudService<Project, Long> {

    Collection<Project> findAllByName(String name);

    Collection<Project> findAllByField(String field);

    void application(Project project, Project_freelancer project_freelancer);
}
