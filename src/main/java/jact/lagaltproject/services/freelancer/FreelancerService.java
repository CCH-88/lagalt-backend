package jact.lagaltproject.services.freelancer;

import jact.lagaltproject.models.Freelancer;
import jact.lagaltproject.services.CrudService;

import java.util.Collection;
import java.util.Set;

public interface FreelancerService extends CrudService<Freelancer, Integer> {
    Collection<Freelancer> findFreelancersInProject(Integer id);
    void updateFreelancersInProject(Set<Integer> freelancerId, int projectId);
}
