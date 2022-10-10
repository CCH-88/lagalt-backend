package jact.lagaltproject.mappers;


import jact.lagaltproject.models.Freelancer;
import jact.lagaltproject.models.Freelancer_history;
import jact.lagaltproject.models.Message;
import jact.lagaltproject.models.Project_freelancer;
import jact.lagaltproject.models.dtos.freelancer.FreelancerDTO;
import jact.lagaltproject.services.FreelancerHistoryService.FreelancerHistoryService;
import jact.lagaltproject.services.freelancer.FreelancerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class FreelancerMapper {

    @Autowired
    protected FreelancerService freelancerService;

    @Autowired
    protected FreelancerHistoryService freelancerHistoryService;

    /*
    *   Mapping of Freelancer To FreelancerDTO
    */
    @Mapping(target = "messages",source = "messages", qualifiedByName = "messagesToIds")
    @Mapping(target = "freelancer_history", source = "freelancer_history.id")
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

    /*
    *   Mapping of Freelancer To FreelancerDTO
    */

    @Mapping(target = "freelancer_history", source = "freelancer_history", qualifiedByName = "freelancerHistoryIdToFreelancerHistory")
    public abstract Freelancer FreelancerDTOtoFreelancer(FreelancerDTO dto);

    @Named("freelancerHistoryIdToFreelancerHistory")
    Freelancer_history mapIdToFreelancer_History(Long id ) {
        return freelancerHistoryService.findById(id);
    }
}
