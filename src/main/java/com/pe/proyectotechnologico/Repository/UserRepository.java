package com.pe.proyectotechnologico.Repository;

import com.pe.proyectotechnologico.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    List<User> findAllByTeacher_Status(Boolean status);
    List<User> findAllByTeacher_RoleAndTeacherStatusOrderByIdAsc(String role, Boolean status);

}
