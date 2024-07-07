package db.domains;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

/**
 * Класс City представляет сущность города в базе данных.
 * Он используется для хранения информации о различных городах.
 */
@Entity
@Table(name = "cities")
@Data
public class City {

    /**
     * Уникальный идентификатор города.
     * Значение генерируется автоматически и не может быть установлено вручную.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    /**
     * Название города.
     * Сохраняется в колонке 'name' таблицы 'cities'.
     */
    @Column(name = "name")
    private String name;
}