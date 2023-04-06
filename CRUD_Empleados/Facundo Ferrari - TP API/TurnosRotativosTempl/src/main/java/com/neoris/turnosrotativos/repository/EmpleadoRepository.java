package com.neoris.turnosrotativos.repository;

import com.neoris.turnosrotativos.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Integer> {

    Empleado findByNroDocumento(int nroDocumento); //Creamos las funciones especificas para verificar si ya existe ese Empleado

    Empleado findByEmail(String email);
}
