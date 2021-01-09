import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule } from '@angular/common/http';
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
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
