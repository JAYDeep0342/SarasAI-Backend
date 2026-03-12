package ips.SarasAI.service;

import ips.SarasAI.dto.ConversationResponseDto;
import ips.SarasAI.entity.Conversation;
import ips.SarasAI.entity.User;
import ips.SarasAI.repository.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConversationService {

    private final ConversationRepository conversationRepository;

    public List<ConversationResponseDto> getConversations(User user){

        List<Conversation> conversations = conversationRepository.findByUser(user);

        return conversations.stream()
                .map(c -> ConversationResponseDto.builder()
                        .id(c.getId())
                        .title(c.getTitle())
                        .build())
                .collect(Collectors.toList());
    }

    public void deleteConversation(Long id){

        if(!conversationRepository.existsById(id)){
            throw new RuntimeException("Conversation not found");
        }

        conversationRepository.deleteById(id);
    }
}