package jact.lagaltproject.repositories;

import jact.lagaltproject.models.Project;
import jact.lagaltproject.models.ProjectFreelancerKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ProjectRepository extends JpaRepository<Project, String> {

    @Query("select p from Project p where p.name like lower(concat('%', ?1, '%'))")
    Set<Project> findAllByName(String name);

    @Query("select p from Project p where lower(p.field) like lower(concat('%', ?1, '%'))")
    Set<Project> findAllByField(String field);

    @Modifying
    @Query("update ProjectFreelancer pf set pf.project.id = ?2 where pf.id = ?1")
    void join(ProjectFreelancerKey projectFreelancerKey, String projectId);
}
