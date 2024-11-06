package com.activehub.ActiveHub.Auth;

import com.activehub.ActiveHub.Repository.RoleRepository;
import com.activehub.ActiveHub.Repository.TokenRepository;
import com.activehub.ActiveHub.Repository.UserRepository;
import com.activehub.ActiveHub.models.AccessToken;
import com.activehub.ActiveHub.models.DecodeToken;
import com.activehub.ActiveHub.models.User;
import com.activehub.ActiveHub.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static com.activehub.ActiveHub.utils.JwtUtils.decodeJwtPayload;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);

        AccessToken accessToken = tokenRepository.findByUsername(request.getUsername()).orElse(new AccessToken());

        accessToken.setUsername(request.getUsername());
        accessToken.setSessionOpen(true);
        accessToken.setLastSession(new Date());

        tokenRepository.save(accessToken);

        return AuthResponse.builder()
                .requestId(String.valueOf(UUID.randomUUID()))
                .response(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request){


        AccessToken accessToken = tokenRepository.findByUsername(request.getEmail()).orElse(new AccessToken());

        accessToken.setUsername(request.getEmail());
        accessToken.setSessionOpen(true);
        accessToken.setLastSession(new Date());

        tokenRepository.save(accessToken);

        User user = User.builder()
                .username(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .country(request.getCountry())
                .status(true)
                .creationDate(new Date())
                .role(roleRepository.findByName("Participante"))
                .build();

        userRepository.save(user);
        return AuthResponse.builder()
                .requestId(String.valueOf(UUID.randomUUID()))
                .response(jwtService.getToken(user))
                .build();
    }

    public AccessToken logout(String jwt) throws Exception {

        DecodeToken token = decodeJwtPayload(jwt);

        AccessToken accessToken = tokenRepository.findByUsername(token.getEmail()).orElse(new AccessToken());

        accessToken.setUsername(token.getEmail());
        accessToken.setSessionOpen(true);
        accessToken.setLastSession(new Date());

        tokenRepository.save(accessToken);
        User user = userRepository.findByUsername(token.getEmail()).orElse(null);
        if(user != null){
            accessToken.setUsername(user.getUsername());
            accessToken.setSessionOpen(false);
            accessToken.setAccessValidity(null);
            accessToken.setLastSession(new Date());
            tokenRepository.save(accessToken);
        }

        return accessToken;
    }

}
