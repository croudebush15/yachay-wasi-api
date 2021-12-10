package com.pe.proyectotechnologico.Controller;

import com.pe.proyectotechnologico.Model.Attendance;
import com.pe.proyectotechnologico.Model.Lesson;
import com.pe.proyectotechnologico.Model.User;
import com.pe.proyectotechnologico.Service.AttendanceService;
import com.pe.proyectotechnologico.Service.LessonService;
import com.pe.proyectotechnologico.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {

    private final LessonService lessonService;
    private final AttendanceService attendanceService;
    private final UserService userService;

    public LessonController(LessonService lessonService,
                            AttendanceService attendanceService,
                            UserService userService) {
        this.lessonService = lessonService;
        this.attendanceService = attendanceService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Lesson>> getLessons(){
        return new ResponseEntity<>(lessonService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{idClassroom}")
    public ResponseEntity<List<Lesson>> getLessonsByClassroom(HttpServletRequest request,
                                                              @PathVariable Integer idClassroom){
        User user = userService.getUserFromRequest(request);
        if(user == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(lessonService.findAllByClassroom(idClassroom), HttpStatus.OK);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<Lesson> getLesson(@PathVariable Integer id){
        Lesson currentLesson = lessonService.findById(id);
        if( null == currentLesson){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentLesson, HttpStatus.OK);
    }*/
    @PostMapping("/create")
    public ResponseEntity<Lesson> postLesson(@RequestBody Lesson lesson){
        lessonService.create(lesson);
        return new ResponseEntity<>(lesson,HttpStatus.CREATED);
    }

    @PostMapping("/attendance")
    public ResponseEntity<Lesson> markAttendanceLesson(HttpServletRequest request,
                                                       @RequestBody Lesson lesson){
        User user = userService.getUserFromRequest(request);
        if(user == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        attendanceService.markAttendanceLesson(lesson.getAttendances());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Lesson> putLesson(@PathVariable Integer id,
                                            @RequestBody Lesson lesson){

        if( null == lessonService.findById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        lesson.setId(id);
        lessonService.update(lesson);
        return new ResponseEntity<>(lesson,HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Lesson> deleteLesson(@PathVariable Integer id){
        if( null == lessonService.findById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        lessonService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
