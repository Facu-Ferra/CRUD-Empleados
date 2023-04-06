package com.neoris.turnosrotativos.DTO;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@Component
public class EmpleadoRequest {


    @Positive(message = "El documento no puede ser negativo.") //El documento si puede ser nulo segun lo indicado en el spotlight
    @Max(value = 999999999, message = "El número maximo de digitos para el nro de documento es 9.") // Se establecen 9 digitos como maximo porque algunos dni extranjeros llegan o superan los 100 millones
    private int nroDocumento;  //de todas maneras ya si supera los 2 mil millones el sistema no lo permitira porque la variable es de tipo int, y devolveria un status code: 400

    @NotBlank(message="El nombre es obligatorio.")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Solo se permiten letras en el campo Nombre.")
    private String nombre;

    @NotBlank(message="El apellido es obligatorio.")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Solo se permiten letras en el campo Apellido.")
    private String apellido;

    @NotBlank(message="El email es obligatorio.")
    @Email(message = "El email ingresado no es correcto.", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @Past(message = "La fecha de nacimiento no puede ser posterior al día de la fecha.")
    @Column(name = "fechaNacimiento",nullable = false)
    @NotNull(message =  "La fecha de nacimiento es obligatoria.")
    private Date fechaNacimiento;
    @Column(name = "fechaIngreso",nullable = false)
    @Past(message = "La fecha de ingreso no puede ser posterior al día de la fecha.")
    @NotNull(message =  "La fecha de ingreso es obligatoria.")
    private Date fechaIngreso;
}
