package jact.lagaltproject.services.chat;

import jact.lagaltproject.exceptions.ChatNotFoundException;
import jact.lagaltproject.models.Chat;
import jact.lagaltproject.repositories.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepo;

    public ChatServiceImpl(ChatRepository chatRepo) {
        this.chatRepo = chatRepo;
    }

    @Override
    public Chat findById(Long id) {
        return chatRepo.findById(id).orElseThrow(() -> new ChatNotFoundException(id));
    }

    @Override
    public Collection<Chat> findAll() {
        return chatRepo.findAll();
    }

    @Override
    public Chat add(Chat entity) {
        return chatRepo.save(entity);
    }

    @Override
    public Chat update(Chat entity) {
        if (!chatRepo.existsById(entity.getId())) throw new ChatNotFoundException(entity.getId());
        return chatRepo.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        chatRepo.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return chatRepo.existsById(id);
    }
}
