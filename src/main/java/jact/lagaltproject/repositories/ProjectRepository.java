package jact.lagaltproject.repositories;

import jact.lagaltproject.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ProjectRepository extends JpaRepository<Project, String> {

    @Query("select p from Project p where p.name like lower(concat('%', ?1, '%'))")
    Set<Project> findAllByName(String name);

    @Query("select p from Project p where lower(p.field) like lower(concat('%', ?1, '%'))")
    Set<Project> findAllByField(String field);
}
