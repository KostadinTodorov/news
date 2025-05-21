package bg.tuvarna.sit.newsblog.controller;

import bg.tuvarna.sit.newsblog.dto.auth.JwtAuthResponseDto;
import bg.tuvarna.sit.newsblog.dto.auth.LoginDto;
import bg.tuvarna.sit.newsblog.dto.auth.RegisterDto;
import bg.tuvarna.sit.newsblog.service.interfaces.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/newsblog")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/public/login")
    public ResponseEntity<JwtAuthResponseDto> login(HttpServletRequest request, @RequestBody LoginDto loginDto) {
        String token = authService.login(request, loginDto);
        JwtAuthResponseDto responseDto = new JwtAuthResponseDto();
        responseDto.setAccessToken(token);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/public/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/public/logout")
    public ResponseEntity<String> logout(HttpServletRequest req) {
        authService.logout(req);
        return new ResponseEntity<>("Logout successfully!", HttpStatus.OK);
    }

}

