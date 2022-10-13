package jact.lagaltproject.services.project;

import jact.lagaltproject.exceptions.ProjectNotFoundException;
import jact.lagaltproject.exceptions.ResourceNotFoundException;
import jact.lagaltproject.models.Chat;
import jact.lagaltproject.models.Project;
import jact.lagaltproject.models.Project_freelancer;
import jact.lagaltproject.repositories.ProjectFreelancerRepository;
import jact.lagaltproject.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepo;
    private final ProjectFreelancerRepository projectFreelancerRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectFreelancerRepository projectFreelancerRepository) {
        this.projectRepo = projectRepository;
        this.projectFreelancerRepository = projectFreelancerRepository;
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
        if (entity.getProjectImages() == null) {
            String[] empty = new String[0];
            entity.setProjectImages(empty);
        }
        Chat chat = new Chat();
        entity.setChat(chat);
        return projectRepo.save(entity);
    }

    @Override
    public void application(Project project, Project_freelancer project_freelancer) {
        if (!projectRepo.existsById(project.getId())
                || !projectFreelancerRepository.existsById(project_freelancer.getProject().getId()))
            throw new ResourceNotFoundException("One of the resources doesn't exists");
    }

    @Override
    public Project update(Project entity) {
        if (!projectRepo.existsById(entity.getId())) throw new ProjectNotFoundException(entity.getId());
        return projectRepo.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        if (!projectRepo.existsById(id)) throw new ProjectNotFoundException(id);
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

//    @Override
//    public Collection<Project> findAllByField(String field) {
//        return null;
//    }
}
