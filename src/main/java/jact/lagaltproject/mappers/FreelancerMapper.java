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
    @Mapping(target = "messages", source = "messages", qualifiedByName = "messagesToIds")
    @Mapping(target = "freelancer_history", source = "freelancerHistory.id")
    @Mapping(target = "projectFreelancers", source = "projectFreelancers", qualifiedByName = "projectFreelancersToIds")
    public abstract FreelancerDTO freelancerToDTO(Freelancer freelancer);

    @Named("messagesToIds")
    Set<Long> mapMessagesToIds(Set<Message> messages) {
        if (messages == null) {
            return null;
        }
        return messages.stream().map(Message::getId).collect(Collectors.toSet());
    }

    @Named("projectFreelancersToIds")
    Set<String> mapProjectFreelancersToIds(Set<ProjectFreelancer> project_freelancers) {
        if (project_freelancers == null) {
            return null;
        }
        return project_freelancers.stream().map(project_freelancer -> project_freelancer.getId().getFreelancer_id() + project_freelancer.getId().getProject_id()).collect(Collectors.toSet());
    }

    /*  Mapping of FreelancerDTO To Freelancer  */
    @Mapping(target = "freelancerHistory", source = "freelancer_history", qualifiedByName = "freelancerHistoryIdToFreelancerHistory")
    @Mapping(target = "messages", source = "messages", qualifiedByName = "messageIdsToMessages")
    @Mapping(target = "projectFreelancers", source = "projectFreelancers", qualifiedByName = "mapProjectFreelancerIdsToProjectFreelancers")
    public abstract Freelancer freelancerDTOtoFreelancer(FreelancerDTO dto);

    @Named("freelancerHistoryIdToFreelancerHistory")
    FreelancerHistory mapIdToFreelancerHistory(Long id) {
        return freelancerHistoryService.findById(id);
    }

    @Named("messageIdsToMessages")
    Set<Message> mapMessageIdsToMessages(Set<Long> ids) {
        if (ids == null) return null;
        return ids.stream().map(id -> messageService.findById(id)).collect(Collectors.toSet());
    }

    @Named("mapProjectFreelancerIdsToProjectFreelancers")
    Set<ProjectFreelancer> mapProjectFreelancerIdsToProjectFreelancers(Set<String> ids) {
        if (ids == null) return null;
        return ids.stream().map(id -> projectFreelancerService.findById(id)).collect(Collectors.toSet());
    }

}
