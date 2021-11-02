package com.pe.proyectotechnologico.Repository;

import com.pe.proyectotechnologico.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findAllByStatusOrderByIdAsc(Boolean status);
    @Query("SELECT s\n" +
            "FROM Student s\n" +
            "INNER JOIN Classroom_Student cs ON s.id = cs.student.id\n" +
            "WHERE cs.classroom_2.id = ?1\n" +
            "AND s.status = true")
    List<Student> findStudentsByClassroom(Integer idClassroom);
}
