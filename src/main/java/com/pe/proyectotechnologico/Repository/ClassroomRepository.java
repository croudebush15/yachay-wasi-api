package com.pe.proyectotechnologico.Repository;

import com.pe.proyectotechnologico.Model.Classroom;
import com.pe.proyectotechnologico.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {
    List<Classroom> findByTeacherOrderByDayOfWeekAscNameAsc(Teacher teacher);
}
