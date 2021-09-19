import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeaderComponent } from './common/header/header.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { AuthGuard } from './guards/auth.guard';
import { BasicLayoutComponent } from './layouts/basic-layout/basic-layout.component';
const routes: Routes = [
  {path: '', component: HeaderComponent, children:[

  ]},
  {
    path: '', component: BasicLayoutComponent, children:[
      {path: 'login', canActivate:[AuthGuard], component: LoginComponent},
      {path:'register', component: RegisterComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
