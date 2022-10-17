package jact.lagaltproject.services.message;

import jact.lagaltproject.models.Message;
import jact.lagaltproject.services.CrudService;
import org.springframework.stereotype.Service;

@Service
public interface MessageService extends CrudService<Message, Long> {
}
