package com.pe.proyectotechnologico.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;



@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "teacher", uniqueConstraints = @UniqueConstraint(columnNames = "document_number"))
public class Teacher implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idTeacher", unique = true, nullable = false)
    private Integer idTeacher;
    private String firstName;
    private String lastName;
    private String document_number;
    private Date birthDate;
    private String phone;
    private String email;
    private String address;
    private Boolean status;
    @Column(name = "role")
    private String role;

    @JsonIgnore
    @OneToOne(mappedBy = "teacher", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;

    @Override
    public String toString() {
        return "Teacher{" +
                "idTeacher=" + idTeacher +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", document_number='" + document_number + '\'' +
                ", birthDate=" + birthDate +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", role='" + role + '\'' +
                '}';
    }
}
