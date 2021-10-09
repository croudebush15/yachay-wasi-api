package com.pe.proyectotechnologico.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
    private Date birthDate;
    private String cellphone;
    private String email;
    private String address;
    private Boolean status;


}
