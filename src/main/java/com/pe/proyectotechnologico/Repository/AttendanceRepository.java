package com.pe.proyectotechnologico.Repository;


import com.pe.proyectotechnologico.Model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
}
