import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './common/header/header.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './pages/login/login.component';
import { BaseRegisterComponent } from './common/base-register/base-register.component';
import { CommonModule } from '@angular/common';
import { RegisterComponent } from './pages/register/register.component';
import { BasicLayoutComponent } from './layouts/basic-layout/basic-layout.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ArrangementComponent } from './pages/arrangement/arrangement/arrangement.component';
import { AddReservationComponent } from './pages/add-reservation/add-reservation.component';
import { HotelsComponent } from './pages/hotels/hotels.component';
import { HotelDetailsComponent } from './pages/hotel-details/hotel-details.component';
import { MyReservationsComponent } from './pages/my-reservations/my-reservations.component';
import { HomeComponent } from './pages/home/home.component';
import {MatToolbarModule} from '@angular/material/toolbar'
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import {MatIconModule} from '@angular/material/icon';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatButtonModule} from '@angular/material/button';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { HotelService } from './services/hotel/hotel.service';
import { JwtInterceptor } from './common/interceptors/jwt.interceptor';
import { ErrorInterceptor } from './common/interceptors/error.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    BaseRegisterComponent,
    RegisterComponent,
    BasicLayoutComponent,
    ArrangementComponent,
    AddReservationComponent,
    HotelsComponent,
    HotelDetailsComponent,
    MyReservationsComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    NgbModule,
    MatToolbarModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    HttpClientModule,
    FormsModule,
    MatSnackBarModule,
    CommonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule
  ],
  providers: [HotelService,
  {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
  {provide: HTTP_INTERCEPTORS, useClass:ErrorInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
