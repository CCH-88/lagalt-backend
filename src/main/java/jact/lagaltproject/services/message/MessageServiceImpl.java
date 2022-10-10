package jact.lagaltproject.services.message;

import jact.lagaltproject.exceptions.MessageNotFoundException;
import jact.lagaltproject.models.Message;
import jact.lagaltproject.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepo;

    public MessageServiceImpl(MessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public Message findById(Long id) {
        return messageRepo.findById(id)
                .orElseThrow(() -> new MessageNotFoundException(id));
    }

    @Override
    public Collection<Message> findAll() {
        return messageRepo.findAll();
    }

    @Override
    public Message add(Message entity) {
        return messageRepo.save(entity);
    }

    @Override
    public Message update(Message entity) {
        return messageRepo.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        messageRepo.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return messageRepo.existsById(id);
    }
}
