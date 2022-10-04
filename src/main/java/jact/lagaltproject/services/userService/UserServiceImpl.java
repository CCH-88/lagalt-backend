package jact.lagaltproject.services.userService;

import jact.lagaltproject.exceptions.UserNotFoundException;
import jact.lagaltproject.models.Project;
import jact.lagaltproject.models.User;
import jact.lagaltproject.repositories.ProjectRepository;
import jact.lagaltproject.repositories.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final ProjectRepository projectRepo;

    public UserServiceImpl(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepo = userRepository;
        this.projectRepo = projectRepository;
    }

    @Override
    public User findById(Integer id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Collection<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User add(User entity) {
        return userRepo.save(entity);
    }

    @Override
    public User update(User entity) {
        return userRepo.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        userRepo.deleteById(integer);
    }

    @Override
    public boolean exists(Integer id) {
        return userRepo.existsById(id);
    }

    @Override
    public Collection<User> findUsersInProject(Integer id) {
        return userRepo.findUsersInProject(id);
    }

    @Override
    public void updateUsersInProject(Set<Integer> userId, int projectId) {
        //TODO: Need to make the DTO before u.setProject(project) works.
//        List<User> users = userRepo.findAllById(userId);
//        Project project = projectRepo.getReferenceById(projectId);
//        for (User u:users){
//            u.setProject(project);
//        }
//        userRepo.saveAll(users);
    }

}
