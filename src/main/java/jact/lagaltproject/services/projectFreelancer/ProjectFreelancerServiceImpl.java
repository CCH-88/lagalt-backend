package jact.lagaltproject.services.projectFreelancer;

import jact.lagaltproject.exceptions.ResourceNotFoundException;
import jact.lagaltproject.models.ProjectFreelancer;
import jact.lagaltproject.models.ProjectFreelancerKey;
import jact.lagaltproject.repositories.ProjectFreelancerRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProjectFreelancerServiceImpl implements ProjectFreelancerService {

    private final ProjectFreelancerRepository projectFreelancerRepository;

    public ProjectFreelancerServiceImpl(ProjectFreelancerRepository projectFreelancerRepository) {
        this.projectFreelancerRepository = projectFreelancerRepository;
    }


    @Override
    public ProjectFreelancer findById(String id) {
        return projectFreelancerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project_Freelancer With the Id: " + id + " Could not be found"));
    }

    @Override
    public Collection<ProjectFreelancer> findAll() {
        return projectFreelancerRepository.findAll();
    }

    @Override
    public ProjectFreelancer add(ProjectFreelancer entity) {
        return projectFreelancerRepository.save(entity);
    }

    @Override
    public ProjectFreelancer update(ProjectFreelancer entity) {
        return projectFreelancerRepository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        projectFreelancerRepository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        return projectFreelancerRepository.existsById(id);
    }

    @Override
    public void deleteByProjectFreelancerKey(ProjectFreelancerKey key) {
        projectFreelancerRepository.deleteByProjectFreelancerKey(key);
    }
}
