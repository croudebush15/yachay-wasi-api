package com.pe.proyectotechnologico.Controller;

import com.pe.proyectotechnologico.Model.Classroom;
import com.pe.proyectotechnologico.Model.Course;
import com.pe.proyectotechnologico.Service.ClassroomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    public ResponseEntity<List<Classroom>> getClassrooms(){
        return new ResponseEntity<>(classroomService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classroom> getClassroom(@PathVariable Integer id){
        Classroom currentClassroom = classroomService.findById(id);
        if( null == currentClassroom){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentClassroom, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Classroom> postClassroom(@RequestBody Classroom classroom){
        classroomService.create(classroom);
        return new ResponseEntity<>(classroom,HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Classroom> putClassroom(@PathVariable Integer id,
                                            @RequestBody Classroom classroom){

        if( null == classroomService.findById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        classroom.setId(id);
        classroomService.update(classroom);
        return new ResponseEntity<>(classroom,HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Classroom> deleteClassroom(@PathVariable Integer id){
        if( null == classroomService.findById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        classroomService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
