package ro.cyberit.library.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.cyberit.library.model.Publisher;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
