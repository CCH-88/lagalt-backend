package jact.lagaltproject.services.freelancer;

import jact.lagaltproject.exceptions.FreelancerNotFoundException;
import jact.lagaltproject.models.Freelancer;
import jact.lagaltproject.models.Project;
import jact.lagaltproject.repositories.FreelancerRepository;
import jact.lagaltproject.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class FreelancerServiceImpl implements FreelancerService {

    private final FreelancerRepository freelancerRepo;
    private final ProjectRepository projectRepo;

    public FreelancerServiceImpl(FreelancerRepository freelancerRepo, ProjectRepository projectRepo) {
        this.freelancerRepo = freelancerRepo;
        this.projectRepo = projectRepo;
    }

    @Override
    public Freelancer findById(Long id) {
        return freelancerRepo.findById(id)
                .orElseThrow(() -> new FreelancerNotFoundException(id));
    }

    @Override
    public Collection<Freelancer> findAll() {
        return freelancerRepo.findAll();
    }

    @Override
    public Freelancer add(Freelancer entity) {
        return freelancerRepo.save(entity);
    }

    @Override
    public Freelancer update(Freelancer entity) {
        return freelancerRepo.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        freelancerRepo.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return freelancerRepo.existsById(id);
    }

    @Override
    public Collection<Freelancer> findFreelancersInProject(Long id) {
        return freelancerRepo.findUsersInProject(id);
    }

}
