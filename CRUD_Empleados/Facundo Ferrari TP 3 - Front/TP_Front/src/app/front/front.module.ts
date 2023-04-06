import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GetComponent } from './components/get/get.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AddComponent } from './components/add/add.component';
import { HttpClientModule } from '@angular/common/http';
import { DeleteComponent } from './components/delete/delete.component';
import { UpdateComponent } from './components/update/update.component';



@NgModule({
  declarations: [
    GetComponent,
    AddComponent,
    DeleteComponent,
    UpdateComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule
  ]
})
export class FrontModule { }
