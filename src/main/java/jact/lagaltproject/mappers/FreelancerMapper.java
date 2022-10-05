package jact.lagaltproject.mappers;


import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class FreelancerMapper {

//    @Autowired
//    protected FreelancerService freelancerService;
//
//    @Mapping(target = "project", source = "project.id")
//    public abstract FreelancerDTO freelancerDTO(Freelancer user);
//
//    public abstract Collection<FreelancerDTO> userToUserDTO(Collection<Freelancer> users);
//
//    @Mapping(target = "project", source = "project", qualifiedByName = "projectIdToProject")
//    public abstract Freelancer userDtoToUser(FreelancerDTO dto);
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
