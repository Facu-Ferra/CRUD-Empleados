import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Empleado } from '../../models/empleado';
import { GetComponent } from '../get/get.component';
import { EmpleadosService } from '../../services/empleados.service';
import { DateValidators } from '../../customValidators/dateValidators';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {


  public form: FormGroup;
  public fechaActual: Date = new Date(new Date().getFullYear(),new Date().getMonth(),new Date().getDate());
  public errorMessage:string = "";
  public successMessage:string = "";
  public fechaActualStr:string;

  get nombreCtrl(): AbstractControl{
    return this.form.get('nombre');
  }

  get apellidoCtrl(): AbstractControl{
    return this.form.get('apellido');
  }

  get emailCtrl(): AbstractControl{
    return this.form.get('email');
  }

  get nroDocumentoCtrl(): AbstractControl{
    return this.form.get('nroDocumento');
  }

  get fechaNacimientoCtrl(): AbstractControl{
    return this.form.get('fechaNacimiento');
  }

  get fechaIngresoCtrl(): AbstractControl{
    return this.form.get('fechaIngreso');
  }

  constructor(private readonly fb: FormBuilder, private pd:DatePipe, private empleadoService: EmpleadosService, private get: GetComponent) { }


  ngOnInit(): void {

    this.fechaActualStr = this.pd.transform(this.fechaActual, "yyyy-MM-dd");
    this.initForm();
    
  }

  initForm(){
    this.form = this.fb.group({
      nombre: ['',[Validators.required, Validators.nullValidator, Validators.maxLength(15), Validators.pattern('^[a-zA-Z\s]*$')]],
      apellido: ['', [Validators.required, Validators.nullValidator, Validators.maxLength(15), Validators.pattern('^[a-zA-Z\s]*$')]],
      email: ['', [Validators.email, Validators.required, Validators.nullValidator]],
      nroDocumento: ['', [Validators.pattern('^[0-9]{5,9}$')]], //la api acepta dni de hasta 9 digitos
      fechaNacimiento : ['', [Validators.required, Validators.nullValidator, DateValidators.validarFechaNacimiento]],
      fechaIngreso : ['', [Validators.required, Validators.nullValidator, DateValidators.validarFechaIngreso]]
    });
  }


  onSubmit(): void{
    console.log(this.form.value)
  }

  onClickLimpiar(): void{
    if(this.nombreCtrl){
      this.nombreCtrl.setValue('');
    }
  }
  
  guardarEmpleado(){
    let empleado = new Empleado();

    empleado.nombre = this.form.get('nombre').value;
    empleado.apellido = this.form.get('apellido').value;
    empleado.email = this.form.get('email').value;
    empleado.nroDocumento = this.form.get('nroDocumento').value;
    empleado.fechaNacimiento = this.form.get('fechaNacimiento').value;
    empleado.fechaIngreso = this.form.get('fechaIngreso').value;

    this.empleadoService.agregarEmpleado(empleado).subscribe(res => {
    this.form.reset();

    this.get.obtenerEmpleados();
    this.successMessage = "El empleado fue agregado correctamente";
    console.log("Se guardo el producto");
    },
    (error) => {
      // Manejamos el error segun el status
      
      if (error.status == 409) {
        this.errorMessage = "Ya existe un empleado con ese correo electrónico o número de documento";
    } else {
        this.errorMessage = "Ocurrió un error al guardar el empleado. Por favor, inténtalo nuevamente más tarde.";
    } 
    });

    
  }
}









