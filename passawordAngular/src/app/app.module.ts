import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { rounting } from "./app.routes";
import { FormsModule } from "@angular/forms";
import { HttpModule } from "@angular/http";
import "rxjs/add/operator/map";



@NgModule({
  imports: [BrowserModule,rounting, FormsModule, HttpModule],
  declarations: [AppComponent],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
