package com.my.diplom.services;

import com.my.diplom.entities.Item;
import com.my.diplom.entities.Question;
import com.my.diplom.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public final class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> findAll(){
        List<Item> items = repository.findAll();
        if (items == null)
            return new ArrayList<>();
        else return items;
    }
}
