package ips.SarasAI.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRequestDto {
    private Long conversationId;
    private String message;

}
