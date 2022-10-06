package jact.lagaltproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProjectNotFoundException extends RuntimeException{

    public ProjectNotFoundException(int id) {
        super("Project does not exist with that ID: " + id);
    }

}

