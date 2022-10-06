package jact.lagaltproject.repositories;

import jact.lagaltproject.models.Freelancer;
import jact.lagaltproject.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("select p from Project p where p.name like %?1%")
    Set<Project> findAllByName(String name);
}
