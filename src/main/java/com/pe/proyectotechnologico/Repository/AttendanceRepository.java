package com.pe.proyectotechnologico.Repository;


import com.pe.proyectotechnologico.Model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
}
