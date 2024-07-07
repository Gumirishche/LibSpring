package db.domains;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

/**
 * Класс {@code Book} представляет собой сущность книги в библиотеке.
 * Он содержит информацию о названии, жанре, а также связи с библиотекой и пользователями.
 */
@Entity
@Table(name = "books")
@Data
public class Book {
    /**
     * Уникальный идентификатор книги.
     *  Значение генерируется автоматически и не может быть установлено вручную.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    /**
     * Название книги.
     */
    @Column(name = "name")
    private String name;

    /**
     * Библиотека, к которой принадлежит книга.
     */
    @ManyToOne
    @JoinColumn(name = "id_lib")
    private Lib idLib;

    /**
     * Предыдущая часть этой книги, если таковая имеется.
     */
    @ManyToOne
    @JoinColumn(name = "prev_part", referencedColumnName = "id")
    private Book prevPart;

    /**
     * Жанр книги.
     */
    @Column(name = "genre")
    private String genre;

    /**
     * Пользователь, которому принадлежит книга.
     */
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User idUser;
}
