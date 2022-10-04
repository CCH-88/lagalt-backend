package jact.lagaltproject.services.userService;

import jact.lagaltproject.models.User;
import jact.lagaltproject.services.CrudService;

import java.util.Collection;
import java.util.Set;

public interface UserService extends CrudService<User, Integer> {
    Collection<User> findUsersInProject(Integer id);
    void updateUsersInProject(Set<Integer> userId, int projectId);
}
