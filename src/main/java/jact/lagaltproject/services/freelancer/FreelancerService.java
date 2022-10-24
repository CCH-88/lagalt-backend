package jact.lagaltproject.services.freelancer;

import jact.lagaltproject.models.Freelancer;
import jact.lagaltproject.services.CrudService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface FreelancerService extends CrudService<Freelancer, Long> {
    Collection<Freelancer> findAllByUsernameRespectHidden(String username);

    Collection<Freelancer> findAllRespectHidden();
}
