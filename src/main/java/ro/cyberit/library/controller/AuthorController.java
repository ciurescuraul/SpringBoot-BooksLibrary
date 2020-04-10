package ro.cyberit.library.controller;

import com.github.javafaker.Faker;
import ro.cyberit.library.model.Author;

import java.util.HashMap;
import java.util.Map;

public class AuthorController {

    private Map<Long, Author> initAuthors() {

        Faker faker = new Faker();
        Map<Long, Author> authors = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            Author author = new Author();
            long id = 20 + i;
            author.setId(id);
            author.setFirstName(faker.name().firstName());
            author.setLastName(faker.name().lastName());

            authors.put(id, author);
        }
        return authors;
    }
}
