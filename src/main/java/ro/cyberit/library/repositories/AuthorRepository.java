package ro.cyberit.library.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.cyberit.library.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
