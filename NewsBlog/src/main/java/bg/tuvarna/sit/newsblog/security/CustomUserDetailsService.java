package bg.tuvarna.sit.newsblog.security;

import bg.tuvarna.sit.newsblog.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import bg.tuvarna.sit.newsblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with username or email" + username ));

        return new CustomUserDetails(user);
    }
}

