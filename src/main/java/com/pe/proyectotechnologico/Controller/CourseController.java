package com.pe.proyectotechnologico.Controller;


import com.pe.proyectotechnologico.Model.Course;
import com.pe.proyectotechnologico.Service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;


    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getCourses(){
        return new ResponseEntity<>(courseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Integer id){
        Course currentCourse = courseService.findById(id);
        if( null == currentCourse){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentCourse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Course> postCourse(@RequestBody Course course){
        courseService.create(course);
        return new ResponseEntity<>(course,HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Course> putCourse(@PathVariable Integer id,
                                              @RequestBody Course course){

        if( null == courseService.findById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        course.setId(id);
        courseService.update(course);
        return new ResponseEntity<>(course,HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable Integer id){
        if( null == courseService.findById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
