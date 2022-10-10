package jact.lagaltproject.services.projectFreelancer;

import jact.lagaltproject.exceptions.ResourceNotFoundException;
import jact.lagaltproject.models.Project_freelancer;
import jact.lagaltproject.repositories.ProjectFreelancerRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProjectFreelancerServiceImpl implements ProjectFreelancerService{

    private final ProjectFreelancerRepository projectFreelancerRepository;

    public ProjectFreelancerServiceImpl(ProjectFreelancerRepository projectFreelancerRepository) {
        this.projectFreelancerRepository = projectFreelancerRepository;
    }


    @Override
    public Project_freelancer findById(Long id) {
        return projectFreelancerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Project_Freelancer With the Id: " + id + " Could not be found"));
    }

    @Override
    public Collection<Project_freelancer> findAll() {
        return projectFreelancerRepository.findAll();
    }

    @Override
    public Project_freelancer add(Project_freelancer entity) {
        return projectFreelancerRepository.save(entity);
    }

    @Override
    public Project_freelancer update(Project_freelancer entity) {
        return projectFreelancerRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        projectFreelancerRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return projectFreelancerRepository.existsById(id);
    }
}
