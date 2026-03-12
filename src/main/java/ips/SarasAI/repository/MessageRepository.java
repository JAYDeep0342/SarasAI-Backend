package ips.SarasAI.repository;

import ips.SarasAI.entity.Conversation;
import ips.SarasAI.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByConversationOrderByCreatedAtAsc(Conversation conversation);

}