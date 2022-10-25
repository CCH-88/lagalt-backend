package jact.lagaltproject.repositories;

import jact.lagaltproject.models.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer, String> {

    @Query("select f from Freelancer f where lower(f.username) like lower(concat('%', ?1, '%'))")
    Set<Freelancer> findAllByUsername(String username);

}
