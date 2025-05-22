package bg.tuvarna.sit.newsblog.service.implement;

import bg.tuvarna.sit.newsblog.dto.auth.LoginDto;
import bg.tuvarna.sit.newsblog.dto.auth.RegisterDto;
import bg.tuvarna.sit.newsblog.entity.Role;
import bg.tuvarna.sit.newsblog.entity.RoleName;
import bg.tuvarna.sit.newsblog.entity.User;
import bg.tuvarna.sit.newsblog.exception.ResourceNotFoundException;
import bg.tuvarna.sit.newsblog.mapper.UserMapper;
import bg.tuvarna.sit.newsblog.repository.RoleRepository;
import bg.tuvarna.sit.newsblog.repository.UserRepository;
import bg.tuvarna.sit.newsblog.security.JwtTokenProvider;
import bg.tuvarna.sit.newsblog.service.interfaces.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    @Override
    public String login(HttpServletRequest req, LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(), loginDto.getPassword())
        );

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication);

       /* HttpSession session = req.getSession(true);
         session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
         return "User logged-in successfully!";*/
    }

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        else{
            Role userRole = roleRepository.findByName(RoleName.valueOf("COMMENTATOR"))
                    .orElseThrow(()-> new ResourceNotFoundException("Role", "COMMENTATOR"));

            User user = userMapper.toEntity(registerDto);
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            user.setRole(userRole);

            userRepository.save(user);

            return "User registered successfully!";
        }
    }

    @Override
    public void logout(HttpServletRequest req) {
        SecurityContextHolder.getContext().setAuthentication(null);
        SecurityContextHolder.clearContext();
        HttpSession hs = req.getSession();
        hs.invalidate();
    }
}

