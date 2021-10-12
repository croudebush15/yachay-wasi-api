package com.pe.proyectotechnologico;

import com.pe.proyectotechnologico.Model.Teacher;
import com.pe.proyectotechnologico.Model.User;
import com.pe.proyectotechnologico.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class ProyectoTechnologicoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoTechnologicoApplication.class, args);
    }

    @Autowired
    UserService service;

    @Override
    public void run(String... args) throws Exception {

        /*User user_admin = new User("admin","123");
        User user1 = new User("aduran","123");
        User user2 = new User("croudebush","123");
        User user3 = new User("asoto","123");
        User user4 = new User("dvillafane","123");
        User user5 = new User("bzamalloa","123");

        Teacher admin = new Teacher();
        admin.setFirstName("Admin");
        admin.setUser(user_admin);
        admin.setRole("ADMIN");
        admin.setStatus(true);
        user_admin.setTeacher(admin);
        service.create(user_admin);

        Teacher teacher1 = new Teacher();
        teacher1.setFirstName("André");
        teacher1.setLastName("Durán");
        teacher1.setUser(user1);
        teacher1.setRole("USER");
        teacher1.setStatus(true);
        teacher1.setBirthDate(new Date());
        user1.setTeacher(teacher1);
        service.create(user1);

        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Chris");
        teacher2.setLastName("Roudebush");
        teacher2.setUser(user2);
        teacher2.setRole("USER");
        teacher2.setStatus(false);
        user2.setTeacher(teacher2);
        service.create(user2);

        Teacher teacher3 = new Teacher();
        teacher3.setFirstName("Ana");
        teacher3.setLastName("Soto");
        teacher3.setUser(user3);
        teacher3.setRole("USER");
        user3.setTeacher(teacher3);
        service.create(user3);

        Teacher teacher4 = new Teacher();
        teacher4.setFirstName("David");
        teacher4.setLastName("Villafane");
        teacher4.setUser(user4);
        teacher4.setRole("USER");
        user4.setTeacher(teacher4);
        service.create(user4);

        Teacher teacher5 = new Teacher();
        teacher5.setFirstName("Bryan");
        teacher5.setLastName("Zamalloa");
        teacher5.setUser(user5);
        teacher5.setRole("USER");
        user5.setTeacher(teacher5);
        service.create(user5);*/
    }
}
