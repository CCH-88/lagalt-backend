package jact.lagaltproject.repositories;

import jact.lagaltproject.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
