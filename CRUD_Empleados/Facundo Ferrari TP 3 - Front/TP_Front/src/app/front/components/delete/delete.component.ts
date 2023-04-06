import { Component, OnInit, ElementRef, ViewChild, Input, Output, EventEmitter } from '@angular/core';
import { EmpleadosService } from '../../services/empleados.service';
import { GetComponent } from '../get/get.component';




@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteComponent implements OnInit {

  @Input() idSentByParent: number;
  @Output() sendOutParent = new EventEmitter<boolean>();

  //La idea original era que el boton de eliminar lanze un modal con el texto "Seguro que desea borrar el siguiente usuario?"
  //Sin embargo tuve que abandonar la idea ya que despues de buscar muchisimas formas diferentes de cerrar el modal, no pude encontrar
  //La solucion para la fecha de entrega. Ya que el data-dismiss="modal" no funciona, tambien intente con
  //Scripts de javascript o mismo intentar cerrarlo desde el componente con varios metodos pero no tuvieron efecto.
  //Al principio ni siquiera me mostraba el modal, y logre que se viera pisando algunos estilos de bootstrap
  // en el css.

  //Finalmente opte por realizar una comunicacion entre el componente Get y Delete, que es de padre a hijo
  //Entonces se inicializa el componente hijo (el delete) con la id del empleado que le pasa el padre
  //y al inicializarse borra a ese empleado, para luego enviar un mensaje al padre y que este lo reciba
  // para asi saber que tiene que actualizar la tabla
  

  constructor(private empleadoService: EmpleadosService, public get: GetComponent) { }

  ngOnInit(): void {
    this.eliminarEmpleado();
    this.sendMessageParent(); //le decimos al padre que ya lo elimino para que actualice la tabla
  }

  eliminarEmpleado(): void {

    this.empleadoService.eliminarEmpleado(this.idSentByParent).subscribe(res => {
    this.get.obtenerEmpleados();
      //console.log("se elimino el empleado");
    })
}

  sendMessageParent()
  {
    this.sendOutParent.emit(true);
  }
}