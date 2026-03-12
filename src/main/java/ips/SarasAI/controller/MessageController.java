package ips.SarasAI.controller;

import ips.SarasAI.dto.MessageResponseDto;
import ips.SarasAI.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/{conversationId}")
    public List<MessageResponseDto> getMessages(
            @PathVariable Long conversationId){

        return messageService.getMessages(conversationId);
    }
}