package org.top.taxinow.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.top.taxinow.entity.Driver;
import org.top.taxinow.entity.User;
import org.top.taxinow.repository.UserRepository;
import org.top.taxinow.service.UserService;

import java.util.Optional;

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

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> deleteById(Integer id) {
        Optional<User> removableDriver = findById(id);
        if(findById(id).isPresent()){
            userRepository.deleteById(id);
        }
        userRepository.deleteById(id);
        return removableDriver;
    }

    @Override
    public Optional<User> add(User user) {
        return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<User> updateById(Integer id, User user) {
        Optional<User> userUpdated = findById(id);

        if(userUpdated.isPresent()){
            user.setId(id);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return Optional.of(userRepository.save(user));
        } else {
            return Optional.empty();
        }
    }}
