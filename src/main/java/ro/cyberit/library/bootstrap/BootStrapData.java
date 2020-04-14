package ro.cyberit.library.bootstrap;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.cyberit.library.model.Author;
import ro.cyberit.library.model.Book;
import ro.cyberit.library.model.Publisher;
import ro.cyberit.library.repositories.AuthorRepository;
import ro.cyberit.library.repositories.BookRepository;
import ro.cyberit.library.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Faker");

        Faker faker = new Faker();

        for (int i = 0; i < 20 ; i++) {
            Book book = new Book();
            book.setTitle(faker.book().title());
            book.setIsbn(String.valueOf(faker.number().randomNumber(5,true)));

            Author author = new Author();
            author.setFirstName(faker.address().firstName());
            author.setLastName(faker.address().lastName());

            Publisher publisher = new Publisher();
            publisher.setName(faker.company().name());
            publisher.setCity(faker.address().city());
            publisher.setState(faker.address().stateAbbr());
            publisher.setAddressLine1(faker.address().streetName());
            publisher.setZip((int) faker.number().randomNumber());

            author.getBooks().add(book);
            book.getAuthors().add(author);
            publisher.getBooks().add(book);

            authorRepository.save(author);
            bookRepository.save(book);
            publisherRepository.save(publisher);
        }

        System.out.println("Publishers Count: " + publisherRepository.count());
        System.out.println("Authors Count: " + authorRepository.count());
        System.out.println("Books Count: " + bookRepository.count());
    }
}
