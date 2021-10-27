package com.pe.proyectotechnologico.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;


import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "classroom")
public class Classroom {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idClassroom", unique = true, nullable = false)
    private Integer id;
    private String name;
    @Column(name = "NRC")
    @Generated(GenerationTime.INSERT)
    private Integer NRC;
    private Integer numberOfStudents;
    private String dayOfWeek;
    private String HoraI;
    private String HoraF;

    @JsonIgnore
    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<Lesson> lessonList;

    @ManyToOne
    @JoinColumn(name = "id_teacher")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "id_course")
    private Course course;


}
