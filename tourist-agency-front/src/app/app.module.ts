import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './common/header/header.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './pages/login/login.component';
import { BaseRegisterComponent } from './common/base-register/base-register.component';
<<<<<<< Updated upstream
import { CommonModule } from '@angular/common';
=======
import { RegisterComponent } from './pages/register/register.component';
import { BasicLayoutComponent } from './layouts/basic-layout/basic-layout.component';
>>>>>>> Stashed changes

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    BaseRegisterComponent,
    RegisterComponent,
    BasicLayoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    CommonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
