package com.pe.proyectotechnologico.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idLesson", unique = true, nullable = false)
    private Integer id;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    //private Boolean isCurrentLesson;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_classroom")
    private Classroom classroom;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.MERGE)
    private List<Attendance> attendances;

    public Lesson(LocalDate date, Classroom classroom) {
        this.date = date;
        this.classroom = classroom;
    }
}
