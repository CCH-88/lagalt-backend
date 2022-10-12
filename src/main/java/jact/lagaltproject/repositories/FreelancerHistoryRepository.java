package jact.lagaltproject.repositories;

import jact.lagaltproject.models.Freelancer;
import jact.lagaltproject.models.Freelancer_history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface FreelancerHistoryRepository extends JpaRepository<Freelancer_history, Long> {


}
