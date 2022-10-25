package jact.lagaltproject.services.freelancer;


import jact.lagaltproject.exceptions.ResourceNotFoundException;
import jact.lagaltproject.models.Freelancer;
import jact.lagaltproject.models.FreelancerHistory;
import jact.lagaltproject.repositories.FreelancerRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FreelancerServiceImpl implements FreelancerService {

    private final FreelancerRepository freelancerRepo;

    public FreelancerServiceImpl(FreelancerRepository freelancerRepo) {
        this.freelancerRepo = freelancerRepo;
    }

    @Override
    public Freelancer findById(String id) {
        return freelancerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer by " + id + " Was not found"));
    }

    @Override
    public Collection<Freelancer> findAll() {
        return freelancerRepo.findAll();
    }

    @Override
    public Freelancer add(Freelancer entity) {
        FreelancerHistory fh = new FreelancerHistory();
        fh.setFreelancer(entity);
        Long[] empty = new Long[0];
        fh.setViewed(empty);
        fh.setApplied(empty);
        fh.setParticipated(empty);
        fh.setClicked(empty);
        entity.setFreelancerHistory(fh);
        if (entity.getSkills() == null) {
            String[] emptySkills = new String[0];
            entity.setSkills(emptySkills);
        }
        return freelancerRepo.save(entity);
    }

    @Override
    public Freelancer update(Freelancer entity) {
        return freelancerRepo.save(entity);
    }

    @Override
    public void deleteById(String id) {
        if (!freelancerRepo.existsById(id)) throw new ResourceNotFoundException("The User was already deleted or didn't exist before, id of: " + id );
        freelancerRepo.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        return freelancerRepo.existsById(id);
    }

    @Override
    public Collection<Freelancer> findAllByUsername(String username) {
        return freelancerRepo.findAllByUsername(username);
    }

}
