package com.neoris.turnosrotativos.service;

import com.neoris.turnosrotativos.entity.Empleado;
import com.neoris.turnosrotativos.exceptions.EmpleadoNoEncontradoException;
import com.neoris.turnosrotativos.exceptions.UsuarioExistenteException;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public interface IEmpleadoService {

    public ArrayList<Empleado> getEmpleados();

    public ResponseEntity<Object> addEmpleado(Empleado newEmpleado) throws UsuarioExistenteException;


    public Optional<Empleado> getEmpleadoById (int id);

    public void calcularEdad(Date fecha);

    public ResponseEntity<Object> deleteEmpleado (int id) throws EmpleadoNoEncontradoException;

    public Empleado updateEmpleado (Empleado newEmpleado) throws UsuarioExistenteException;
}
