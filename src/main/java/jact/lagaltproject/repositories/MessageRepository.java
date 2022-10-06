package jact.lagaltproject.repositories;

import jact.lagaltproject.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
