package jact.lagaltproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ChatNotFoundException extends RuntimeException{

    public ChatNotFoundException(Long id) {super("Chat does not exist with this id: " + id);}
}
