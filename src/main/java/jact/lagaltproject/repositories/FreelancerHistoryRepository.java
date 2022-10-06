package jact.lagaltproject.repositories;

import jact.lagaltproject.models.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreelancerHistoryRepository extends JpaRepository<Freelancer, Integer> {
}
