import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeaderComponent } from './common/header/header.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { AuthGuard } from './guards/auth.guard';
import { BasicLayoutComponent } from './layouts/basic-layout/basic-layout.component';
const routes: Routes = [
  {path: '', component: HeaderComponent,  canActivate:[AuthGuard], children:[

  ]},
  {
<<<<<<< Updated upstream
    path: '', component: BasicLayoutComponent, children:[
      {path: 'login', canActivate:[AuthGuard], component: LoginComponent},
=======
    path: '', children:[
      {path: 'login', component: LoginComponent},
>>>>>>> Stashed changes
      {path:'register', component: RegisterComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
