import { TestBed } from '@angular/core/testing';

import { EmpleadosService } from './empleados.service';

describe('ApiEmpleadosServiceService', () => {
  let service: EmpleadosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmpleadosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
