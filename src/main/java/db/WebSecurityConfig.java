package db;

import db.repositories.UserRepository;
import db.domains.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

/**
 * Конфигурационный класс для настройки параметров безопасности веб-приложения.
 * Включает в себя аннотации @Configuration и @EnableWebSecurity для активации
 * настроек безопасности Spring Security.
 *
 * Отключает CSRF-защиту для определенных путей и настраивает обработку аутентификации
 * и авторизации запросов, а также конфигурацию формы входа и выхода из системы.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // Поля класса с аннотацией @Autowired для внедрения зависимостей
    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Настраивает параметры безопасности HTTP-запросов.
     * @param http Объект HttpSecurity для конфигурации.
     * @throws Exception Исключение в случае ошибок конфигурации.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Отключение CSRF-защиты
                .authorizeRequests()
                .antMatchers("/", "/register", "/tables", "/cities").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    /**
     * Создает репозиторий токенов CSRF с пользовательской логикой генерации и сохранения токенов.
     * @return CsrfTokenRepository Кастомный репозиторий токенов CSRF.
     */
    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        CookieCsrfTokenRepository repository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        repository.setCookiePath("/");
        return new CsrfTokenRepository() {
            @Override
            public CsrfToken generateToken(HttpServletRequest request) {
                CsrfToken token = repository.generateToken(request);
                // Логирование токена
                System.out.println("CSRF Token: " + token.getToken());
                return token;
            }

            @Override
            public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
                repository.saveToken(token, request, response);
            }

            @Override
            public CsrfToken loadToken(HttpServletRequest request) {
                return repository.loadToken(request);
            }
        };
    }

    /**
     * Настраивает менеджер аутентификации с пользовательским сервисом UserDetailsService
     * и кодировщиком паролей.
     * @param auth Объект AuthenticationManagerBuilder для конфигурации.
     * @throws Exception Исключение в случае ошибок конфигурации.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * Создает бин кодировщика паролей, использующий BCrypt.
     * @return PasswordEncoder Кодировщик паролей.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Определяет бин UserDetailsService, использующий репозиторий пользователей
     * для загрузки данных пользователя по имени пользователя.
     * @param userRepository Репозиторий пользователей для поиска.
     * @return UserDetailsService Сервис для загрузки данных пользователя.
     */
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("Пользователь не найден");
            }else {
                System.out.println("Пользователь найден!");
            }

            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        };
    }
}