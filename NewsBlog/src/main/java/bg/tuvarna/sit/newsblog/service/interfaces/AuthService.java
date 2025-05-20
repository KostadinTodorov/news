package bg.tuvarna.sit.newsblog.service.interfaces;

import bg.tuvarna.sit.newsblog.dto.LoginDto;
import bg.tuvarna.sit.newsblog.dto.RegisterDto;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    String login(HttpServletRequest req, LoginDto loginDto);
    String register(RegisterDto registerDto);
    void logout(HttpServletRequest req);
}
