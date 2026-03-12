package ips.SarasAI.service;

import ips.SarasAI.dto.LoginRequestDto;
import ips.SarasAI.dto.LoginResponseDto;
import ips.SarasAI.dto.SignupRequestDto;
import ips.SarasAI.dto.SignupResponseDto;
import ips.SarasAI.entity.User;
import ips.SarasAI.entity.type.Role;
import ips.SarasAI.repository.UserRepository;
import ips.SarasAI.security.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;


    public SignupResponseDto signup(SignupRequestDto dto) {

        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .roles(Set.of(Role.USER))
                .build();

        userRepository.save(user);

        return new SignupResponseDto(user.getId(), user.getUsername());
    }

    public LoginResponseDto login(LoginRequestDto dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );

        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = authUtil.generateAccessToken(user);


        return new LoginResponseDto(
                token,
                user.getId()
        );

    }
}
