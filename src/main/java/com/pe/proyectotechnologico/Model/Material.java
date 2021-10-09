package com.pe.proyectotechnologico.Model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "material")
public class Material {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idMaterial", unique = true, nullable = false)
    private Integer id;
    private String route;
    private String description;
    private LocalDate uploadDate;

    @ManyToOne
    @JoinColumn(name = "id_course")
    private Course course;

    @JsonIgnore
    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL)
    private List<Lesson> lessonList;

}
