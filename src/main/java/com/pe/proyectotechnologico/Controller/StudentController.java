package com.pe.proyectotechnologico.Controller;

import com.pe.proyectotechnologico.Model.Student;
import com.pe.proyectotechnologico.Service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Integer id){
        Student currentStudent = studentService.findById(id);
        if( null == currentStudent){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentStudent, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Student> postStudent(@RequestBody Student student){
        studentService.create(student);
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> putStudent(@PathVariable Integer id,
                                              @RequestBody Student student){

        if( null == studentService.findById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        student.setId(id);
        studentService.update(student);
        return new ResponseEntity<>(student,HttpStatus.OK);

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Integer id){
        if( null == studentService.findById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
