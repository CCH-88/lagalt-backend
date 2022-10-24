package jact.lagaltproject.services.freelancer;

import jact.lagaltproject.exceptions.FreelancerNotFoundException;
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
    public void deleteById(Long id) {
        if (!freelancerRepo.existsById(id)) throw new FreelancerNotFoundException(id);
        freelancerRepo.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return freelancerRepo.existsById(id);
    }

    @Override
    public Collection<Freelancer> findAllByUsername(String username) {
        return freelancerRepo.findAllByUsername(username);
    }

    @Override
    public Collection<Freelancer> findAllRespectHidden() {
        return null;
    }

}
