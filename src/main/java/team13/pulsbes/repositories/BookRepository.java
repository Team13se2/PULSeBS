package team13.pulsbes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team13.pulsbes.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
}
