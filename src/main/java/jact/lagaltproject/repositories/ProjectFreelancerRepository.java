package jact.lagaltproject.repositories;

import jact.lagaltproject.models.Project_freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectFreelancerRepository extends JpaRepository<Project_freelancer, Long> {
}
