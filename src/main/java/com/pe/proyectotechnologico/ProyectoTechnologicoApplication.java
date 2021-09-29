package com.pe.proyectotechnologico;

import com.pe.proyectotechnologico.Model.User;
import com.pe.proyectotechnologico.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoTechnologicoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoTechnologicoApplication.class, args);
    }

    @Autowired
    UserService service;

    @Override
    public void run(String... args) throws Exception {

        User admin = new User("admin","123","ADMIN");
        User user1 = new User("aduran","123","USER");
        User user2 = new User("croudebush","123","USER");
        User user3 = new User("asoto","123","USER");
        User user4 = new User("dvillafane","123","USER");
        User user5 = new User("bzamalloa","123","USER");

        service.create(admin);
        service.create(user1);
        service.create(user2);
        service.create(user3);
        service.create(user4);
        service.create(user5);
    }
}
