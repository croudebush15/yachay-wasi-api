package com.pe.proyectotechnologico.Repository;

import com.pe.proyectotechnologico.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
    List<Course> findAllByStatusOrderByIdAsc(Boolean status);
}
