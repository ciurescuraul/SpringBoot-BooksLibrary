package ro.cyberit.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.cyberit.library.model.Book;
import ro.cyberit.library.repositories.BookRepository;

import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @RequestMapping(value = "/books")
    public String getBooks(Model model){

        model.addAttribute("books", bookRepository.findAll());

        return "books/list";
    }

    @RequestMapping(value = "books/details/{id}")
    public String getBookDetails(@PathVariable Long id, Model model) throws Exception {

        Optional<Book> optBook = bookRepository.findById(id);

        if (optBook.isPresent()){
            model.addAttribute("book",optBook.get());
            return "books/details";
        }
        throw new Exception();
    }
}
