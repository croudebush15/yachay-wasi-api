package com.pe.proyectotechnologico.Repository;

import com.pe.proyectotechnologico.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findAllByStatusOrderByIdAsc(Boolean status);
}
