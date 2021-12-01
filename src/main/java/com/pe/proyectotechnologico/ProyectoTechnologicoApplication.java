package com.pe.proyectotechnologico;

import com.pe.proyectotechnologico.Model.Classroom;
import com.pe.proyectotechnologico.Model.Teacher;
import com.pe.proyectotechnologico.Model.User;
import com.pe.proyectotechnologico.Service.ClassroomService;
import com.pe.proyectotechnologico.Service.LessonService;
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
    LessonService lessonService;

    @Autowired
    ClassroomService classroomService;

    @Override
    public void run(String... args) throws Exception {
        Classroom classroom = classroomService.findById(101);
        //lessonService.generateWeeklyLessonsForClassroom(classroom,15,"2021-12-06");

    }
}
