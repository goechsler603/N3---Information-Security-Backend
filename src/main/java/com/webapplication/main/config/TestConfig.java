package com.webapplication.main.config;

import com.webapplication.main.entities.Item;
import com.webapplication.main.repositories.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
@AllArgsConstructor
@Profile("test")
public class TestConfig implements CommandLineRunner {

    private final ItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception {

        Item item1 = Item.builder()
                .id(null)
                .name("T-shirt")
                .price(12.)
                .build();

        Item item2 = Item.builder()
                .id(null)
                .name("Tv")
                .price(1200.50)
                .build();

        Item item3 = Item.builder()
                .id(null)
                .name("Underpants")
                .price(5.76)
                .build();

        itemRepository.saveAll(Arrays.asList(item1, item2, item3));

    }
}
