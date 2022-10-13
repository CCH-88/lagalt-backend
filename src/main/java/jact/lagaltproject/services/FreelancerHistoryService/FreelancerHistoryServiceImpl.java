package jact.lagaltproject.services.FreelancerHistoryService;

import jact.lagaltproject.exceptions.ResourceNotFoundException;
import jact.lagaltproject.models.Freelancer_history;
import jact.lagaltproject.repositories.FreelancerHistoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class FreelancerHistoryServiceImpl implements FreelancerHistoryService{

    private final FreelancerHistoryRepository freelancerHistoryRepository;

    public FreelancerHistoryServiceImpl(FreelancerHistoryRepository freelancerHistoryRepository) {
        this.freelancerHistoryRepository = freelancerHistoryRepository;
    }

    @Override
    public Freelancer_history findById(Long id) {
        return freelancerHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Freelance History With the Id: " + id + " Could not be found"));
    }

    @Override
    public Collection<Freelancer_history> findAll() {
        return freelancerHistoryRepository.findAll();
    }

    @Override
    public Freelancer_history add(Freelancer_history entity) {
        return freelancerHistoryRepository.save(entity);
    }

    @Override
    public Freelancer_history update(Freelancer_history entity) {
        if (!freelancerHistoryRepository.existsById(entity.getId())) throw new ResourceNotFoundException("Freelance History with id: " + entity.getId() + " Could not be found");
        return freelancerHistoryRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        freelancerHistoryRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return freelancerHistoryRepository.existsById(id);
    }
}
