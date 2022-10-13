package jact.lagaltproject.services.project;

import jact.lagaltproject.exceptions.ProjectNotFoundException;
import jact.lagaltproject.models.Chat;
import jact.lagaltproject.models.Freelancer;
import jact.lagaltproject.models.Project;
import jact.lagaltproject.models.Project_freelancer;
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
        if (entity.getProjectImages() == null) {
            String[] empty = new String[0];
            entity.setProjectImages(empty);
        }
        Chat chat = new Chat();
        entity.setChat(chat);
        return projectRepo.save(entity);
    }

    @Override
    public void application(Project project, Project_freelancer project_freelancer){

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

//    @Override
//    public Collection<Project> findAllByField(String field) {
//        return null;
//    }
}
