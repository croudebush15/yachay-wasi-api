package com.pe.proyectotechnologico.Service;

import com.pe.proyectotechnologico.Model.Classroom;
import com.pe.proyectotechnologico.Model.Teacher;
import com.pe.proyectotechnologico.Repository.ClassroomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService implements CrudService<Classroom, Integer> {

    private final ClassroomRepository classroomRepository;

    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    public void create(Classroom classroom) {
        classroomRepository.save(classroom);
    }

    @Override
    public void update(Classroom classroom) {
        classroomRepository.save(classroom);
    }

    @Override
    public void delete(Integer id) {
        Classroom classroom = classroomRepository.findById(id).orElse(null);
        if (classroom != null){
            classroom.setStatus(false);
        }
        classroomRepository.save(classroom);
    }

    public void restoreClassroom(Classroom classroom){
        if (classroomRepository.findById(classroom.getId()).isPresent()) classroom.setStatus(true);
        classroomRepository.save(classroom);
    }

    @Override
    public Classroom findById(Integer integer) {
        return classroomRepository.findById(integer).orElse(null);
    }

    @Override
    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    public List<Classroom> findAllByStatus(Boolean status){
        return classroomRepository.findAllByStatusOrderByIdAsc(status);
    }

    public List<Classroom> findAllByTeacher(Teacher teacher){
        return classroomRepository.findByTeacherOrderByDayOfWeekAscNameAsc(teacher);
    }
}
