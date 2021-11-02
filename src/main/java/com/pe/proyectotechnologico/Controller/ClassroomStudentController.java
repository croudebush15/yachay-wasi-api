package com.pe.proyectotechnologico.Controller;

import com.pe.proyectotechnologico.Model.Classroom_Student;
import com.pe.proyectotechnologico.Model.Student;
import com.pe.proyectotechnologico.Service.ClassroomStudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/classroom_student")
public class ClassroomStudentController {
    private final ClassroomStudentService classroomStudentService;

    public ClassroomStudentController(ClassroomStudentService classroomStudentService) {
        this.classroomStudentService = classroomStudentService;
    }

    @GetMapping
    public ResponseEntity<List<Classroom_Student>> getAll(){
        return new ResponseEntity<>(classroomStudentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classroom_Student> getClassroomStudent(@PathVariable Integer id){
        Classroom_Student classroom_student = classroomStudentService.findById(id);
        if( null == classroom_student){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(classroom_student, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Classroom_Student> postClassroomStudent(@RequestBody Classroom_Student classroom_student){
        classroomStudentService.create(classroom_student);
        return new ResponseEntity<>(classroom_student,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Classroom_Student> putClassroomStudent(@PathVariable Integer integer,
                                                                 @RequestBody Classroom_Student classroom_student){
        if( null == classroomStudentService.findById(integer)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        classroom_student.setId(integer);
        classroomStudentService.update(classroom_student);
        return new ResponseEntity<>(classroom_student,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Classroom_Student> deleteClassroomStudent(@PathVariable Integer integer){
        if(null == classroomStudentService.findById(integer)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        classroomStudentService.delete(integer);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
