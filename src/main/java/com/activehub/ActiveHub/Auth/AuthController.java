package com.activehub.ActiveHub.Auth;

import com.activehub.ActiveHub.models.AccessToken;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

import static com.activehub.ActiveHub.utils.StringUtils.prettyJson;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        log.info("Login: " + prettyJson(request));
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        log.info("Register: " + prettyJson(request));
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping(value = "/logout")
    public ResponseEntity<AuthResponse> logout(@RequestHeader("Authorization") String token) throws Exception {
        log.info("Logout: " + token);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setRequestId(String.valueOf(UUID.randomUUID()));
        if (token != null) {
            AccessToken accessToken = authService.logout(token);
            authResponse.setResponse(accessToken);
        }
        return ResponseEntity.ok(authResponse);
    }

}
