package org.top.taxinow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.taxinow.entity.Driver;
import org.top.taxinow.entity.TaxiOrder;
import org.top.taxinow.service.impl.DriverServiceImpl;
import org.top.taxinow.service.impl.TaxiOrderServiceImpl;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("order")
@RequiredArgsConstructor
public class TaxiOrderController {

    private final TaxiOrderServiceImpl taxiOrderService;
    private final DriverServiceImpl driverService;


    @GetMapping("")
    public String findAll(Model model){
        Iterable<TaxiOrder> orders = taxiOrderService.findAll();
        if (orders.iterator().hasNext()) {
            model.addAttribute("orders", orders);
        } else {
            model.addAttribute("orders", null);
        }
        return "order/order-list";
    }

    @GetMapping("new")
    public String addOrder(Model model){
        TaxiOrder order = new TaxiOrder();
        model.addAttribute("order", order);
        Iterable<Driver> driverList = driverService.findAll();
        model.addAttribute("drivers", driverList);
        return "order/new-order-form";
    }

    @PostMapping("new")
    public String addDriver(TaxiOrder order, LocalDateTime orderTime,  RedirectAttributes redirectAttributes){
        Optional<TaxiOrder> newOrder = taxiOrderService.add(order, orderTime);
        if(newOrder.isPresent()){
            redirectAttributes.addFlashAttribute("successMessage", "Заказ успешно добавлен");
        } else
            redirectAttributes.addFlashAttribute("errMessage", "Заказ не был добавлен");
        return "redirect:/order";
    }

}
