package jact.lagaltproject.repositories;

import jact.lagaltproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM project where project_id = ?!", nativeQuery = true)
    Collection<User> findUsersInProject(Integer id);
}
