package db.repositories;

import db.domains.Lib;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностями библиотек.
 * Предоставляет CRUD-операции и поиск по идентификатору.
 */
@Repository
public interface LibRepository extends JpaRepository<Lib,Integer> {
}
