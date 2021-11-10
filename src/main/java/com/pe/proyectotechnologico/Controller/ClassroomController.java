package com.pe.proyectotechnologico.Controller;

import com.pe.proyectotechnologico.Model.Classroom;
import com.pe.proyectotechnologico.Model.Teacher;
import com.pe.proyectotechnologico.Model.User;
import com.pe.proyectotechnologico.Service.ClassroomService;
import com.pe.proyectotechnologico.Service.TeacherService;
import com.pe.proyectotechnologico.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/classroom")
public class ClassroomController {

    private final UserService userService;
    private final ClassroomService classroomService;
    private final TeacherService teacherService;

    public ClassroomController(ClassroomService classroomService, UserService userService, TeacherService teacherService) {
        this.classroomService = classroomService;
        this.userService = userService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<Classroom>> getClassroomsForTeacher(HttpServletRequest request){
        User user = userService.getUserFromRequest(request);
        if(user == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);

        List<Classroom> classrooms = classroomService.findAllByTeacher(user.getTeacher());
        if (classrooms == null) return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity(classrooms, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<Classroom> getAllActiveClassrooms(HttpServletRequest request){
        if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        List<Classroom> classrooms = classroomService.findAllByStatus(true);
        if (classrooms == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(classrooms, HttpStatus.OK);
    }

    @GetMapping("/inactive")
    public ResponseEntity getAllInactiveClassrooms(HttpServletRequest request){
        if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        List<Classroom> classrooms = classroomService.findAllByStatus(false);
        if(classrooms == null) return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity(classrooms, HttpStatus.OK);
    }

    @GetMapping("/teacher")
    public ResponseEntity<Classroom> getAllTeachers(HttpServletRequest request){
        if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        List<Teacher> teachers = teacherService.findAll();
        if (teachers == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(teachers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classroom> getClassroom(@PathVariable Integer id){
        Classroom currentClassroom = classroomService.findById(id);
        if( null == currentClassroom){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentClassroom, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Classroom> postClassroom(HttpServletRequest request,
                                                   @RequestBody Classroom classroom){
        if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        if (classroomService.teacherHasClassAtTime(classroom)) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        classroomService.create(classroom);
        return new ResponseEntity(classroom,HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Classroom> putClassroom(HttpServletRequest request,
                                            @RequestBody Classroom classroom){
        if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        if( null == classroomService.findById(classroom.getId())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        classroomService.update(classroom);
        return new ResponseEntity<>(classroom,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Classroom> deleteClassroom(HttpServletRequest request,
                                                     @PathVariable Integer id){
        if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        if( null == classroomService.findById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        classroomService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/restore")
    public ResponseEntity restoreClassroom(HttpServletRequest request,
                                      @RequestBody Classroom classroom) {
        if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        if(classroomService.findById(classroom.getId()) == null) return new ResponseEntity(HttpStatus.NO_CONTENT);
        classroomService.restoreClassroom(classroom);
        return new ResponseEntity(HttpStatus.OK);
    }
}
