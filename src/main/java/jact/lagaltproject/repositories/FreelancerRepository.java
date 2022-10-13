package jact.lagaltproject.repositories;

import jact.lagaltproject.models.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {
    @Query(value = "SELECT * FROM freelancer f where f.id = ?1", nativeQuery = true)
    Collection<Freelancer> findFreelancersInFreelancer(Long id);

    @Query("select f from Freelancer f where f.username like %?1%")
    Set<Freelancer> findAllByUsername(String username);
}
