import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { FrontModule } from './front/front.module';
import { DatePipe } from '@angular/common';
import { DeleteComponent } from './front/components/delete/delete.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FrontModule
  ],
  providers: [DatePipe, DeleteComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
