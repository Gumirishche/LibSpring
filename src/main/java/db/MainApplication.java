package db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс для запуска Spring Boot приложения.
 * Использует аннотацию @SpringBootApplication, которая включает в себя:
 * - @Configuration: Теги класс как источник определений компонентов бина.
 * - @EnableAutoConfiguration: Говорит Spring Boot начать добавлять бины на основе настроек пути класса,
 *   других бинов и различных настроек свойств.
 * - @ComponentScan: Говорит Spring искать другие компоненты, конфигурации и сервисы в текущем пакете,
 *   позволяя ему находить контроллеры и другие компоненты.
 *
 * @param args Аргументы командной строки, передаваемые при запуске приложения.
 */
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}