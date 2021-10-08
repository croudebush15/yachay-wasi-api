package com.pe.proyectotechnologico.Service;

import com.pe.proyectotechnologico.Model.Course;
import com.pe.proyectotechnologico.Repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements CrudService<Course,Integer> {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @Override
    public void create(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void update(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void delete(Integer id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course findById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }
}
