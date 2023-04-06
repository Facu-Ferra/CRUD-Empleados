import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GetComponent } from './front/components/get/get.component';
import { UpdateComponent } from './front/components/update/update.component';

const routes: Routes = [
  {
    path: '',
    component: GetComponent
  },
  {
    path: 'editarEmpleado/:id',
    component: UpdateComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
