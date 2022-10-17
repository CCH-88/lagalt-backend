package jact.lagaltproject.services.projectFreelancer;

import jact.lagaltproject.models.ProjectFreelancer;
import jact.lagaltproject.models.ProjectFreelancerKey;
import jact.lagaltproject.services.CrudService;
import org.springframework.stereotype.Service;

@Service
public interface ProjectFreelancerService extends CrudService<ProjectFreelancer, Long> {

    void deleteByProjectFreelancerKey(ProjectFreelancerKey key);
}
