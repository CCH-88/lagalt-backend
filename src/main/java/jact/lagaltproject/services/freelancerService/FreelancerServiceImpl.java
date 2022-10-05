package jact.lagaltproject.services.freelancerService;

import jact.lagaltproject.exceptions.FreelancerNotFoundException;
import jact.lagaltproject.models.Freelancer;
import jact.lagaltproject.repositories.FreelancerRepository;

import java.util.Collection;
import java.util.Set;

public class FreelancerServiceImpl implements FreelancerService {

    private final FreelancerRepository freelancerRepo;

    public FreelancerServiceImpl(FreelancerRepository freelancerRepo) {
        this.freelancerRepo = freelancerRepo;
    }

    @Override
    public Freelancer findById(Integer id) {
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
    public void deleteById(Integer integer) {
        freelancerRepo.deleteById(integer);
    }

    @Override
    public boolean exists(Integer id) {
        return freelancerRepo.existsById(id);
    }

    @Override
    public Collection<Freelancer> findFreelancersInProject(Integer id) {
        return freelancerRepo.findUsersInProject(id);
    }

    @Override
    public void updateFreelancersInProject(Set<Integer> freelancerId, int projectId) {
        //TODO: Need to make the DTO before u.setProject(project) works.
//        List<Freelancer> freelancers = freelancerRepo.findAllById(userId);
//        Project project = projectRepo.getReferenceById(projectId);
//        for (Freelancer f:freelancers){
//            f.setProject(project);
//        }
//        userRepo.saveAll(freelancers);
    }

}
