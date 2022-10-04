package jact.lagaltproject.services.project;

import jact.lagaltproject.models.Project;
import jact.lagaltproject.repositories.ProjectRepository;

import java.util.Collection;

public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepo;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepo = projectRepository;
    }

    @Override
    public Project findById(Integer integer) {
        return null;
    }

    @Override
    public Collection<Project> findAll() {
        return null;
    }

    @Override
    public Project add(Project entity) {
        return null;
    }

    @Override
    public Project update(Project entity) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public boolean exists(Integer integer) {
        return false;
    }
}
