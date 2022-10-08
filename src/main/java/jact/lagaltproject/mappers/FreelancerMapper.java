package jact.lagaltproject.mappers;


import jact.lagaltproject.models.Freelancer;
import jact.lagaltproject.models.Message;
import jact.lagaltproject.models.Project_freelancer;
import jact.lagaltproject.models.dtos.freelancer.FreelancerDTO;
import jact.lagaltproject.services.freelancer.FreelancerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class FreelancerMapper {
//
//    @Autowired
 //   protected FreelancerService freelancerService;
//
//    @Mapping(target = "freelancer", source = "freelancer.id")
//    public abstract FreelancerDTO freelancerDTO(Freelancer user);
//
//    public abstract Collection<FreelancerDTO> userToUserDTO(Collection<Freelancer> users);
//
//    @Mapping(target = "project", source = "project", qualifiedByName = "projectIdToProject")
//    public abstract Freelancer freelancerDtoToFreelancer(FreelancerDTO dto);
//
//    @Named("usersToIds")
//    Set<Long> mapFreelancersToIds(Set<Freelancer> source) {
//        if (source == null) return null;
//        return source.stream()
//                .map(Freelancer::getId).collect(Collectors.toSet());
//    }
//
//    @Named("userIdsToUsers")
//    Set<Freelancer> mapIdsToFreelancers(Set<Integer> id) {
//        return id.stream()
//                .map(i -> freelancerService.findById(i))
//                .collect(Collectors.toSet());
//    }

    /*
    * Mapping:  Messages - check
    *           Freelance History
    *           Project_Freelancers - check
    * */
    @Mapping(target = "messages",source = "messages", qualifiedByName = "messagesToIds")
    @Mapping(target = "project_history", source = "freelancer_history.id")
    @Mapping(target = "projectFreelancers", source = "project_freelancers", qualifiedByName = "projectFreelancersToIds")
    public abstract FreelancerDTO freelancerToDTO(Freelancer freelancer);

    @Named("messagesToIds")
    Set<Long> mapMessagesToIds(Set<Message> messages) {
        if(messages == null) {
            return null;
        }
        return messages.stream().map(Message::getId).collect(Collectors.toSet());
    }

    @Named("projectFreelancersToIds")
    Set<Long> mapProjectFreelancersToIds(Set<Project_freelancer> project_freelancers) {
        if (project_freelancers == null) {
            return null;
        }
        return project_freelancers.stream().map(project_freelancer -> project_freelancer.getId().getFreelancer_id() + project_freelancer.getId().getProject_id()).collect(Collectors.toSet());
    }
}
