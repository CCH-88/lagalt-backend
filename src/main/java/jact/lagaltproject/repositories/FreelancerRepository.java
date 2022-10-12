package jact.lagaltproject.repositories;

import jact.lagaltproject.models.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {
    @Query(value = "SELECT * FROM Project p where p.id = ?1", nativeQuery = true)
    Collection<Freelancer> findUsersInProject(Long id);

    // Query for finding all freelancers by name which aren't hidden.
    @Query("select f from Freelancer f where f.name like %?1% and f.hidden is false")
    Set<Freelancer> findAllByName(String name);
}
