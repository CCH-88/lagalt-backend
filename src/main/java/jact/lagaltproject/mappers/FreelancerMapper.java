package jact.lagaltproject.mappers;


import jact.lagaltproject.models.*;
import jact.lagaltproject.models.dtos.freelancer.FreelancerDTO;
import jact.lagaltproject.services.FreelancerHistoryService.FreelancerHistoryService;
import jact.lagaltproject.services.freelancer.FreelancerService;
import jact.lagaltproject.services.message.MessageService;
import jact.lagaltproject.services.projectFreelancer.ProjectFreelancerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class FreelancerMapper {

    @Autowired
    protected FreelancerService freelancerService;

    @Autowired
    protected FreelancerHistoryService freelancerHistoryService;

    @Autowired
    protected MessageService messageService;

    @Autowired
    protected ProjectFreelancerService projectFreelancerService;

    /*  Mapping of Freelancer To FreelancerDTO  */
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
    Set<Long> mapProjectFreelancersToIds(Set<ProjectFreelancer> project_freelancers) {
        if (project_freelancers == null) {
            return null;
        }
        return project_freelancers.stream().map(project_freelancer -> project_freelancer.getId().getFreelancer_id() + project_freelancer.getId().getProject_id()).collect(Collectors.toSet());
    }

    /*  Collection<Freelancer> To Collection<FreelancerDTO>   */
    public abstract Collection<FreelancerDTO> freelancerToDTO(Collection<Freelancer> freelancers);


    /*  Mapping of Freelancer To FreelancerDTO  */

    @Mapping(target = "freelancer_history", source = "freelancer_history", qualifiedByName = "freelancerHistoryIdToFreelancerHistory")
    @Mapping(target = "messages", source = "messages", qualifiedByName = "messageIdsToMessages")
    @Mapping(target = "project_freelancers",source = "projectFreelancers", qualifiedByName = "mapProjectFreelancerIdsToProjectFreelancers")
    public abstract Freelancer FreelancerDTOtoFreelancer(FreelancerDTO dto);

    @Named("freelancerHistoryIdToFreelancerHistory")
    FreelancerHistory mapIdToFreelancer_History(Long id ) {
        return freelancerHistoryService.findById(id);
    }

    @Named("messageIdsToMessages")
    Set<Message> mapMessageIdsToMessages(Set<Long> ids) {
        if(ids == null) return null;
        return ids.stream().map(id -> messageService.findById(id)).collect(Collectors.toSet());
    }

    @Named("mapProjectFreelancerIdsToProjectFreelancers")
    Set<ProjectFreelancer> mapProjectFreelancerIdsToProjectFreelancers(Set<Long> ids) {
        if (ids == null) return null;
        return ids.stream().map(id ->projectFreelancerService.findById(id)).collect(Collectors.toSet());
    }

    /*  Collection<Freelancer> To Collection<FreelancerDTO>   */
    public abstract Collection<Freelancer> FreelancerDTOtoFreelancer(Collection<FreelancerDTO> freelancers);

}
