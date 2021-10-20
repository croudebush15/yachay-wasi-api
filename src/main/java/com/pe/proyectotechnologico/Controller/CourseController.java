package com.pe.proyectotechnologico.Controller;

import com.pe.proyectotechnologico.Model.Course;
import com.pe.proyectotechnologico.Service.CourseService;
import com.pe.proyectotechnologico.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    final UserService userService;
    private final CourseService courseService;


    public CourseController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getCourses(HttpServletRequest request){
        if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(courseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(HttpServletRequest request,
                                            @PathVariable Integer id){
        if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        Course currentCourse = courseService.findById(id);
        if( null == currentCourse){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentCourse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> postCourse(HttpServletRequest request,
                                             @RequestBody Course course){
        if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        courseService.create(course);
        return new ResponseEntity<>(course,HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Course> putCourse(HttpServletRequest request,
                                              @RequestBody Course course){
        if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        if( null == courseService.findById(course.getId())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        course.setId(course.getId());
        courseService.update(course);
        return new ResponseEntity<>(course,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteCourse(HttpServletRequest request,
                                               @PathVariable Integer id){
        if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        if( null == courseService.findById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
