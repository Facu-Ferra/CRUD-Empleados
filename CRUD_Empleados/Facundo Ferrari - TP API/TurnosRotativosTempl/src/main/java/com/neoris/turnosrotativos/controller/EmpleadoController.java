package com.neoris.turnosrotativos.controller;

import com.neoris.turnosrotativos.DTO.EmpleadoRequest;
import com.neoris.turnosrotativos.entity.Empleado;
import com.neoris.turnosrotativos.exceptions.EmpleadoNoEncontradoException;
import com.neoris.turnosrotativos.exceptions.UsuarioExistenteException;
import com.neoris.turnosrotativos.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<ArrayList<Empleado>> getEmpleado ()
    {
        return new ResponseEntity<>(empleadoService.getEmpleados(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getEmpleado (@PathVariable("id") Integer id) {
        Optional<Empleado> empleado = empleadoService.getEmpleadoById(id);
        if(empleado.isPresent())
        {
            return new ResponseEntity<>(empleado.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró el empleado con Id: " + id,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> addEmpleado(@Valid @RequestBody EmpleadoRequest empleadoRequest) {
        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setNombre(empleadoRequest.getNombre());
        nuevoEmpleado.setApellido(empleadoRequest.getApellido());
        nuevoEmpleado.setEmail(empleadoRequest.getEmail());
        nuevoEmpleado.setFechaNacimiento(empleadoRequest.getFechaNacimiento());
        nuevoEmpleado.setFechaIngreso(empleadoRequest.getFechaIngreso());
        nuevoEmpleado.setNroDocumento(empleadoRequest.getNroDocumento());
        nuevoEmpleado.setFechaCreacion(new Date());


        return empleadoService.addEmpleado(nuevoEmpleado);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteEmpleado (@PathVariable("id") Integer id) {

        return empleadoService.deleteEmpleado(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateEmpleado (@PathVariable("id") Integer id, @Valid @RequestBody EmpleadoRequest empleadoRequest) throws EmpleadoNoEncontradoException, UsuarioExistenteException {
        Optional<Empleado> viejoEmpleado = empleadoService.getEmpleadoById(id);
        if(viejoEmpleado.isPresent()) {
            Empleado nuevoEmpleado = viejoEmpleado.get();
            nuevoEmpleado.setNombre(empleadoRequest.getNombre());
            nuevoEmpleado.setApellido(empleadoRequest.getApellido());
            nuevoEmpleado.setEmail(empleadoRequest.getEmail());
            nuevoEmpleado.setFechaNacimiento(empleadoRequest.getFechaNacimiento());
            nuevoEmpleado.setFechaIngreso(empleadoRequest.getFechaIngreso());
            nuevoEmpleado.setNroDocumento(empleadoRequest.getNroDocumento());
            nuevoEmpleado.setFechaCreacion(new Date());

            return new ResponseEntity<>(empleadoService.updateEmpleado(nuevoEmpleado),HttpStatus.OK);
        } else {
            throw new EmpleadoNoEncontradoException("No se encontró el empleado con Id: " + id);
        }


    }

}
