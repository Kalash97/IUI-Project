import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PageContactComponent } from './angular/pages/page-contact/page-contact.component';
import { PageError404Component } from './angular/pages/page-error404/page-error404.component';
import { PageOffersComponent } from './angular/pages/page-offers/page-offers.component';
import { PageTicketsComponent } from './angular/pages/page-tickets/page-tickets.component';
import { PageUserComponent } from './angular/pages/page-user/page-user.component';
import { PageLoginComponent } from "./angular/pages/page-login/page-login.component";

const routes: Routes = [
    { path: '', component: PageOffersComponent },
    { path: 'offers', component: PageOffersComponent },
    { path: 'tickets', component: PageTicketsComponent },
    { path: 'user', component: PageUserComponent },
    { path: 'contact', component: PageContactComponent },
    { path: 'login', component: PageLoginComponent },
    { path: '**', component: PageError404Component }


];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }

export const PagesOfSite = [
    PageContactComponent,
    PageError404Component,
    PageOffersComponent,
    PageTicketsComponent,
    PageUserComponent,
    PageLoginComponent
];
