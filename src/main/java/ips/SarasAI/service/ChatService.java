package ips.SarasAI.service;

import ips.SarasAI.dto.ChatRequestDto;
import ips.SarasAI.dto.ChatResponseDto;
import ips.SarasAI.entity.Conversation;
import ips.SarasAI.entity.Message;
import ips.SarasAI.repository.ConversationRepository;
import ips.SarasAI.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final GeminiService geminiService;

    public ChatResponseDto chat(ChatRequestDto request){

         Conversation conversation;

        if(request.getConversationId() == null){

            conversation = Conversation.builder()
                    .title(request.getMessage())
                    .build();

            conversationRepository.save(conversation);

        } else {

            conversation = conversationRepository
                    .findById(request.getConversationId())
                    .orElseThrow(() -> new RuntimeException("Conversation not found"));
        }

        Message userMessage = Message.builder()
                .conversation(conversation)
                .role("USER")
                .content(request.getMessage())
                .build();

        messageRepository.save(userMessage);

        String aiReply = geminiService.ask(request.getMessage());

        Message aiMessage = Message.builder()
                .conversation(conversation)
                .role("AI")
                .content(aiReply)
                .build();

        messageRepository.save(aiMessage);

        return ChatResponseDto.builder()
                .conversationId(conversation.getId())
                .reply(aiReply)
                .build();
    }
}
