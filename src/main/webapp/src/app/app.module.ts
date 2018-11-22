import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatAutocompleteModule, MatNativeDateModule} from "@angular/material";


import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ErrMessageComponent } from './err-message/err-message.component';
import { CreateEvenementComponent } from './create-evenement/create-evenement.component';
import { CreateAddressComponent } from './create-address/create-address.component';
import { EvenementComponent } from './evenement/evenement.component';
import { InvitationComponent } from './invitation/invitation.component';
import { ProfilComponent } from './profil/profil.component';
import { UsersComponent } from './users/users.component';
import { AdminComponent } from './admin/admin.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    DashboardComponent,
    ErrMessageComponent,
    CreateEvenementComponent,
    CreateAddressComponent,
    EvenementComponent,
    InvitationComponent,
    ProfilComponent,
    UsersComponent,
    AdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatNativeDateModule,
    MatAutocompleteModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
