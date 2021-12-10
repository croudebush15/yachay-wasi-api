package com.pe.proyectotechnologico.Service;

import com.pe.proyectotechnologico.Model.Classroom;
import com.pe.proyectotechnologico.Model.Lesson;
import com.pe.proyectotechnologico.Repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService implements CrudService<Lesson,Integer> {

    private final LessonRepository lessonRepository;
    private final ClassroomService classroomService;

    public LessonService(LessonRepository lessonRepository,
                         ClassroomService classroomService) {
        this.lessonRepository = lessonRepository;
        this.classroomService = classroomService;
    }

    @Override
    public void create(Lesson Lesson) {
        lessonRepository.save(Lesson);
    }

    public void createAll(List<Lesson> lessons){ lessonRepository.saveAll(lessons); }

    @Override
    public void update(Lesson Lesson) {
        lessonRepository.save(Lesson);
    }

    @Override
    public void delete(Integer integer) {
        lessonRepository.deleteById(integer);
    }

    @Override
    public Lesson findById(Integer integer) {
        return lessonRepository.findById(integer).orElse(null);
    }

    @Override
    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    public List<Lesson> findAllByClassroom(Integer idClassroom) {
        Classroom classroom = classroomService.findById(idClassroom);
        return lessonRepository.getAllByClassroomOrderByDateAsc(classroom);
    }

    public void generateWeeklyLessonsForClassroom(Classroom classroom, Integer quantityLessons, String startDate){
        List<Lesson> lessonList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(startDate,formatter);
        for (int i = 0; i < quantityLessons; i++){
            LocalDate lessonDate = date.plusWeeks(i);
            Lesson lesson  = new Lesson(lessonDate, classroom);
            lessonList.add(lesson);
        }
        createAll(lessonList);
    }
}
