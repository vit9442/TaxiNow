package org.top.taxinow.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.top.taxinow.entity.User;
import org.top.taxinow.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RdbUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository dispatcherRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> dispatcher = dispatcherRepository.findUserByLogin(username);
        if(dispatcher.isEmpty()){
            return null;
        }
        UserDetails dispatcherDetails = new UserDetails();
        dispatcherDetails.setDispatchers(dispatcher.get());
        return dispatcherDetails;
    }
}
