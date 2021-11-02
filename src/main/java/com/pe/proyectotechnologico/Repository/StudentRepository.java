package com.pe.proyectotechnologico.Repository;

import com.pe.proyectotechnologico.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findAllByStatusOrderByIdAsc(Boolean status);
    @Query("SELECT *\n" +
            "FROM student s\n" +
            "INNER JOIN classroom_student cs ON s.id_student = cs.id_student\n" +
            "WHERE id_classroom = ?1\n" +
            "AND status = true")
    List<Student> findStudentsByClassroom(Integer idClassroom);
}
