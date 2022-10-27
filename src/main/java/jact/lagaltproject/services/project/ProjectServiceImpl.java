package jact.lagaltproject.services.project;

import jact.lagaltproject.exceptions.ProjectNotFoundException;
import jact.lagaltproject.exceptions.ResourceNotFoundException;
import jact.lagaltproject.models.Chat;
import jact.lagaltproject.models.Project;
import jact.lagaltproject.models.ProjectFreelancer;
import jact.lagaltproject.models.ProjectFreelancerKey;
import jact.lagaltproject.repositories.ProjectFreelancerRepository;
import jact.lagaltproject.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public Project findById(String id) {
        return projectRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project with the following id, was not found: "+id));
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
    public void application(Project project, ProjectFreelancer project_freelancer) {
        if (!projectRepo.existsById(project.getId())
                || !projectFreelancerRepository.existsById(project_freelancer.getProject().getId()))
            throw new ResourceNotFoundException("One of the resources doesn't exists");
    }

    @Override
    public void join(ProjectFreelancerKey projectFreelancerKey, Project project) {
        projectRepo.join(projectFreelancerKey, project.getId());
    }

    @Override
    public Project update(Project entity) {
        return projectRepo.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        if (!projectRepo.existsById(id)) throw new ResourceNotFoundException("Either project didn't exist or already deleted: "+id);
        Project proj = projectRepo.findById(id).get();
        proj.getProjectFreelancers().forEach(f -> projectFreelancerRepository.deleteByProjectFreelancerKey(f.getId()));
        projectRepo.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        return projectRepo.existsById(id);
    }

    @Override
    public Collection<Project> findAllByName(String name) {
        return projectRepo.findAllByName(name);
    }

    @Override
    public Collection<Project> findAllByField(String field) {
        return projectRepo.findAllByField(field);
    }
}
