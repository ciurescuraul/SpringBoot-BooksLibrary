package ro.cyberit.library.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.cyberit.library.model.Book;

public interface BookRepository extends CrudRepository<Book, Long>{

}
