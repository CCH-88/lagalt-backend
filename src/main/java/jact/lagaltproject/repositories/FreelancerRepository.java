package jact.lagaltproject.repositories;

import jact.lagaltproject.models.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Set;

public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {
    @Query(value = "SELECT * FROM Project p where p.id = ?1", nativeQuery = true)
    Collection<Freelancer> findUsersInProject(Long id);

    @Query("select f from Freelancer f where f.name like %?1%")
    Set<Freelancer> findAllByName(String name);
}
