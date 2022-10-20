package jact.lagaltproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProjectFreelancerAlreadyExists extends RuntimeException{
    public ProjectFreelancerAlreadyExists(Long id){super("Project freelancer with id: " + id + " already exists in this project");}
}
