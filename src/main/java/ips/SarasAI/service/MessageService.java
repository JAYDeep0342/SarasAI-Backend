package ips.SarasAI.service;

import ips.SarasAI.dto.MessageResponseDto;
import ips.SarasAI.entity.Conversation;
import ips.SarasAI.entity.Message;
import ips.SarasAI.repository.ConversationRepository;
import ips.SarasAI.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;

    public List<MessageResponseDto> getMessages(Long conversationId){

        Conversation conversation = conversationRepository
                .findById(conversationId)
                .orElseThrow(() -> new RuntimeException("Conversation not found"));

        List<Message> messages = messageRepository
                .findByConversationOrderByCreatedAtAsc(conversation);

        return messages.stream()
                .map(m -> MessageResponseDto.builder()
                        .role(m.getRole())
                        .content(m.getContent())
                        .build())
                .collect(Collectors.toList());
    }
}
