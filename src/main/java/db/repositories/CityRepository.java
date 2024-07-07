package db.repositories;

import db.domains.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Репозиторий для работы с сущностями городов.
 * Предоставляет CRUD-операции и поиск по идентификатору.
 */
@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}
