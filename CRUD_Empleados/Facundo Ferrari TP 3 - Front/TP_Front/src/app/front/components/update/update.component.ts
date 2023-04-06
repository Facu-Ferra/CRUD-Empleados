import { Component, OnInit } from '@angular/core';
import { EmpleadosService } from '../../services/empleados.service';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Empleado } from '../../models/empleado';
import { DateValidators } from '../../customValidators/dateValidators';
import { ActivatedRoute } from '@angular/router';
import { DatePipe } from '@angular/common';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

  public idToUpdate: string;

  public empleadoToUpdate: Empleado = new Empleado();

  public form: FormGroup;

  public localhost = environment.localhost;

  public fechaActual: Date = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate());
  public errorMessage: string = "";
  public successMessage: string = "";
  public fechaActualStr: string = this.pd.transform(this.fechaActual, "yyyy-MM-dd");

  get nombreCtrl(): AbstractControl {
    return this.form.get('nombre');
  }

  get apellidoCtrl(): AbstractControl {
    return this.form.get('apellido');
  }

  get emailCtrl(): AbstractControl {
    return this.form.get('email');
  }

  get nroDocumentoCtrl(): AbstractControl {
    return this.form.get('nroDocumento');
  }

  get fechaNacimientoCtrl(): AbstractControl {
    return this.form.get('fechaNacimiento');
  }

  get fechaIngresoCtrl(): AbstractControl {
    return this.form.get('fechaIngreso');
  }


  constructor(private empleadoService: EmpleadosService, private pd: DatePipe, private readonly fb: FormBuilder, private route: ActivatedRoute) {

   
  }

  onSubmit(): void {
    console.log(this.form.value)
  }

  ngOnInit(): void {

    this.setearID();
    this.obtenerEmpleado();
    
    this.initForm();

  } 

  initForm() {



    this.form = this.fb.group({
      nombre: ['', [Validators.required, Validators.nullValidator, Validators.maxLength(15), Validators.pattern('^[a-zA-Z\s]*$')]],
      apellido: ['', [Validators.required, Validators.nullValidator, Validators.maxLength(15), Validators.pattern('^[a-zA-Z\s]*$')]],
      email: ['', [Validators.email, Validators.required, Validators.nullValidator]],
      nroDocumento: ['', [Validators.pattern('^[0-9]{5,9}$')]], //la api acepta dni de hasta 9 digitos
      fechaNacimiento: ['', [Validators.required, Validators.nullValidator, DateValidators.validarFechaNacimiento]],
      fechaIngreso: ['', [Validators.required, Validators.nullValidator, DateValidators.validarFechaIngreso]]
    });

  }

  actualizarEmpleado() {
    let empleado = new Empleado();

    empleado.nombre = this.form.get('nombre').value;
    empleado.apellido = this.form.get('apellido').value;
    empleado.email = this.form.get('email').value;
    empleado.nroDocumento = this.form.get('nroDocumento').value;
    empleado.fechaNacimiento = this.form.get('fechaNacimiento').value;
    empleado.fechaIngreso = this.form.get('fechaIngreso').value;

    this.empleadoService.actualizarEmpleado(empleado, this.idToUpdate).subscribe(res => {
      
      this.form.reset();

      this.successMessage=("Se actualizo el empleado exitosamente");
    },
      (error) => {
        // Manejamos el error segun el status

        if (error.status == 409) {
          this.errorMessage = "Ya existe un empleado con ese correo electrónico o número de documento";
        } else {
          this.errorMessage = "Ocurrió un error al actualizar el empleado. Por favor, inténtalo nuevamente más tarde.";
        }
      });
  }
  setearID() {
    this.route.paramMap.subscribe(params => {
      this.idToUpdate = params.get('id');
    });
  }
  obtenerEmpleado() {
    this.empleadoService.obtenerEmpleado(this.idToUpdate).subscribe(res => {

      this.empleadoToUpdate = res;
      console.log(this.empleadoToUpdate);

    })
  }
}
