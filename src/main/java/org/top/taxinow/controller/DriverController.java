package org.top.taxinow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.taxinow.entity.Car;
import org.top.taxinow.entity.Driver;
import org.top.taxinow.entity.Image;
import org.top.taxinow.service.impl.CarServiceImpl;
import org.top.taxinow.service.impl.DriverServiceImpl;
import org.top.taxinow.service.impl.ImageServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("driver")
@RequiredArgsConstructor
public class DriverController {
    private final DriverServiceImpl driverService;
    private final CarServiceImpl carService;
    private final ImageServiceImpl imageService;

    @GetMapping("")
    public String findAll(Model model){
        Iterable<Driver> drivers = driverService.findAll();
        if (drivers.iterator().hasNext()) {
            model.addAttribute("drivers", drivers);
        } else {
            model.addAttribute("drivers", null);
        }
        return "driver/driver-list";
    }

    @GetMapping("new")
    public String addDriver(Model model){
        Driver driver = new Driver();
        model.addAttribute("driver", driver);
        ArrayList<Car> carList = (ArrayList<Car>) carService.findAll();
        model.addAttribute("cars", carList.stream().filter(car -> car.getDriver() == null).collect(Collectors.toList()));
        return "driver/new-driver-form";
    }

    @PostMapping("new")
    public String addDriver( Driver driver,@RequestParam MultipartFile imagePhoto, RedirectAttributes redirectAttributes) throws IOException {
        String imageData = Base64.getEncoder().encodeToString(imagePhoto.getBytes());
        Image newImage = new Image();
        newImage.setData(imageData);
        newImage = imageService.add(newImage);
        driver.setImage(newImage);
        Optional<Driver> newDriver = driverService.add(driver);
        if(newDriver.isPresent()){
            redirectAttributes.addFlashAttribute("successMessage", "Водитель " + driver.getName() + " успешно добавлен");
        } else
            redirectAttributes.addFlashAttribute("errMessage", "Водитель " + driver.getName() + " не был добавлен");
        return "redirect:/driver";
    }

    @GetMapping("/delete/{id}")
    public String deleteByID(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        Optional<Driver> removableDriver = driverService.deleteById(id);
        if(removableDriver.isPresent()){
            redirectAttributes.addFlashAttribute("successMessage", "Водитель " + removableDriver.get().getName() + "был успешно удалён");
            return "redirect:/driver";
        }
        else {
            redirectAttributes.addFlashAttribute("errMessage", "Водитель не был удалён");
        }
        return "redirect:/driver";
    }

    @GetMapping("{id}")
    public String details(@PathVariable Integer id, Model model){
        Optional<Driver> driver = driverService.findById(id);

        if(driver.isPresent()) {
            model.addAttribute("driver", driver.get());
        } else {
            model.addAttribute("driver", null);
        }
        return "driver/driver-details";
    }

    @GetMapping("update/{id}")
    public String updateCar(@PathVariable Integer id, Model model){

        Optional<Driver> driver = driverService.findById(id);

        if(driver.isPresent()) {
            driver.get().setCar(null);
            Iterable<Car> carList = carService.findAll();
            model.addAttribute("cars", carList);
            model.addAttribute("driver", driver.get());
        } else {
            model.addAttribute("driver", null);
        }
        return "driver/driver-edit";
    }

    @PostMapping("update/{id}")
    public String updateCar(@PathVariable Integer id, Driver driver, RedirectAttributes redirectAttributes){
        Optional<Driver> driverUpdated =  driverService.updateById(id, driver);
        if(driverUpdated.isPresent()){
            redirectAttributes.addFlashAttribute("successMessage", "Водитель " + driver.getName() + " успешно обновлён");
        } else
            redirectAttributes.addFlashAttribute("errMessage", "Водитель" + driver.getName() + " не был обновлён");
        return "redirect:/driver";
    }

}


