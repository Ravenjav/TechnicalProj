package com.my.diplom.services;

import com.my.diplom.repository.OrderRepository;
import com.my.diplom.entities.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAllOrders(){
        List<Order> orders = orderRepository.findAll();
        return orders;
    }

    public List<Order> findSellOrders(){
        List<Order> orders = findAllOrders();
        List<Order> sellOrders = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++){
            if (orders.get(i).getType().equals("sell"));
                sellOrders.add(orders.get(i));
        }
        return sellOrders;
    }

    public List<Order> findBuyOrders(){
        List<Order> orders = findAllOrders();
        List<Order> buyOrders = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++){
            if (orders.get(i).getType().equals("buy"));
            buyOrders.add(orders.get(i));
        }
        return buyOrders;
    }
}
