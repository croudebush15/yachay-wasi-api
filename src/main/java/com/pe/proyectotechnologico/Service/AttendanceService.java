package com.pe.proyectotechnologico.Service;

import com.pe.proyectotechnologico.Model.Attendance;
import com.pe.proyectotechnologico.Model.Lesson;
import com.pe.proyectotechnologico.Repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService implements CrudService<Attendance, Integer> {

    private final AttendanceRepository repository;

    public AttendanceService(AttendanceRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Attendance attendance) {

    }

    @Override
    public void update(Attendance attendance) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public Attendance findById(Integer integer) {
        return null;
    }

    @Override
    public List<Attendance> findAll() {
        return null;
    }

    public void markAttendanceLesson(Lesson lesson) {
        lesson.getAttendances().forEach(attendance -> {
            attendance.setLesson(lesson);
        });
        repository.saveAll(lesson.getAttendances());
    }
}
