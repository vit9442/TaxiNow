package org.top.taxinow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.taxinow.entity.Car;
import org.top.taxinow.service.impl.CarServiceImpl;

import java.util.Optional;

@Controller
@RequestMapping("car")
@RequiredArgsConstructor
public class CarController {
    private final CarServiceImpl carService;

    @GetMapping("")
    public String findAll(Model model){
        Iterable<Car> cars = carService.findAll();
        if (cars.iterator().hasNext()) {
            model.addAttribute("cars", cars);
        } else {
            model.addAttribute("cars", null);
        }
        return "car/car-list";
    }

    @GetMapping("new")
    public String addCar(Model model){
        Car car = new Car();
        model.addAttribute("car", car);
         return "car/new-car-form";
    }

    @PostMapping("new")
    public String addCar(Car car, RedirectAttributes redirectAttributes){
        Optional<Car> newCar = carService.add(car);
        if(newCar.isPresent()){
            redirectAttributes.addFlashAttribute("successMessage", "Автомобиль " + car.getName() + " успешно добавлен");
        } else
            redirectAttributes.addFlashAttribute("errMessage", "Автомобиль" + car.getName() + " не был добавлен");
        return "redirect:/car";
    }

    @GetMapping("/delete/{id}")
    public String deleteByID(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        Optional<Car> removableCar = carService.deleteById(id);
        if(removableCar.isPresent()){
            redirectAttributes.addFlashAttribute("successMessage", "Автомобиль " + removableCar.get().getName() + "был успешно удалён");
            return "redirect:/car";
        }
       else {
            redirectAttributes.addFlashAttribute("errMessage", "Автомобиль не был удалён");
        }
        return "redirect:/car";
    }

    @GetMapping("{id}")
    public String details(@PathVariable Integer id, Model model){
        Optional<Car> car = carService.findById(id);

        if(car.isPresent()) {
            model.addAttribute("car", car.get());
        } else {
            model.addAttribute("car", null);
        }
        return "car/car-details";
    }

    @GetMapping("update/{id}")
    public String updateCar(@PathVariable Integer id, Model model){
        Optional<Car> car = carService.findById(id);
        if(car.isPresent()) {
            model.addAttribute("car", car.get());
        } else {
            model.addAttribute("car", null);
        }
        return "car/car-edit";
    }

    @PostMapping("update/{id}")
    public String updateCar(@PathVariable Integer id, Car car, RedirectAttributes redirectAttributes){
      Optional<Car> carUpdated =  carService.updateById(id, car);

        if(carUpdated .isPresent()){
            redirectAttributes.addFlashAttribute("successMessage", "Автомобиль " + car.getName() + " успешно обновлён");
        } else
            redirectAttributes.addFlashAttribute("errMessage", "Автомобиль" + car.getName() + " не был обновлён");
        return "redirect:/car";
    }

}
