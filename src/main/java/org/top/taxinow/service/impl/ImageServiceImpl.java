package org.top.taxinow.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.top.taxinow.entity.Image;
import org.top.taxinow.repository.ImageRepository;
import org.top.taxinow.service.ImageService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Iterable<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Optional<Image> findById(Integer id) {
        return imageRepository.findById(id);
    }

    @Override
    public Image add(Image image) {
        return imageRepository.save(image);

    }
}
