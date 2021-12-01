package com.pe.proyectotechnologico.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

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
    private Boolean attendedClass;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idStudent", referencedColumnName = "idStudent")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "idLesson")
    private Lesson lesson;
}
