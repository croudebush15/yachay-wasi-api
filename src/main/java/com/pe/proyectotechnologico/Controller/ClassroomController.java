package com.pe.proyectotechnologico.Controller;

import com.pe.proyectotechnologico.Model.Classroom;
import com.pe.proyectotechnologico.Model.User;
import com.pe.proyectotechnologico.Service.ClassroomService;
import com.pe.proyectotechnologico.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {

    final UserService userService;
    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService, UserService userService) {
        this.classroomService = classroomService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Classroom>> getClassroomsForTeacher(HttpServletRequest request){
        User user = userService.getUserFromRequest(request);
        if(user == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);

        List<Classroom> classrooms = classroomService.findAllByTeacher(user.getTeacher());
        if (classrooms == null) return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity(classrooms, HttpStatus.OK);
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
