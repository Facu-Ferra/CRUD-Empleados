package com.neoris.turnosrotativos.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@Entity
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nroDocumento" , nullable = true, unique = true)
    private int nroDocumento;
    @Column(name = "nombre" , nullable = false)
    private String nombre;
    @Column(name = "apellido", nullable = false)
    private String apellido;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "fechaNacimiento",nullable = false)
    private Date fechaNacimiento;
    @Column(name = "fechaIngreso",nullable = false)
    private Date fechaIngreso;
    @Column(name = "fechaCreacion",nullable = false)
    private Date fechaCreacion;
}
