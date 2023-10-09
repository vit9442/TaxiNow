package org.top.taxinow.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.top.taxinow.entity.Dispatchers;
import org.top.taxinow.repository.DispatcherRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DispatcherDetailsService implements UserDetailsService {

    private final DispatcherRepository dispatcherRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Dispatchers> dispatcher = dispatcherRepository.findDispatchersByLogin(username);
        if(dispatcher.isEmpty()){
            return null;
        }
        DispatcherDetails dispatcherDetails = new DispatcherDetails();
        dispatcherDetails.setDispatchers(dispatcher.get());
        return dispatcherDetails;
    }
}
