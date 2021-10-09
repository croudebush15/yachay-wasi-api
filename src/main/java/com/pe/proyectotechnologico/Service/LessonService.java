package com.pe.proyectotechnologico.Service;

import com.pe.proyectotechnologico.Model.Lesson;
import com.pe.proyectotechnologico.Repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService implements CrudService<Lesson,Integer> {

    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void create(Lesson Lesson) {
        lessonRepository.save(Lesson);
    }

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
}
