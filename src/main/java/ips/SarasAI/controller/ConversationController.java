package ips.SarasAI.controller;

import ips.SarasAI.dto.ConversationResponseDto;
import ips.SarasAI.entity.User;
import ips.SarasAI.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversations")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @GetMapping
    public List<ConversationResponseDto> getConversations(
            @AuthenticationPrincipal User user){

        return conversationService.getConversations(user);
    }
    @DeleteMapping("/{id}")
    public String deleteConversation(@PathVariable Long id){

        conversationService.deleteConversation(id);

        return "Conversation deleted successfully";
    }
}
