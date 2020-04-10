package ro.cyberit.library.controller;

import com.github.javafaker.Faker;
import ro.cyberit.library.model.Shelf;

import java.util.HashMap;
import java.util.Map;

public class ShelfController {

    private Map<Long, Shelf> initShelves() {

        Faker faker = new Faker();
        Map<Long, Shelf> shelves = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            Shelf shelf = new Shelf();
            long id = 30 + i;
            shelf.setId(id);
            shelf.setGenre(faker.book().genre());

            shelves.put(id, shelf);
        }
        return shelves;
    }
}
