package db.repositories;

import db.domains.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностями книг.
 * Предоставляет CRUD-операции и поиск по идентификатору.
 */
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
