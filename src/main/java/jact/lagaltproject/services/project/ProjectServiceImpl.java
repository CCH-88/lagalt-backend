package jact.lagaltproject.services.project;

import jact.lagaltproject.exceptions.ProjectNotFoundException;
import jact.lagaltproject.models.Project;
import jact.lagaltproject.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepo;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepo = projectRepository;
    }

    @Override
    public Project findById(Long id) {
        return projectRepo.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

    @Override
    public Collection<Project> findAll() {
        return projectRepo.findAll();
    }

    @Override
    public Project add(Project entity) {
        return projectRepo.save(entity);
    }

    @Override
    public Project update(Project entity) {
        return projectRepo.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        projectRepo.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return projectRepo.existsById(id);
    }

    @Override
    public Collection<Project> findAllByName(String name) {
        return projectRepo.findAllByName(name);
    }
}
