package db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import db.domains.User;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностями пользователей.
 * Предоставляет CRUD-операции и поиск по идентификатору.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}