package com.webapplication.main.resources;


import com.webapplication.main.entities.Item;
import com.webapplication.main.services.ItemService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Resource
@RequestMapping(value = "/items")
public class ItemResource {

    private final ItemService service;

    public ItemResource(ItemService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Item>> findAll() {
        List<Item> items = service.findAll();
        return ResponseEntity.ok().body(items);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Item> findById(@PathVariable Long id) {
        Item item = service.findById(id);
        return ResponseEntity.ok().body(item);
    }

    @PostMapping
    public ResponseEntity<Item> insert(@RequestBody Item obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Item> deleteById(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public  ResponseEntity<Item> update(@PathVariable Long id, @RequestBody Item obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}
