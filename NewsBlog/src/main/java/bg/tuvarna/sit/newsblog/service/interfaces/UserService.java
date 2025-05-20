package bg.tuvarna.sit.newsblog.service.interfaces;

import bg.tuvarna.sit.newsblog.dto.UserRegistrationDto;
import bg.tuvarna.sit.newsblog.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User register(UserRegistrationDto dto);
    Optional<User> findByUsername(String username);
}

