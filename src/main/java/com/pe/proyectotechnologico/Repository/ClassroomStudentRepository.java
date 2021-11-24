package com.pe.proyectotechnologico.Repository;

import com.pe.proyectotechnologico.Model.Classroom_Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClassroomStudentRepository extends JpaRepository<Classroom_Student, Integer> {
    @Query("SELECT cs.student.id\n" +
            "FROM Classroom_Student cs\n" +
            "WHERE cs.classroom_2.id = ?1\n" +
            "AND cs.status = true")
    List<Integer> findStudentsIdByClassroom(Integer idClassroom);

    @Modifying
    @Transactional
    @Query("UPDATE Classroom_Student cs\n" +
            "SET cs.status = false\n" +
            "WHERE cs.student.id IN (?1)\n" +
            "AND cs.classroom_2.id = ?2")
    void deactivateStudentsInClassroom(List<Integer> idStudents, Integer idClassroom);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO classroom_student (id_student, id_classroom)\n" +
            "VALUES (?1, ?2)\n" +
            "ON CONFLICT (id_student,id_classroom) DO UPDATE\n" +
            "SET status = true", nativeQuery = true)
    void saveAndActivateStudentsInClassroom(Integer idStudent, Integer idClassroom);
}
