package org.top.taxinow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.top.taxinow.entity.Image;
import org.top.taxinow.service.impl.ImageServiceImpl;

import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping("image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageServiceImpl imageService;

    @GetMapping("")
    public String findAll(Model model){
        Iterable<Image> imageList = imageService.findAll();
        model.addAttribute("images", imageList);
        return "image/image-list";
    }
    @GetMapping("new")
    public String addNew(){
        return "image/image-form";
    }

    @PostMapping("new")
    public String addNew(@RequestParam MultipartFile image) throws IOException {
        String imageData = Base64.getEncoder().encodeToString(image.getBytes());
        Image newImage = new Image();
        newImage.setData(imageData);
        imageService.add(newImage);
        return "redirect:/image";
    }
}
