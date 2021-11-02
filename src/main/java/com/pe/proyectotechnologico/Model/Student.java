package com.pe.proyectotechnologico.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Student", uniqueConstraints = @UniqueConstraint(columnNames = "document_number"))
public class Student implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idStudent", unique = true, nullable = false)
    private Integer id;
    private String firstName;
    private String lastName;
    private String document_number;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;
    private String phone;
    private String email;
    private String address;
    private Boolean status = true;


    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Classroom_Student> classroom_students;


}
