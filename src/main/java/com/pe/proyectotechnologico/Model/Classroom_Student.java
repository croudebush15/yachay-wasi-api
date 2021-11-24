package com.pe.proyectotechnologico.Model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "classroom_student")
public class Classroom_Student {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_Classroom_Student", unique = true, nullable = false)
    private Integer id;

    @JsonIgnore
    private Boolean status = true;

    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_classroom")
    private Classroom classroom_2;



}
