package ro.cyberit.library.controller;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.cyberit.library.model.Book;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private Map<Long, Book> books = initBooks();

    @RequestMapping(value = "/all")
    public String index(Model model) {
        logger.debug("Entered books index");

        model.addAttribute("books", books);

        return "books/all";
    }


    private Map<Long, Book> initBooks() {

        Faker faker = new Faker();
        Map<Long, Book> books = new HashMap<>();

        for (int i = 0; i < 50; i++) {
            Book book = new Book();
            long id = 10 + i;
            book.setId(id);
            book.setTitle(faker.book().title());
            book.setIsbn((long) faker.number().numberBetween(2345, 4567));
            book.setGenre(faker.book().genre());
            book.setDescription(faker.lorem().paragraph());
            book.setPrice(faker.number().randomDouble(3, 100, 900));

            books.put(id, book);
        }
        return books;
    }
}
