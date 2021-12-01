package com.pe.proyectotechnologico.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.util.List;

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
        private Integer id;
        private String name;
        private String description;
        @JsonIgnore
        private Boolean status = true;

        @JsonIgnore
        @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
        private List<Classroom> classroomList;

        @Override
        public String toString() {
                return "Course{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", description='" + description + '\'' +
                        ", status=" + status +
                        '}';
        }
}
