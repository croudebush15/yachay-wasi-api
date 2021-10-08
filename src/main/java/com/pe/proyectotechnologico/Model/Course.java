package com.pe.proyectotechnologico.Model;


import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {
        @Id
        @GeneratedValue(strategy = IDENTITY)
        @Column(name = "idCourse", unique = true, nullable = false)
        private String id;
        private String name;
        private String description;
}
