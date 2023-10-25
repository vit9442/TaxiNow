package org.top.taxinow.service;

import org.springframework.stereotype.Service;
import org.top.taxinow.entity.Image;


import java.util.Optional;

@Service
public interface ImageService {

    Iterable<Image> findAll();

    Optional<Image> findById(Integer id);

    Image add(Image image);

}
