import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup } from '@angular/forms';
import { EmpleadosService } from '../../services/empleados.service';
import { Empleado } from '../../models/empleado';
import { DeleteComponent } from '../delete/delete.component';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-get',
  templateUrl: './get.component.html',
  styleUrls: ['./get.component.css']
})
export class GetComponent implements OnInit {
  agregarNuevo = false;
  elementoBorrar = false;
  title = "Empleados";
  listEmpleados: Array<Empleado> = [];
  localhost = environment.localhost;
  idToBorrar : number;

  constructor(private empleadoService: EmpleadosService, private router: Router) { }

  ngOnInit(): void {
    this.obtenerEmpleados(); //cargamos la lista de empleados para mostrar en la tabla
  }

  onClickAgregar(): void {
    this.agregarNuevo = !this.agregarNuevo;

    if (this.title === "Empleados") {
      this.title = "Agregar nuevo empleado";
    } else {
      this.title = "Empleados";
    }
  }

  obtenerEmpleados() { 
    this.empleadoService.obtenerEmpleados().subscribe(res => { //llamamos a metodo que hace el get a la API

      this.listEmpleados = res;
      console.log(this.listEmpleados);

    })
  }

  borrarYrecargar(id: number) {
    this.idToBorrar = id;
    this.elementoBorrar = true;
  }

  recibAlert(data: boolean) {
    if(data)
    {
      
      this.obtenerEmpleados();

      Promise.resolve().then(() =>this.elementoBorrar = false);  //la promesa es para que no me tire el error en consola
                                                                // de que se cambio el error de la variable sin chekear
     

    }
  }
  


}
