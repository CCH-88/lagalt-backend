package jact.lagaltproject.services.chat;

import jact.lagaltproject.models.Chat;
import jact.lagaltproject.services.CrudService;
import org.springframework.stereotype.Service;

@Service
public interface ChatService extends CrudService<Chat, Long> {
}
