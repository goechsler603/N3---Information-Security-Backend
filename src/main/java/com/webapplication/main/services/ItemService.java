package com.webapplication.main.services;

import com.webapplication.main.entities.Item;
import com.webapplication.main.repositories.ItemRepository;
import com.webapplication.main.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    public ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public Item findById(Long id) {
        Optional<Item> item = repository.findById(id);
        return item.orElseThrow(()  -> new NotFoundException(id));
    }

    public List<Item> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.findById(id).orElseThrow(() -> new NotFoundException("Item not found, ID = " + id));
        repository.deleteById(id);
    }

    public Item insert(Item obg) {
        return repository.save(obg);
    }

    public void updateData (Item entity, Item obj) {
        entity.setName(obj.getName());
        entity.setPrice(obj.getPrice());
    }

    public Item update (Long id,  Item obj) {
        repository.findById(id).orElseThrow(() -> new NotFoundException("Item not found, ID = " + id));
        Item item = repository.findById(id).get();
        updateData(item, obj);
        return repository.save(item);
    }

}
