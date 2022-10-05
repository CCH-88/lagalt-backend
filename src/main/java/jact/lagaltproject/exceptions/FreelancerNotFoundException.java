package jact.lagaltproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FreelancerNotFoundException extends RuntimeException {
    public FreelancerNotFoundException(int id) {
        super("Freelancer does not exist with that ID: " + id);
    }
}
