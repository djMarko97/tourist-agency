import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/authority/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public isAdmin!: boolean;

  constructor(private authService: AuthService, private router: Router) {
    this.isAdmin = this.authService.isAdmin();
  }

  ngOnInit(): void {
  }

  public logout(): void {
    this.authService.logout();
    this.router.navigate(['login']);
  }
}
