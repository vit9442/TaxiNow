package org.top.taxinow.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.taxinow.entity.Image;

@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {
}
