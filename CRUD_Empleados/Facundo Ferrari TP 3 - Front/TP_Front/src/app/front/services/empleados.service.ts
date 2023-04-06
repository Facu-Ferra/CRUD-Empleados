import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { end } from '@popperjs/core';
import { Empleado } from '../models/empleado';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmpleadosService {

  endpoint: string = "empleado";

  constructor(private http: HttpClient) { }

  public agregarEmpleado(empleadoRequest: Empleado): Observable<any> {
    let url = environment.api + this.endpoint;
    return this.http.post(url,empleadoRequest);
  }

  public obtenerEmpleados(): Observable<Array<Empleado>> {
    let url = environment.api + this.endpoint;
    return this.http.get<Array<Empleado>>(url);
  }

  public obtenerEmpleado(id: string): Observable<Empleado> {
    let url = environment.api + this.endpoint + "/" + id;
    return this.http.get<Empleado>(url);
  }

  public eliminarEmpleado(id : number): Observable<any> {
    let url = environment.api + this.endpoint + "/" + id;
    return this.http.delete(url);
  }

  public actualizarEmpleado(empleadoRequest: Empleado, id : string): Observable<any> {
    let url = environment.api + this.endpoint + "/" + id;
    return this.http.put(url,empleadoRequest);
  }

}
