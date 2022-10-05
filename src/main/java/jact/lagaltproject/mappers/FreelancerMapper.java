package jact.lagaltproject.mappers;


import jact.lagaltproject.models.Freelancer;
import jact.lagaltproject.models.dtos.user.FreelancerDTO;
import jact.lagaltproject.services.freelancerService.FreelancerService;
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

}
