package com.neoris.turnosrotativos.controller;

import com.neoris.turnosrotativos.entity.Concepto;
import com.neoris.turnosrotativos.entity.Empleado;
import com.neoris.turnosrotativos.service.ConceptoService;
import com.neoris.turnosrotativos.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("concepto")
public class ConceptoController {

    @Autowired
    private ConceptoService conceptoService;

    @GetMapping
    public ResponseEntity<ArrayList<Concepto>> getConceptos ()
    {
        return new ResponseEntity<>(conceptoService.getConceptos(), HttpStatus.OK);
    }
}
