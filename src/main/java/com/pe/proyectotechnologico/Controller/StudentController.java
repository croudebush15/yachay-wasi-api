package com.pe.proyectotechnologico.Controller;

import com.pe.proyectotechnologico.Model.Classroom;
import com.pe.proyectotechnologico.Model.Student;
import com.pe.proyectotechnologico.Model.User;
import com.pe.proyectotechnologico.Service.StudentService;
import com.pe.proyectotechnologico.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {


    private final UserService userService;
    private final StudentService studentService;


    public StudentController(StudentService studentService, UserService userService) {
        this.studentService = studentService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(HttpServletRequest request){
        if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<Student> getActiveStudents(HttpServletRequest request){
        if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        List<Student> students = studentService.findAllByStatus(true);
        if (students == null) return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity(students, HttpStatus.OK);
    }

    @GetMapping("/inactive")
    public ResponseEntity getAllInactiveStudents(HttpServletRequest request){
        if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        List<Student> students = studentService.findAllByStatus(false);
        if (students == null) return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity(students, HttpStatus.OK);
    }

    @PostMapping("/classroom")
    public ResponseEntity<List<Student>> getStudentsForClassroom(HttpServletRequest request,
                                                                 @RequestBody Classroom classroom){
        User user = userService.getUserFromRequest(request);
        if (!userService.isUserAdmin(user) ){
            if(user == null || !classroom.getTeacher().getIdTeacher().equals(user.getTeacher().getIdTeacher()))
                return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        List<Student> students = studentService.findAllByClassroom(classroom.getId());
        if (students == null) return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(HttpServletRequest request,
                                              @PathVariable Integer id){
        if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        Student currentStudent = studentService.findById(id);
        if( null == currentStudent){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentStudent, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> postStudent(HttpServletRequest request,
                                               @RequestBody Student student){
        if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        studentService.create(student);
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Student> putStudent(HttpServletRequest request,
                                            @RequestBody Student student){
        if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        if(null == studentService.findById(student.getId())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentService.update(student);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(HttpServletRequest request,
                                                 @PathVariable Integer id){
        if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        if( null == studentService.findById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/restore")
    public ResponseEntity restoreStudent(HttpServletRequest request,
                                        @RequestBody Student student) {
        if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        if(studentService.findById(student.getId()) == null) return new ResponseEntity(HttpStatus.NO_CONTENT);
        studentService.restoreStudent(student);
        return new ResponseEntity(HttpStatus.OK);
    }

}
