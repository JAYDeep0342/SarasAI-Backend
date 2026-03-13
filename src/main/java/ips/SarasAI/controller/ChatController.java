package ips.SarasAI.controller;
import ips.SarasAI.dto.ChatRequestDto;
import ips.SarasAI.dto.ChatResponseDto;
import ips.SarasAI.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/create-chat")
    public ChatResponseDto chat(@RequestBody ChatRequestDto request) {

        return chatService.chat(request);
    }
}