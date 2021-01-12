import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule, PagesOfSite } from './app-routing.module';

import { AppComponent } from './app.component';

import { TopMenuComponent } from './angular/layout/top-menu/top-menu.component';
import { PageBodyComponent } from './angular/layout/page-body/page-body.component';

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
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
