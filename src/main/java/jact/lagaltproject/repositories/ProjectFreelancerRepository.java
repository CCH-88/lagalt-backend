package jact.lagaltproject.repositories;

import jact.lagaltproject.models.ProjectFreelancer;
import jact.lagaltproject.models.ProjectFreelancerKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectFreelancerRepository extends JpaRepository<ProjectFreelancer, String> {
    @Modifying
    @Query("delete from ProjectFreelancer pf where pf.id = ?1")
    void deleteByProjectFreelancerKey(ProjectFreelancerKey key);
}
