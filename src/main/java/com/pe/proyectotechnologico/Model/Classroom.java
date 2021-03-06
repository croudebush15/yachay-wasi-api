package com.pe.proyectotechnologico.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "classroom")
public class Classroom {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "nrc_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "100"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "idClassroom", unique = true, nullable = false)
    private Integer id;
    private String name;
    private Integer numberOfStudents;
    private String dayOfWeek;
    private Long durationHours;
    private String HoraI;
    @JsonIgnore
    private Boolean status = true;

    @JsonIgnore
    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<Lesson> lessonList;

    @ManyToOne
    @JoinColumn(name = "id_teacher")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "id_course")
    private Course course;

    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfStudents=" + numberOfStudents +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", durationHours=" + durationHours +
                ", HoraI='" + HoraI + '\'' +
                ", status=" + status +
                ", teacher=" + teacher.toString() +
                ", course=" + course.toString() +
                '}';
    }

    @JsonIgnore
    @OneToMany(mappedBy = "classroom_2")
    private List<Classroom_Student> classroom_students;



}
