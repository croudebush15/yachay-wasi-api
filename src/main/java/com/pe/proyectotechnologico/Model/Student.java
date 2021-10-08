package com.pe.proyectotechnologico.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Student", uniqueConstraints = @UniqueConstraint(columnNames = "DNI"))
public class Student implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idStudent", unique = true, nullable = false)
    private Integer id;
    private String name;
    private String lastName;
    private String DNI;
    private Date birthDate;
    private String cellphone;
    private String email;
    private String address;
    private Boolean status;


}
