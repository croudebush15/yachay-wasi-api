package com.pe.proyectotechnologico.Service;


import com.pe.proyectotechnologico.Model.Classroom_Student;
import com.pe.proyectotechnologico.Repository.ClassroomStudentRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ClassroomStudentService implements CrudService<Classroom_Student,Integer>{


    private final ClassroomStudentRepository classroomStudentRepository;

    public ClassroomStudentService(ClassroomStudentRepository classroomStudentRepository) {
        this.classroomStudentRepository = classroomStudentRepository;
    }

    @Override
    public void create(Classroom_Student classroom_student) {
        classroomStudentRepository.save(classroom_student);
    }

    @Override
    public void update(Classroom_Student classroom_student) {
        classroomStudentRepository.save(classroom_student);
    }

    @Override
    public void delete(Integer integer) {
       classroomStudentRepository.deleteById(integer);
    }

    @Override
    public Classroom_Student findById(Integer integer) {
        return classroomStudentRepository.findById(integer).orElse(null);
    }

    @Override
    public List<Classroom_Student> findAll() {
        return classroomStudentRepository.findAll();
    }
}
