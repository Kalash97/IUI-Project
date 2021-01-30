import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule, PagesOfSite } from './app-routing.module';

import { AppComponent } from './app.component';

import { TopMenuComponent } from './angular/layout/top-menu/top-menu.component';
import { PageBodyComponent } from './angular/layout/page-body/page-body.component';
import {AppHttpInterceptor} from "./angular/services/http.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    PagesOfSite,
    TopMenuComponent,
    PageBodyComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,

    AppRoutingModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AppHttpInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
