package com.pe.proyectotechnologico.Service;

import com.pe.proyectotechnologico.Model.Classroom_Student;
import com.pe.proyectotechnologico.Repository.ClassroomStudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassroomStudentService implements CrudService<Classroom_Student,Integer>{


    private final ClassroomStudentRepository classroomStudentRepository;
    private final StudentService studentService;
    private final ClassroomService classroomService;

    public ClassroomStudentService(ClassroomStudentRepository classroomStudentRepository,
                                   StudentService studentService,
                                   ClassroomService classroomService) {
        this.classroomStudentRepository = classroomStudentRepository;
        this.studentService = studentService;
        this.classroomService = classroomService;
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

    public void registerStudentsInClassroom(List<Integer> idNewStudents, Integer idClassroom){

        List<Integer> studentsInClassroom = classroomStudentRepository.findStudentsIdByClassroom(idClassroom);
        List<Integer> studentsToDeactivate = new ArrayList<>();
        List<Integer> studentsToAdd = new ArrayList<>();

        for (Integer oldStudent: studentsInClassroom){
            if (!idNewStudents.contains(oldStudent))
                studentsToDeactivate.add(oldStudent);
        }

        for (Integer newStudent: idNewStudents){
            if (!studentsInClassroom.contains(newStudent))
                studentsToAdd.add(newStudent);
        }

        if (!studentsToDeactivate.isEmpty())
            classroomStudentRepository.deactivateStudentsInClassroom(studentsToDeactivate,idClassroom);

        if (!studentsToAdd.isEmpty()){
            for (Integer student: studentsToAdd){
                classroomStudentRepository.saveAndActivateStudentsInClassroom(student,idClassroom);
            }
        }
    }
}
