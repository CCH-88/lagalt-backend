package jact.lagaltproject.services.FreelancerHistoryService;

import jact.lagaltproject.models.Freelancer;
import jact.lagaltproject.models.Freelancer_history;
import jact.lagaltproject.services.CrudService;
import org.springframework.stereotype.Service;

@Service
public interface FreelancerHistoryService extends CrudService<Freelancer_history, Long> {
}
