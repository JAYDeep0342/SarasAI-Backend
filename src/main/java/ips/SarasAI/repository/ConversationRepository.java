package ips.SarasAI.repository;

import ips.SarasAI.entity.Conversation;
import ips.SarasAI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> findByUser(User user);

}