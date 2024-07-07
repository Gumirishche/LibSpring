package db.domains;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

/**
 * Класс User представляет сущность пользователя в базе данных.
 * Он используется для хранения информации о пользователях системы.
 */
@Entity
@Table(name = "users")
@Data
public class User {

    /**
     * Уникальный идентификатор пользователя.
     * Значение генерируется автоматически и не может быть установлено вручную.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    /**
     * Имя пользователя, используемое для входа в систему.
     * Сохраняется в колонке 'username' таблицы 'users'.
     */
    @Column(name = "username")
    private String username;

    /**
     * Пароль пользователя, используемый для аутентификации.
     * Сохраняется в колонке 'password' таблицы 'users'.
     */
    @Column(name = "password")
    private String password;

    /**
     * Статус активности пользователя в системе.
     * Если значение true, пользователь может входить в систему.
     */
    @Column(name = "enabled")
    private boolean enabled;

    /**
     * Роль пользователя в системе, определяющая уровень доступа.
     * Сохраняется в колонке 'role' таблицы 'users'.
     */
    @Column(name = "role")
    private String role;
}

