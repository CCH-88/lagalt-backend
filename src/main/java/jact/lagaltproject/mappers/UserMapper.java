package jact.lagaltproject.mappers;


import jact.lagaltproject.models.Project;
import jact.lagaltproject.models.User;
import jact.lagaltproject.models.dtos.user.UserDTO;
import jact.lagaltproject.services.project.ProjectService;
import jact.lagaltproject.services.userService.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected UserService userService;

    @Mapping(target = "user", source = "user.id")
    public abstract UserDTO userDTO(User user);

    public abstract Collection<UserDTO> userToUserDTO(Collection<User> users);

    @Mapping(target = "project", source = "project", qualifiedByName = "projectIdToProject")
    public abstract User userDtoToUser(UserDTO dto);

    @Named("usersToIds")
    Set<Long> mapUsersToIds(Set<User> source) {
        if (source == null) return null;
        return source.stream()
                .map(User::getId).collect(Collectors.toSet());
    }

    @Named("userIdsToUsers")
    Set<User> mapIdsToUsers(Set<Integer> id) {
        return id.stream()
                .map(i -> userService.findById(i))
                .collect(Collectors.toSet());
    }

}
