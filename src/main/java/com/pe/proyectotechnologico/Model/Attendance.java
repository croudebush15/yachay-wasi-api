package com.pe.proyectotechnologico.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idAttendance", unique = true, nullable = false)
    private Integer id;
    private Boolean presentInClass;
    private String report;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idStudent", referencedColumnName = "idStudent")
    private Student student;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idLesson")
    private Lesson lesson;
}
