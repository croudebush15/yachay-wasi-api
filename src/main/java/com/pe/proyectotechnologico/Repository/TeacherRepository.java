package com.pe.proyectotechnologico.Repository;

import com.pe.proyectotechnologico.Model.Teacher;
import com.pe.proyectotechnologico.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    List<Teacher> findAllByStatusAndRole(Boolean status, String role);
}
