package jact.lagaltproject.services.freelancer;

import jact.lagaltproject.models.Freelancer;
import jact.lagaltproject.services.CrudService;

import java.util.Collection;
import java.util.Set;

public interface FreelancerService extends CrudService<Freelancer, Long> {
    Collection<Freelancer> findFreelancersInProject(Long id);

    Collection<Freelancer> findAllByName(String name);

    Freelancer add(String uid);

    Freelancer findByUid(String uid);
}
