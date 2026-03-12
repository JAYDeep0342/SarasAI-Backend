package ips.SarasAI.controller;

import ips.SarasAI.dto.LoginRequestDto;
import ips.SarasAI.dto.LoginResponseDto;
import ips.SarasAI.dto.SignupRequestDto;
import ips.SarasAI.dto.SignupResponseDto;
import ips.SarasAI.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")

@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto dto) {
        return ResponseEntity.ok(authService.signup(dto));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
}