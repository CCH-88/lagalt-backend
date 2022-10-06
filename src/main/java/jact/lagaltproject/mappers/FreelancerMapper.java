package jact.lagaltproject.mappers;


import jact.lagaltproject.models.Freelancer;
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
//    protected FreelancerService freelancerService;
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

}
