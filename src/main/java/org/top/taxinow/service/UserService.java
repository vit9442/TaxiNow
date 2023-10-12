package org.top.taxinow.service;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.top.taxinow.entity.Driver;
import org.top.taxinow.entity.TaxiOrder;
import org.top.taxinow.entity.User;

import java.util.Optional;
import java.util.UUID;

@Service
public interface UserService  {

    boolean register(String name, String login, String password, String passwordConfirmation);

    Iterable<User> findAll();

    Optional<User> findById(Integer id);

    Optional<User> deleteById(Integer  id);

    Optional<User> add(User user);

    Optional<User> updateById(Integer id, User user);

}
