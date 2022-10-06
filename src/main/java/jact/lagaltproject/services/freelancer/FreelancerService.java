package jact.lagaltproject.services.freelancer;

import jact.lagaltproject.models.Freelancer;
import jact.lagaltproject.services.CrudService;

import java.util.Collection;
import java.util.Set;

public interface FreelancerService extends CrudService<Freelancer, Long> {
    Collection<Freelancer> findFreelancersInProject(Long id);
}
