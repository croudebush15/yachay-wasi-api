package com.pe.proyectotechnologico.Model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
    private String id;
    private String route;
    private String description;
    private Date uploadDate;

    //Relacion con Idcurso

}
