package ro.cyberit.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.cyberit.library.model.Book;
import ro.cyberit.library.repositories.AuthorRepository;
import ro.cyberit.library.repositories.BookRepository;
import ro.cyberit.library.repositories.PublisherRepository;

import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, PublisherRepository publisherRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
    }


    @RequestMapping("/books")
    public String getBooks(Model model) {

        model.addAttribute("books", bookRepository.findAll());

        return "books/list";
    }

    @RequestMapping("books/details/{id}")
    public String getBookDetails(@PathVariable Long id, Model model) throws Exception {

        Optional<Book> optBook = bookRepository.findById(id);

        if (optBook.isPresent()) {
            model.addAttribute("book", optBook.get());
            return "books/details";
        }
        throw new Exception();
    }

    @RequestMapping("books/add")
    public String newBook(Model model) {

        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("publishers", publisherRepository.findAll());
        model.addAttribute("book", new Book());

        return "books/add";
    }

    @RequestMapping(value = "books/add", method = RequestMethod.POST)
    public String createBook(Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }
}
