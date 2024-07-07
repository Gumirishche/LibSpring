package db.domains;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

/**
 * Класс Lib представляет сущность библиотеки в базе данных.
 * Он используется для хранения информации о библиотеках и их связи с городами.
 */
@Entity
@Table(name = "libs")
@Data
public class Lib {

    /**
     * Уникальный идентификатор библиотеки.
     * Значение генерируется автоматически и не может быть установлено вручную.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    /**
     * Название библиотеки.
     * Сохраняется в колонке 'name' таблицы 'libs'.
     */
    @Column(name = "name")
    private String name;

    /**
     * Связь с сущностью города.
     * Определяет город, в котором находится библиотека.
     * Связь многие к одному, указывает на сущность города, связанную с библиотекой.
     */
    @ManyToOne
    @JoinColumn(name = "id_city")
    private City idCity;
}
