package com.pe.proyectotechnologico.Controller;


import com.pe.proyectotechnologico.Model.Teacher;
import com.pe.proyectotechnologico.Service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getTeachers(){
        return new ResponseEntity<>(teacherService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable Integer id){
        Teacher currentTeacher = teacherService.findById(id);
        if( null == currentTeacher){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentTeacher, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Teacher> postTeacher(@RequestBody Teacher teacher){
        teacherService.create(teacher);
        return new ResponseEntity<>(teacher,HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Teacher> putTeacher(@PathVariable Integer id,
                                              @RequestBody Teacher teacher){

        if( null == teacherService.findById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        teacher.setIdTeacher(id);
        teacherService.update(teacher);
        return new ResponseEntity<>(teacher,HttpStatus.OK);

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable Integer id){
        if( null == teacherService.findById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        teacherService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
