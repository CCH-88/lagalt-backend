package jact.lagaltproject.repositories;

import jact.lagaltproject.models.FreelancerHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreelancerHistoryRepository extends JpaRepository<FreelancerHistory, Long> {


}
