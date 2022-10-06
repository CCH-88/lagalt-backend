package jact.lagaltproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MessageNotFoundException extends RuntimeException{

    public MessageNotFoundException(Long id) {
        super("Project does not exist with that ID: " + id);
    }

}
