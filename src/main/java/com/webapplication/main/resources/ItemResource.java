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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/items")
@Tag(name = "Itens", description = "Operações relacionadas aos itens do estoque")
public class ItemResource {

    private final ItemService service;

    public ItemResource(ItemService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar todos os itens", description = "Retorna uma lista de todos os itens cadastrados.")
    public ResponseEntity<List<Item>> findAll() {
        List<Item> items = service.findAll();
        return ResponseEntity.ok().body(items);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar item por ID", description = "Retorna um item com o ID informado.")
    public ResponseEntity<Item> findById(
            @Parameter(description = "ID do item a ser buscado") @PathVariable Long id) {
        Item item = service.findById(id);
        return ResponseEntity.ok().body(item);
    }

    @PostMapping
    @Operation(summary = "Criar novo item", description = "Insere um novo item no estoque.")
    public ResponseEntity<Item> insert(
            @Parameter(description = "Objeto item a ser criado") @RequestBody Item obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar item", description = "Remove um item do estoque pelo ID.")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ID do item a ser deletado") @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar item", description = "Atualiza os dados de um item pelo ID.")
    public ResponseEntity<Item> update(
            @Parameter(description = "ID do item a ser atualizado") @PathVariable Long id,
            @Parameter(description = "Novos dados do item") @RequestBody Item obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
