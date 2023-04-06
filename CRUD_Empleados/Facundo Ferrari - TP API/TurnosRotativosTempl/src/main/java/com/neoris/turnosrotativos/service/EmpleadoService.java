package com.neoris.turnosrotativos.service;

import com.neoris.turnosrotativos.entity.Empleado;
import com.neoris.turnosrotativos.exceptions.EdadInvalidaException;
import com.neoris.turnosrotativos.exceptions.EmpleadoNoEncontradoException;
import com.neoris.turnosrotativos.exceptions.UsuarioExistenteException;
import com.neoris.turnosrotativos.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class EmpleadoService implements IEmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public ArrayList<Empleado> getEmpleados()
    {
        return(ArrayList<Empleado>) empleadoRepository.findAll();
    }

    @Override
    public ResponseEntity<Object> addEmpleado(Empleado newEmpleado){

        if (empleadoRepository.findByEmail(newEmpleado.getEmail()) != null)
        {
            //throw new UsuarioExistenteException("Ya existe un empleado con el email ingresado.");
            return new ResponseEntity<>("Ya existe un empleado con el email ingresado.", HttpStatus.CONFLICT);

        } else if (empleadoRepository.findByNroDocumento(newEmpleado.getNroDocumento()) != null) {
            //throw new UsuarioExistenteException("Ya existe un empleado con el DNI ingresado.");
            return new ResponseEntity<>("Ya existe un empleado con el documento ingresado.", HttpStatus.CONFLICT);

        }else {

            calcularEdad(newEmpleado.getFechaNacimiento()); //Arroja una excepcion si la edad es menor a 18

            return new ResponseEntity<>(empleadoRepository.save(newEmpleado), HttpStatus.CREATED);
        }
    }

    @Override
    public Optional<Empleado> getEmpleadoById (int id)
    {
        return empleadoRepository.findById(id);
    }

    @Override
    public void calcularEdad(Date fecha) throws EdadInvalidaException
    {
        Date hoy = new Date();
        int edad = hoy.getYear() - fecha.getYear();

       if(edad < 18)
       {
           throw new EdadInvalidaException("La edad del empleado no puede ser menor a 18 años, edad: " + edad);
       }
    }

    @Override
    public ResponseEntity<Object> deleteEmpleado (int id)  {
        Optional<Empleado> empleado = getEmpleadoById(id);

        if (empleado.isPresent()) {
            empleadoRepository.deleteById(id);
            return new ResponseEntity<>( null , HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>( "No se encontró el empleado con Id: " + id , HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Empleado updateEmpleado (Empleado newEmpleado) throws UsuarioExistenteException {

        calcularEdad(newEmpleado.getFechaNacimiento()); //Arroja una excepcion si la edad es menor a 18

        if (empleadoRepository.findByEmail(newEmpleado.getEmail()) != null)  // si encontro ALGO con el mismo email:
        {
            if(!empleadoRepository.findByEmail(newEmpleado.getEmail()).getId().equals(newEmpleado.getId()))  // si ese Algo NO es el mismo objeto:
                throw new UsuarioExistenteException("Ya existe un empleado con el email ingresado.");

        }

        if (empleadoRepository.findByNroDocumento(newEmpleado.getNroDocumento()) != null) {// si encontro ALGO con el mismo documento:

            if(empleadoRepository.findByNroDocumento(newEmpleado.getNroDocumento()).getId().equals(newEmpleado.getId())) // si ese Algo es el mismo objeto:
                return empleadoRepository.save(newEmpleado);
            else {
                throw new UsuarioExistenteException("Ya existe un empleado con el documento ingresado.");
            }

        }else {

            return empleadoRepository.save(newEmpleado);
        }
    }



}
