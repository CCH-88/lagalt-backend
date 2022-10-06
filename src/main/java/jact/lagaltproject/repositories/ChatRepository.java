package jact.lagaltproject.repositories;

import jact.lagaltproject.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
