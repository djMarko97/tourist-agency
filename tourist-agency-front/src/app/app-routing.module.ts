import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeaderComponent } from './common/header/header.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { AuthGuard } from './guards/auth.guard';
import { BasicLayoutComponent } from './layouts/basic-layout/basic-layout.component';
import { ArrangementComponent } from './pages/arrangement/arrangement/arrangement.component';
import { AddReservationComponent } from './pages/add-reservation/add-reservation.component';
import { HotelsComponent } from './pages/hotels/hotels.component';
import { HotelDetailsComponent } from './pages/hotel-details/hotel-details.component';
import { MyReservationsComponent } from './pages/my-reservations/my-reservations.component';
const routes: Routes = [
  {path: '', component: HeaderComponent,  canActivate:[AuthGuard], children:[
    {path: 'home', component: AddReservationComponent},
      {path: '', redirectTo: '/home', pathMatch: 'full' },
      {path: 'arrangement', component: ArrangementComponent, pathMatch: 'full'},
      {path: 'add-reservation', component: AddReservationComponent, pathMatch: 'full'},
      {path: 'my-reservations', component: MyReservationsComponent, pathMatch: 'full'},
      {path: 'hotels', component: HotelsComponent, pathMatch: 'full'},
      {path: 'details/:id', component: HotelDetailsComponent, pathMatch: 'full'},

  ]},
  {
    path: '', component: BasicLayoutComponent, children:[
      {path: 'login', component: LoginComponent},
      {path:'register', component: RegisterComponent}
    ]
  },
  {path: '**', redirectTo: 'home'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
