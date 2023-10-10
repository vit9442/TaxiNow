package org.top.taxinow.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    boolean register(String name, String login, String password, String passwordConfirmation);

}
