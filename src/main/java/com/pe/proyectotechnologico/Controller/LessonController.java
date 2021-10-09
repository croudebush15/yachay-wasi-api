package com.pe.proyectotechnologico.Controller;


import com.pe.proyectotechnologico.Model.Lesson;
import com.pe.proyectotechnologico.Service.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    public ResponseEntity<List<Lesson>> getLessons(){
        return new ResponseEntity<>(lessonService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLesson(@PathVariable Integer id){
        Lesson currentLesson = lessonService.findById(id);
        if( null == currentLesson){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentLesson, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Lesson> postLesson(@RequestBody Lesson lesson){
        lessonService.create(lesson);
        return new ResponseEntity<>(lesson,HttpStatus.CREATED);
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
