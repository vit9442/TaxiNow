package org.top.taxinow.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.top.taxinow.entity.User;
import org.top.taxinow.repository.UserRepository;
import org.top.taxinow.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean register(String name, String login, String password, String passwordConfirmation) {
        if (!password.equals(passwordConfirmation) || userRepository.findUserByLogin(login).isPresent()) {
            return false;
        }
        userRepository.save(User
                .builder()
                .name(name)
                .login(login)
                .password(passwordEncoder.encode(password))
                .role("USER")
                .build()
        );
        return true;
    }

}
