package com.my.diplom.controllers;

import com.my.diplom.entities.Item;
import com.my.diplom.entities.Order;
import com.my.diplom.entities.User;
import com.my.diplom.services.OrderService;
import com.my.diplom.services.ItemService;
import com.my.diplom.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public final class MainController {

    private final UserService userService;
    private final ItemService itemService;

    private final OrderService orderService;

    public MainController(UserService userService, ItemService itemService, OrderService orderService) {
        this.userService = userService;
        this.itemService = itemService;
        this.orderService = orderService;
    }

    @GetMapping("/items")
    public String itemsPage(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Item> items = new ArrayList<>();
        items = itemService.findAll();
        model.addAttribute("items", items);
        return "questions";
    }

    @GetMapping("/orders/sale")
    public String saleOrdersPage(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> orders = new ArrayList<>();
        orders = orderService.findSellOrders();
        model.addAttribute("orders", orders);
        return "sale_orders";
    }

    @GetMapping("/order/buy")
    public String buyOrdersPage(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> orders = new ArrayList<>();
        orders = orderService.findBuyOrders();
        model.addAttribute("orders", orders);
        return "buy_orders";
    }
}
