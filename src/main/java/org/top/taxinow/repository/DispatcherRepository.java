package org.top.taxinow.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.taxinow.entity.Dispatchers;

import java.util.Optional;

@Repository
public interface DispatcherRepository extends CrudRepository<Dispatchers, Integer> {
    Optional<Dispatchers> findDispatchersByLogin(String login);

}
