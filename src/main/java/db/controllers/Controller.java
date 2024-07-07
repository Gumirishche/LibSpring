package db.controllers;

import db.domains.Book;
import db.domains.City;
import db.domains.Lib;
import db.repositories.BookRepository;
import db.repositories.CityRepository;
import db.repositories.LibRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private LibRepository libRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/register")
    public String registrationPage() {
        return "registration";
    }

    @GetMapping("/login")
    public String signInPage() {
        return "login";
    }

    @GetMapping("/cities")
    public String tablePage(Model model) {
        List<City> tableDataCity = cityRepository.findAll();
        model.addAttribute("tableDataCity", tableDataCity);
        return "cities";
    }

    @PostMapping("/cities")
    @ResponseBody
    public ResponseEntity<?> editCity(@RequestBody City city) {
        // Проверка, существует ли город с таким ID
        System.out.println("EditCity is processing");
        Optional<City> existingCity = cityRepository.findById(city.getId());
        if (existingCity.isPresent()) {
            System.out.println("Введеный город: " + city.getId() + "  " + city.getName());
            // Если город существует, обновляем его данные
            City updatedCity = existingCity.get();
            System.out.println("Найденый город: " + updatedCity.getId() + "  " + updatedCity.getName());
            updatedCity.setName(city.getName());
            cityRepository.save(updatedCity);
            return ResponseEntity.ok(updatedCity); // Возвращаем обновленный объект города в формате JSON
        } else {
            // Если города с таким ID нет, создаем новый город
            System.out.println("Создание нового города: " + city.getId() + "  " + city.getName());
            City newCity = new City();
            newCity.setName(city.getName());
            cityRepository.save(newCity);
            return ResponseEntity.status(HttpStatus.CREATED).body(newCity); // Возвращаем созданный объект города в формате JSON
        }
    }
}
