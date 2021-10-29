package com.pe.proyectotechnologico.Repository;

import com.pe.proyectotechnologico.Model.Classroom_Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomStudentRepository extends JpaRepository<Classroom_Student, Integer> {
}
