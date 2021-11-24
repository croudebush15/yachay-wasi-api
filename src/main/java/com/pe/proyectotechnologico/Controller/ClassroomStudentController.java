package com.pe.proyectotechnologico.Controller;

import com.pe.proyectotechnologico.Service.ClassroomStudentService;
import com.pe.proyectotechnologico.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/register")
public class ClassroomStudentController {
    private final ClassroomStudentService classroomStudentService;
    private final UserService userService;

    public ClassroomStudentController(ClassroomStudentService classroomStudentService,
                                      UserService userService) {
        this.classroomStudentService = classroomStudentService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity registerStudentsInClassroom(HttpServletRequest request,
                                                      @RequestBody StudentRegister studentRegister){
        if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        classroomStudentService.registerStudentsInClassroom(studentRegister.getIdStudents(),
                studentRegister.getIdClassroom());
        return new ResponseEntity(HttpStatus.OK);
    }

    public static class StudentRegister{
        private List<Integer> idStudents;
        private Integer idClassroom;

        public List<Integer> getIdStudents() {
            return idStudents;
        }

        public Integer getIdClassroom() {
            return idClassroom;
        }
    }

}
