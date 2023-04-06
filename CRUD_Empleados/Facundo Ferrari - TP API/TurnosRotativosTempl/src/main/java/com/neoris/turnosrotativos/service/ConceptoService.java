package com.neoris.turnosrotativos.service;

import com.neoris.turnosrotativos.entity.Concepto;
import com.neoris.turnosrotativos.entity.Empleado;
import com.neoris.turnosrotativos.repository.ConceptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class ConceptoService implements  IConceptoService{

    @Autowired
    ConceptoRepository conceptoRepository;

    @Override
    public ArrayList<Concepto> getConceptos()
    {
        return  conceptoRepository.findAll()
                .stream()
                .filter(x -> (x.getHsMaximo() != null || x.getHsMinimo() != null) || !x.getLaborable())
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
